package com.aptkode.nemo.api.argument;

import com.aptkode.nemo.api.key.Key;

import java.util.Map;

@SuppressWarnings("rawtypes")
public interface ArgumentProvider {
    Map<Key, Class<? extends Argument>> get();
}
