package com.aptkode.nemo.api;

public interface Action {

    ActionResult execute( ActionContext context );

    String name();

}
