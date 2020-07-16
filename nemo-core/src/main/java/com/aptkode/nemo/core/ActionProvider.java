package com.aptkode.nemo.core;

import com.aptkode.nemo.api.Action;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;

public class ActionProvider {

    private static final ActionHolder holder = new ActionHolder();

    public Optional<Action> get(String name){
        return Optional.of(holder.getActions().get(name));
    }

    private static class ActionHolder{
        private final Map<String, Action> actions = new HashMap<>();

        public ActionHolder() {
            for (Action action : ServiceLoader.load(Action.class)) {
                actions.put(action.name(), action);
            }
        }

        public Map<String, Action> getActions() {
            return actions;
        }
    }
}
