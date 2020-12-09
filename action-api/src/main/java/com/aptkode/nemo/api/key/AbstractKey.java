package com.aptkode.nemo.api.key;

import com.aptkode.nemo.api.ActionResult;
import com.aptkode.nemo.api.Arguments;

public class AbstractKey<T> implements Key<T> {
    private final String id;

    public AbstractKey(String id) {
        this.id = id;
    }

    @Override
    public String key() {
        return id;
    }

    @Override
    public T getValue(ActionResult actionResult) {
        return actionResult.get(this);
    }

    @Override
    public T getValue(Arguments arguments) {
        return arguments.get(this);
    }

    @Override
    public void set(ActionResult actionResult, T value) {
        actionResult.put(this, value);
    }
}
