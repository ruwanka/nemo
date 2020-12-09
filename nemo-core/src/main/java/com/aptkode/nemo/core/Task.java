package com.aptkode.nemo.core;

import com.aptkode.nemo.api.argument.Argument;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task {
    private String name;
    private Map<String,Object> arguments;
    private List<Argument<Object>> args;
    private List<Task> tasks;
    private Execution execution;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getArguments() {
        if(arguments == null){
            arguments = new HashMap<>();
        }
        return arguments;
    }

    public void setArguments(Map<String, Object> arguments) {
        this.arguments = arguments;
    }

    public List<Argument<Object>> getArgs() {
        return args;
    }

    public void setArgs(List<Argument<Object>> args) {
        this.args = args;
    }

    public List<Task> getTasks() {
        if(tasks == null){
            return Collections.emptyList();
        }
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Execution getExecution() {
        return execution;
    }

    public void setExecution(Execution execution) {
        this.execution = execution;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", arguments=" + arguments +
                ", tasks=" + tasks +
                ", execution=" + execution +
                '}';
    }
}
