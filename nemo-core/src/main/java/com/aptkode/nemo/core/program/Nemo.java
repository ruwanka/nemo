package com.aptkode.nemo.core.program;

import com.aptkode.nemo.core.Task;
import com.aptkode.nemo.core.TaskExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Nemo {

    private static final Logger logger = LogManager.getLogger(Nemo.class);

    public static void main(String[] args) {
        logger.info("starting nemo task {}", args[0]);
        TaskExecutor taskExecutor = new TaskExecutor();
        Task task = taskExecutor.read(args[0]);
        logger.debug(task);
        taskExecutor.execute(task);
    }
}
