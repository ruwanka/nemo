package com.aptkode.nemo.core.serialize;

import com.aptkode.nemo.api.Argument;
import com.aptkode.nemo.api.Key;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@SuppressWarnings("rawtypes")
class ArgumentDeserializer extends StdDeserializer<Argument> {
    private final Map<String, Class<? extends Argument>> registry = new HashMap<>();

    ArgumentDeserializer() {
        super(Argument.class);
    }

    void registerArgumentByKey(Key uniqueAttribute, Class<? extends Argument> animalClass) {
        registry.put(uniqueAttribute.key(), animalClass);
    }

    @Override
    public Argument deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        ObjectNode root = mapper.readTree(jp);
        Class<? extends Argument> animalClass = null;
        Iterator<Map.Entry<String, JsonNode>> elementsIterator = root.fields();
        while (elementsIterator.hasNext()) {
            Map.Entry<String, JsonNode> element = elementsIterator.next();
            String name = element.getKey();
            if (name.equals("key")) {
                String k = element.getValue().asText();
                if (registry.get(k) != null) {
                    animalClass = registry.get(k);
                    break;
                }
            }
        }
        if (animalClass == null) throw new IllegalArgumentException("argument should have key property!");
        return mapper.treeToValue(root, animalClass);
    }
}