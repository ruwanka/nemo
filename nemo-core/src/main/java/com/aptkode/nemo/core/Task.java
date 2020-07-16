package com.aptkode.nemo.core;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Task {
    private String name;
    private Map<String,String> arguments;
    private List<Task> tasks;
    private Execution execution;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getArguments() {
        return arguments;
    }

    public void setArguments(Map<String, String> arguments) {
        this.arguments = arguments;
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
