package com.aptkode.nemo.api.argument;

public class StringArgument extends Argument<String> {

    @Override
    public Class<? extends Argument<String>> type() {
        return StringArgument.class;
    }
}
