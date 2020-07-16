package com.aptkode.nemo.core;

import java.util.List;

public class Execution {
    private String by;
    private String argument;
    private List<String> repeatOn;

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public List<String> getRepeatOn() {
        return repeatOn;
    }

    public void setRepeatOn(List<String> repeatOn) {
        this.repeatOn = repeatOn;
    }

    @Override
    public String toString() {
        return "Execution{" +
                "by='" + by + '\'' +
                ", argument='" + argument + '\'' +
                ", repeatOn=" + repeatOn +
                '}';
    }
}
