package com.aptkode.nemo.git.argument;

public class GitCheckOutConfig {
    private boolean create;
    private String branch;

    public boolean isCreate() {
        return create;
    }

    public void setCreate(boolean create) {
        this.create = create;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
