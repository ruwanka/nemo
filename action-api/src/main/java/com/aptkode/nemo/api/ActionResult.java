package com.aptkode.nemo.api;

import java.util.HashMap;
import java.util.Map;

public class ActionResult {
    private final Map<String, Object> results = new HashMap<>();

    public <T> void put(String key, T value) {
        results.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) results.get(key);
    }
}
