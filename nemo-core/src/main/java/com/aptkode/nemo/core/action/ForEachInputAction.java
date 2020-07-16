package com.aptkode.nemo.core.action;

import com.aptkode.nemo.api.Action;
import com.aptkode.nemo.api.ActionContext;
import com.aptkode.nemo.api.ActionResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import java.util.Optional;

public class ForEachInputAction implements Action {
    @Override
    public ActionResult execute(ActionContext context) {
        String inputArg = context.getArgument("inputArg");
        String inputType = context.getArgument("inputType", "json-list");
        Optional<ActionResult> previousResult = context.previousResult();
        ActionResult result = new ActionResult();
        if(previousResult.isPresent()){
            ActionResult actionResult = previousResult.get();
            if("json-list".equals(inputType)) {
                String json = actionResult.get(inputArg);
                ObjectMapper objectMapper = new ObjectMapper();
                ObjectReader objectReader = objectMapper.readerFor(String[].class);
                String[] values = new String[0];
                try {
                    values = objectReader.readValue(json);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                result.put("repeatOn", values);
            }
        }
        return result;
    }

    @Override
    public String name() {
        return "for-each";
    }
}
