package com.aptkode.nemo.api;

import com.aptkode.nemo.api.argument.Args;

import java.util.Optional;

public class ActionContext {
    private final Arguments arguments;
    private final Args args;
    private final ActionResult previousResult;

    public ActionContext(Arguments arguments, Args args) {
        this.arguments = arguments;
        this.args = args;
        this.previousResult = null;
    }

    public ActionContext(Arguments arguments, Args args, ActionResult previousResult) {
        this.arguments = arguments;
        this.args = args;
        this.previousResult = previousResult;
    }

    public String getArgument(String argument) {
        return arguments.get(argument);
    }

    public String getArgument(String argument, String defaultValue){
        return arguments.get(argument, defaultValue);
    }

    public Arguments arguments() {
        return arguments;
    }

    public Args args(){
        return args;
    }

    public Optional<ActionResult> previousResult(){
        return Optional.ofNullable(previousResult);
    }
}
