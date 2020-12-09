package com.aptkode.nemo.core;

import com.aptkode.nemo.core.serialize.ArgumentDeserializer;
import com.aptkode.nemo.core.serialize.TaskReader;
import com.aptkode.nemo.git.argument.GitCheckOutArgument;
import com.aptkode.nemo.git.argument.GitCheckOutConfig;
import com.aptkode.nemo.git.key.GitKeys;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskReaderTest {
    @Test
    void readArgsOfDifferentTypes() throws IOException {
        ArgumentDeserializer.getInstance().registerArgumentByKey(GitKeys.GIT_CHECK_OUT_CONFIG_KEY, GitCheckOutArgument.class);
        Task task = TaskReader.getInstance().read("src/test/resources/git-clone-checkout-new.yml");
        assertEquals(3, task.getTasks().get(0).getArgs().size());
        assertEquals("TASK-01", ((GitCheckOutConfig)task.getTasks().get(1).getArgs().get(0).getValue()).getBranch());
    }
}
