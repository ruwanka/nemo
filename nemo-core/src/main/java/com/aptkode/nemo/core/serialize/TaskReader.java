package com.aptkode.nemo.core.serialize;

import com.aptkode.nemo.api.argument.Argument;
import com.aptkode.nemo.api.argument.ArgumentProvider;
import com.aptkode.nemo.core.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.ServiceLoader;

public class TaskReader {
    private final ObjectMapper objectMapper;

    private TaskReader() {
        objectMapper = new ObjectMapper(new YAMLFactory());
        SimpleModule module = new SimpleModule("args");
        ArgumentDeserializer argumentDeserializer = ArgumentDeserializer.getInstance();
        for (ArgumentProvider argumentProvider : ServiceLoader.load(ArgumentProvider.class)) {
            argumentProvider.get().forEach(argumentDeserializer::registerArgumentByKey);
        }
        module.addDeserializer(Argument.class, argumentDeserializer);
        objectMapper.registerModule(module);
    }

    public Task read(String path) throws IOException {
        ObjectReader objectReader = objectMapper.readerFor(Task.class);
        try {
            return objectReader.readValue(new File(path));
        } catch (MismatchedInputException e) {
            throw new IllegalArgumentException("cannot parse task!", e);
        }
    }

    public static TaskReader getInstance() {
        return SingletonHolder.reader;
    }

    private static class SingletonHolder {
        private static final TaskReader reader = new TaskReader();
    }
}
