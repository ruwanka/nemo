package com.aptkode.nemo.api;

import java.util.HashMap;
import java.util.Map;

public class Arguments {
    private final Map<String, Object> args = new HashMap<>();

    public Arguments(Map<? extends String, ?> m) {
        if (m != null) {
            args.putAll(m);
        }
    }

    public String get(String key) {
        return (String) args.get(key);
    }

    public String get(String key, String defaultValue) {
        return (String) args.getOrDefault(key, defaultValue);
    }
}
