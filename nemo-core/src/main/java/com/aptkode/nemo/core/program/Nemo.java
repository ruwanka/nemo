package com.aptkode.nemo.core.program;

import com.aptkode.nemo.core.Task;
import com.aptkode.nemo.core.TaskExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Nemo {

    private static final Logger logger = LogManager.getLogger(Nemo.class);

    public static void main(String[] args) {
        try {
            logger.info("starting nemo task {}", args[0]);
            TaskExecutor taskExecutor = new TaskExecutor();
            Task task = taskExecutor.read(args[0]);
            logger.debug(task);
            taskExecutor.execute(task);
        }catch (FileNotFoundException e){
            logger.error("task not found {}", args[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
