package com.aptkode.nemo.api;

public interface Key<T> {

    String key();

    T getValue(ActionResult actionResult);

    T getValue(Arguments arguments);

    void set(ActionResult actionResult, T value);

}
