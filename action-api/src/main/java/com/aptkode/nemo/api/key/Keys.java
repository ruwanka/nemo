package com.aptkode.nemo.api.key;

public final class Keys {

    public static final FileKey WORK_DIR = new FileKey("workDir");
    public static final FileKey DIR = new FileKey("dir");
    public static final FileKey BASE_DIR = new FileKey("baseDir");
    public static final StringKey USERNAME = new StringKey("username");
    public static final StringKey PASSWORD = new StringKey("password");
    public static final StringKey URL = new StringKey("url");

    private Keys() {
        // hide default
    }
}
