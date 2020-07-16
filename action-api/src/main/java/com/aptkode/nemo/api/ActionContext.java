package com.aptkode.nemo.api;

import java.util.Optional;

public class ActionContext {
    private final Arguments arguments;
    private final ActionResult previousResult;

    public ActionContext(Arguments arguments) {
        this.arguments = arguments;
        this.previousResult = null;
    }

    public ActionContext(Arguments arguments, ActionResult previousResult) {
        this.arguments = arguments;
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

    public Optional<ActionResult> previousResult(){
        return Optional.ofNullable(previousResult);
    }
}
