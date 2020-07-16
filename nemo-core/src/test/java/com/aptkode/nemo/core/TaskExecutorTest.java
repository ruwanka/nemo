package com.aptkode.nemo.core;

import com.aptkode.nemo.api.Action;
import com.aptkode.nemo.api.ActionContext;
import com.aptkode.nemo.api.ActionResult;
import com.aptkode.nemo.core.action.ForEachInputAction;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TaskExecutorTest {

    @Test
    void readTaskTest() {
        TaskExecutor taskExecutor = new TaskExecutor();
        Task task = taskExecutor.read("src/test/resources/git-clone-edit-push.yml");
        assertEquals(5, task.getTasks().size());
    }

    @Test
    void executeTest() {
        ActionProvider actionProvider = mock(ActionProvider.class);
        doReturn(Optional.of(mock(Action.class))).when(actionProvider).get(anyString());

        TaskExecutor taskExecutor = Mockito.spy(new TaskExecutor(actionProvider));
        doReturn(null).when(taskExecutor).executeAction(any(), isNull());

        Task task = taskExecutor.read("src/test/resources/git-clone-edit-push.yml");
        taskExecutor.execute(task);

        verify(taskExecutor, times(15)).executeAction(any(), any());
    }

    @Test
    void executeActionTest() {
        ActionProvider actionProvider = mock(ActionProvider.class);
        Action action = mock(Action.class);
        doReturn(Optional.of(action)).when(actionProvider).get(eq("test-action"));

        Task t = new Task();
        t.setName("test-action");
        Map<String, String> arguments = new HashMap<>();
        t.setArguments(arguments);

        TaskExecutor taskExecutor = new TaskExecutor(actionProvider);
        taskExecutor.executeAction(t, null);

        ArgumentCaptor<ActionContext> contextArgumentCaptor = ArgumentCaptor.forClass(ActionContext.class);
        verify(action, times(1)).execute(contextArgumentCaptor.capture());
        assertEquals(arguments, contextArgumentCaptor.getValue().arguments());
    }

    @Test
    void executeForEachActionTest() {
        ActionProvider actionProvider = mock(ActionProvider.class);
        ForEachInputAction forEachInputAction = spy(new ForEachInputAction());
        doReturn(Optional.of(forEachInputAction)).when(actionProvider).get(eq("for-each-input"));
        doReturn(Optional.of(mock(Action.class))).when(actionProvider).get(anyString());

        TaskExecutor taskExecutor = Mockito.spy(new TaskExecutor(actionProvider));
        ActionResult result = new ActionResult();
        result.put("repeatOn", Collections.singletonList("input"));
        doReturn(result).when(taskExecutor).executeAction(argThat(task -> task.getName().equals("for-each-input")), isNull());
        doReturn(result).when(taskExecutor).executeAction(any(), isNull());

        Task task = taskExecutor.read("src/test/resources/for-each-task.yml");
        taskExecutor.execute(task);

        verify(taskExecutor, times(5)).executeAction(any(), any());
        verify(taskExecutor, times(1)).executeForEachAction(any(), any());
        verify(taskExecutor, times(2)).execute(any());
    }

}
