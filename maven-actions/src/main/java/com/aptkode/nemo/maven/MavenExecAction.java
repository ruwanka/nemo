package com.aptkode.nemo.maven;

import com.aptkode.nemo.api.Action;
import com.aptkode.nemo.api.ActionContext;
import com.aptkode.nemo.api.ActionResult;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MavenExecAction implements Action {
    @Override
    public ActionResult execute(ActionContext context) {
        var processBuilder = new ProcessBuilder();

        File dir;

        Optional<ActionResult> actionResult = context.previousResult();
        if (actionResult.isPresent()) {
            dir = actionResult.get().get("dir");
        } else {
            dir = new File(context.arguments().get("dir"));
        }

        String command = context.arguments().get("command");
        String[] split = command.split(",");
        List<String> args = new ArrayList<>();
        args.add("mvn");
        for (String cmd : split) {
            args.add(cmd.trim());
        }

        processBuilder.command(args);
        processBuilder.directory(dir);

        Process process;
        try {
            process = processBuilder.start();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        try (var reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i = 0;
        try {
            i = process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("process exited " + i);
        return new ActionResult();
    }

    @Override
    public String name() {
        return "mvn-exec";
    }
}
