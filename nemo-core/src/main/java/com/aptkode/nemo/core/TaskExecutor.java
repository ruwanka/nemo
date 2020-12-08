package com.aptkode.nemo.core;

import com.aptkode.nemo.api.Action;
import com.aptkode.nemo.api.ActionContext;
import com.aptkode.nemo.api.ActionResult;
import com.aptkode.nemo.api.Arguments;
import com.aptkode.nemo.core.serialize.TaskReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TaskExecutor {
    private final ActionProvider actionProvider;
    private static final Logger logger = LogManager.getLogger(TaskExecutor.class);

    public TaskExecutor() {
        actionProvider = new ActionProvider();
    }

    public TaskExecutor(ActionProvider actionProvider) {
        this.actionProvider = actionProvider;
    }

    public Task read(String path) {
        return TaskReader.getInstance().read(path);
    }

    public ActionResult execute(Task task) {
        Execution execution = task.getExecution();
        String by = execution.getBy();
        String argument = execution.getArgument();
        List<String> repeatOn = execution.getRepeatOn();
        ActionResult previousResult = null;
        for (String arg : repeatOn) {
            List<Task> tasks = task.getTasks();
            Optional<Task> commonTask = tasks.stream().filter(t -> t.getName().equals(by)).findFirst();
            commonTask.ifPresent(t -> t.getArguments().put(argument, arg));
            for (Task t : tasks) {
                if ("for-each-input".equals(t.getName())) {
                    previousResult = executeForEachAction(t, previousResult);
                } else {
                    previousResult = executeAction(t, previousResult);
                }
            }
        }
        return previousResult;
    }

    public ActionResult executeAction(Task t, ActionResult previousResult) {
        String name = t.getName();
        logger.info("executing action {}", name);
        Optional<Action> optionalAction = actionProvider.get(name);
        Action action = optionalAction.orElseThrow(() -> new IllegalArgumentException("no action found by name " + name));
        return action.execute(new ActionContext(new Arguments(t.getArguments()), previousResult));
    }

    public ActionResult executeForEachAction(Task t, ActionResult previousResult) {
        Execution execution = t.getExecution();
        if (execution != null) {
            throw new IllegalStateException("for each input should'n have execution");
        }
        Map<String, Object> arguments = t.getArguments();
        String ouputArg = (String) arguments.get("ouputArg");

        ActionResult actionResult = executeAction(t, previousResult);

        Execution newExecution = new Execution();
        newExecution.setArgument(ouputArg);
        newExecution.setBy(t.getTasks().get(0).getName()); // todo validate
        newExecution.setRepeatOn(actionResult.get("repeatOn"));
        t.setExecution(newExecution);

        return execute(t);
    }
}
