import com.aptkode.nemo.api.*;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DeserializeDifferentTypesOfArgumentsTest {

    @Test
    void test() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        KeyValueDeserializer keyValueDeserializer = new KeyValueDeserializer();
        keyValueDeserializer.registerArgumentByKey(Keys.WORK_DIR, FileArgument.class);
        SimpleModule args = new SimpleModule("args");
        args.addDeserializer(Argument.class, keyValueDeserializer);
        objectMapper.registerModule(args);
        Args arguments = objectMapper.readValue(new File("src/test/resources/test2.yml"), Args.class);
        System.out.println(arguments.get(Keys.WORK_DIR));
    }

    static class KeyValueDeserializer extends StdDeserializer<Argument>
    {
        private Map<String, Class<? extends Argument>> registry =
                new HashMap<String, Class<? extends Argument>>();

        KeyValueDeserializer()
        {
            super(Argument.class);
        }

        void registerArgumentByKey(Key uniqueAttribute,
                                   Class<? extends Argument> animalClass)
        {
            registry.put(uniqueAttribute.key(), animalClass);
        }

        @Override
        public Argument deserialize(
                JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException
        {
            ObjectMapper mapper = (ObjectMapper) jp.getCodec();
            ObjectNode root = (ObjectNode) mapper.readTree(jp);
            Class<? extends Argument> animalClass = null;
            Iterator<Map.Entry<String, JsonNode>> elementsIterator =
                    root.fields();
            while (elementsIterator.hasNext())
            {
                Map.Entry<String, JsonNode> element=elementsIterator.next();
                String name = element.getKey();
                if( name.equals("key")){
                    String k = element.getValue().asText();
                    if (registry.get(k) != null)
                    {
                        animalClass = registry.get(k);
                        break;
                    }
                }
            }
            if (animalClass == null) return null;
            return mapper.treeToValue(root, animalClass);
        }
    }


}
