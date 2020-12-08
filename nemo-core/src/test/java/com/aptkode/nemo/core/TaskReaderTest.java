package com.aptkode.nemo.core;

import com.aptkode.nemo.core.serialize.TaskReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskReaderTest {
    @Test
    void readArgsOfDifferentTypes(){
        Task task = TaskReader.getInstance().read("src/test/resources/test2.yml");
        assertEquals(1, task.getTasks().get(0).getArgs().size());
    }
}
