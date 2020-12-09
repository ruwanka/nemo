package com.aptkode.nemo.api.argument;

import java.io.File;

public class FileArgument extends Argument<File> {

    @Override
    public Class<? extends Argument<File>> type() {
        return FileArgument.class;
    }
}
