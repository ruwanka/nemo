package com.aptkode.nemo.api;

import java.io.File;

public class FileArgument extends Argument<File> {

    private File value;

    @Override
    public File getValue() {
        return value;
    }

    @Override
    public void setValue(File value) {
        this.value = value;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Class<? extends Argument> type() {
        return FileArgument.class;
    }

    @Override
    public String toString() {
        return "FileArgument{" +
                "value=" + value +
                ", key='" + getKey() + '\'' +
                ", type='" + type() + '\'' +
                '}';
    }
}
