package com.aptkode.nemo.api;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class CoreArgumentProvider implements ArgumentProvider {
    private static final Map<Key, Class<? extends Argument>> registry = new HashMap<>();

    public CoreArgumentProvider() {
        // register api types
        registry.put(Keys.WORK_DIR, FileArgument.class);
    }

    @Override
    public Map<Key, Class<? extends Argument>> get() {
        return registry;
    }
}
