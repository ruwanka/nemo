package com.aptkode.nemo.api.argument;

import com.aptkode.nemo.api.key.Key;
import com.aptkode.nemo.api.key.Keys;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class CoreArgumentProvider implements ArgumentProvider {
    private static final Map<Key, Class<? extends Argument>> registry = new HashMap<>();

    public CoreArgumentProvider() {
        // register api keys with argument types
        registry.put(Keys.WORK_DIR, FileArgument.class);
        registry.put(Keys.USERNAME, StringArgument.class);
        registry.put(Keys.PASSWORD, StringArgument.class);
        registry.put(Keys.BASE_DIR, FileArgument.class);
    }

    @Override
    public Map<Key, Class<? extends Argument>> get() {
        return registry;
    }
}
