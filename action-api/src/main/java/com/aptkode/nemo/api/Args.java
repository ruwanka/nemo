package com.aptkode.nemo.api;

import java.util.List;
import java.util.Optional;

public class Args {

    private List<Argument<Object>> args;

    public List<Argument<Object>> getArgs() {
        return args;
    }

    public void setArgs(List<Argument<Object>> args) {
        this.args = args;
    }

    public <T> T get(Key<T> key ){
        Optional<Argument<Object>> first = args.stream().filter(a -> a.getKey().equals(key.key())).findFirst();
        return (T) first.get().getValue();
    }
}
