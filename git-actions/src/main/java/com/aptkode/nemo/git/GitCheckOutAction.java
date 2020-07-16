package com.aptkode.nemo.git;

import com.aptkode.nemo.api.Action;
import com.aptkode.nemo.api.ActionContext;
import com.aptkode.nemo.api.ActionResult;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRefNameException;
import org.eclipse.jgit.api.errors.RefAlreadyExistsException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.lib.Ref;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class GitCheckOutAction implements Action {
    @Override
    public ActionResult execute(ActionContext actionContext) {
        Optional<ActionResult> optionalActionResult = actionContext.previousResult();
        File dir;
        if(optionalActionResult.isPresent()){
            ActionResult actionResult = optionalActionResult.get();
            dir = actionResult.get("dir");
        }else{
            dir = new File(actionContext.arguments().get("dir"));
        }
        try {
            Git git = Git.open(dir);
            String newBranch = actionContext.arguments().get("new");
            String branchName = actionContext.arguments().get("branch");
            if("true".equals(newBranch)){
                Ref call = git.checkout().setCreateBranch(true).setName(branchName).call();
            }else{
                Ref call = git.checkout().setName(branchName).call();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidRefNameException e) {
            e.printStackTrace();
        } catch (RefAlreadyExistsException e) {
            e.printStackTrace();
        } catch (RefNotFoundException e) {
            e.printStackTrace();
        } catch (GitAPIException e) {
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
