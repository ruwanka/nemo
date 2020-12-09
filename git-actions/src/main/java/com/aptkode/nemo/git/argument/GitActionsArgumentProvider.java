package com.aptkode.nemo.git.argument;

import com.aptkode.nemo.api.argument.Argument;
import com.aptkode.nemo.api.argument.ArgumentProvider;
import com.aptkode.nemo.api.key.Key;
import com.aptkode.nemo.git.key.GitKeys;

import java.util.Collections;
import java.util.Map;

public class GitActionsArgumentProvider implements ArgumentProvider {
    @Override
    public Map<Key, Class<? extends Argument>> get() {
        return Collections.singletonMap(GitKeys.GIT_CHECK_OUT_CONFIG_KEY, GitCheckOutArgument.class);
    }
}
