package com.aptkode.nemo.maven;

import com.aptkode.nemo.api.Action;
import com.aptkode.nemo.api.ActionContext;
import com.aptkode.nemo.api.ActionResult;

import java.io.File;
import java.util.Optional;

public abstract class BaseMavenAction implements Action {

    File getPomFile(ActionContext context) {
        Optional<ActionResult> optionalPrevResult = context.previousResult();
        File pomFile = null;
        if (optionalPrevResult.isPresent()) {
            ActionResult actionResult = optionalPrevResult.get();
            pomFile = actionResult.get("pomFile");
            if (pomFile == null) {
                File dir = actionResult.get("dir");
                if (dir != null) {
                    File tempPomFile = new File(dir.getPath() + "/pom.xml");
                    if (tempPomFile.exists() && tempPomFile.isFile()) {
                        pomFile = tempPomFile;
                    }
                }
            }
        }
        if (pomFile == null) {
            String pomPath = context.arguments().get("pomFile");
            if (pomPath != null) {
                pomFile = new File(pomPath);
            }
        }
        if (pomFile == null) {
            throw new IllegalStateException("couldn't fine pom file!");
        }
        return pomFile;
    }

}
