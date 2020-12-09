package com.aptkode.nemo.api.key;

import com.aptkode.nemo.api.ActionResult;
import com.aptkode.nemo.api.Arguments;

public interface Key<T> {

    String key();

    T getValue(ActionResult actionResult);

    T getValue(Arguments arguments);

    void set(ActionResult actionResult, T value);

}
