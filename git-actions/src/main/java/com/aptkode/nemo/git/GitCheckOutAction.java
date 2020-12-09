package com.aptkode.nemo.git;

import com.aptkode.nemo.api.Action;
import com.aptkode.nemo.api.ActionContext;
import com.aptkode.nemo.api.ActionResult;
import com.aptkode.nemo.api.key.Keys;
import com.aptkode.nemo.git.argument.GitCheckOutConfig;
import com.aptkode.nemo.git.key.GitKeys;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class GitCheckOutAction implements Action {
    @Override
    public ActionResult execute(ActionContext actionContext) {
        Optional<ActionResult> optionalActionResult = actionContext.previousResult();
        File dir;
        if (optionalActionResult.isPresent()) {
            ActionResult actionResult = optionalActionResult.get();
            dir = actionResult.get(Keys.WORK_DIR);
        } else {
            dir = new File(actionContext.arguments().get("dir"));
        }
        try (Git git = Git.open(dir)) {
            GitCheckOutConfig gitCheckOutConfig = actionContext.args().get(GitKeys.GIT_CHECK_OUT_CONFIG_KEY);
            String newBranch = actionContext.arguments().get("new");
            String branchName = actionContext.arguments().get("branch");
            if (gitCheckOutConfig.isCreate()) {
                Ref call = git.checkout().setCreateBranch(true).setName(gitCheckOutConfig.getBranch()).call();
            } else {
                Ref call = git.checkout().setName(gitCheckOutConfig.getBranch()).call();
            }
        } catch (IOException | GitAPIException e) {
            e.printStackTrace();
        }
        ActionResult actionResult = new ActionResult();
        actionResult.put("dir", dir);
        return actionResult;
    }

    @Override
    public String name() {
        return "git-checkout";
    }
}
