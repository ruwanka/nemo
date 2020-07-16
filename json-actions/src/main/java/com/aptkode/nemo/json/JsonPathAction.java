package com.aptkode.nemo.json;

import com.aptkode.nemo.api.Action;
import com.aptkode.nemo.api.ActionContext;
import com.aptkode.nemo.api.ActionResult;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class JsonPathAction implements Action {
    @Override
    public ActionResult execute(ActionContext context) {
        String jsonSrc = context.getArgument("jsonSrc");
        String jsonPath = context.getArgument("jsonPath");
        DocumentContext jsonContext = JsonPath.parse(jsonSrc);
        String out = jsonContext.read(jsonPath).toString();
        ActionResult actionResult = new ActionResult();
        actionResult.put("json", out);
        return actionResult;
    }

    @Override
    public String name() {
        return "json-path";
    }
}
