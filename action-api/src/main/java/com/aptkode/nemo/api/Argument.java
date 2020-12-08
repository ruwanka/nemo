package com.aptkode.nemo.api;

public abstract class Argument<V> {
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public abstract V getValue();

    public abstract void setValue(V value);

    public abstract Class<? extends Argument> type();
}
