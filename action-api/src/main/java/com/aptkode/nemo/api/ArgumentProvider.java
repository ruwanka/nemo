package com.aptkode.nemo.api;

import java.util.Map;

@SuppressWarnings("rawtypes")
public interface ArgumentProvider {
    Map<Key, Class<? extends Argument>> get();
}
