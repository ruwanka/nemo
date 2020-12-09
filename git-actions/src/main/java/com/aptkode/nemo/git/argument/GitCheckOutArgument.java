package com.aptkode.nemo.git.argument;

import com.aptkode.nemo.api.argument.Argument;

public class GitCheckOutArgument extends Argument<GitCheckOutConfig> {

    @Override
    public Class<? extends Argument<GitCheckOutConfig>> type() {
        return GitCheckOutArgument.class;
    }
}
