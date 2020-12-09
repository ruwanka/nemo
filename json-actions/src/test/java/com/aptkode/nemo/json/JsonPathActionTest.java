package com.aptkode.nemo.json;

import com.aptkode.nemo.api.ActionContext;
import com.aptkode.nemo.api.ActionResult;
import com.aptkode.nemo.api.Arguments;
import com.aptkode.nemo.api.argument.Args;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonPathActionTest {

    @Test
    void test(){
        String json = "{\n" +
                "    \"tool\": \n" +
                "    {\n" +
                "        \"jsonpath\": \n" +
                "        {\n" +
                "            \"creator\": \n" +
                "            {\n" +
                "                \"name\": \"Jayway Inc.\",\n" +
                "                \"location\": \n" +
                "                [\n" +
                "                    \"Malmo\",\n" +
                "                    \"San Francisco\",\n" +
                "                    \"Helsingborg\"\n" +
                "                ]\n" +
                "            }\n" +
                "        }\n" +
                "    },\n" +
                " \n" +
                "    \"book\": \n" +
                "    [\n" +
                "        {\n" +
                "            \"title\": \"Beginning JSON\",\n" +
                "            \"price\": 49.99\n" +
                "        },\n" +
                " \n" +
                "        {\n" +
                "            \"title\": \"JSON at Work\",\n" +
                "            \"price\": 29.99\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        JsonPathAction jsonPathAction = new JsonPathAction();
        HashMap<String, Object> arguments = new HashMap<>();
        arguments.put("jsonSrc", json);
        arguments.put("jsonPath", "$.book[*].price");
        ActionResult result = jsonPathAction.execute(new ActionContext(new Arguments(arguments), new Args()));
        assertEquals("[49.99,29.99]", result.get("json"));
    }
}
