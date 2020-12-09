package com.aptkode.nemo.api.argument;

import com.aptkode.nemo.api.key.Key;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Args {

    private List<Argument<Object>> args;

    public Args() {
        this.args = new ArrayList<>();
    }

    public Args(List<Argument<Object>> args) {
        this.args = args;
    }

    public List<Argument<Object>> getArgs() {
        return args;
    }

    public void setArgs(List<Argument<Object>> args) {
        this.args = args;
    }

    public void put(Argument arg){
        this.args.add(arg);
    }

    public <T> T get(Key<T> key ){
        Optional<Argument<Object>> arg = args.stream().filter(a -> a.getKey().equals(key.key())).findFirst();
        if(arg.isPresent()) {
            return (T) arg.get().getValue();
        }else{
            throw new IllegalArgumentException(key.key() + " is not provided!");
        }
    }

    @SuppressWarnings("unchecked")
    public <T> Optional<T> getOptional(Key<T> key) {
        Optional<Argument<Object>> arg = args.stream().filter(a -> a.getKey().equals(key.key())).findFirst();
        return arg.flatMap(objectArgument -> (Optional<T>) Optional.of(objectArgument.getValue()));
    }
//
//    @SuppressWarnings("unchecked")
//    public <T> T get(Key<T> key, T defaultValue) {
//        Optional<Argument<Object>> first = args.stream().filter(a -> a.getKey().equals(key.key())).findFirst();
//        if (first.isPresent()) {
//            return (T) first.get().getValue();
//        } else {
//            return defaultValue;
//        }
//    }
}
