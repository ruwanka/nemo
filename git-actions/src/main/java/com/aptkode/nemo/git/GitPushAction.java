package com.aptkode.nemo.git;

import com.aptkode.nemo.api.Action;
import com.aptkode.nemo.api.ActionContext;
import com.aptkode.nemo.api.ActionResult;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class GitPushAction implements Action {
    @Override
    public ActionResult execute(ActionContext context) {
        Optional<ActionResult> actionResult = context.previousResult();
        if (actionResult.isPresent()) {
            ActionResult result = actionResult.get();
            File dir = result.get("dir");
            if (dir.exists()) {
                try {
                    String username = context.arguments().get("username");
                    String password = context.arguments().get("password");
                    Git git = Git.open(dir);
                    git.push()
                            .setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password))
                            .call();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InvalidRemoteException e) {
                    e.printStackTrace();
                } catch (TransportException e) {
                    e.printStackTrace();
                } catch (GitAPIException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public String name() {
        return "git-push";
    }
}
