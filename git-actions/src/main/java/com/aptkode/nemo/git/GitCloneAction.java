package com.aptkode.nemo.git;

import com.aptkode.nemo.api.*;
import com.aptkode.nemo.api.Keys;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.net.URI;

public class GitCloneAction implements Action {

    public ActionResult execute(ActionContext context) {
        try {
            Arguments args = context.arguments();
            File directory = getDirectory(args);
            Git call = Git.cloneRepository()
                    .setCredentialsProvider(
                            new UsernamePasswordCredentialsProvider(args.get(Keys.USERNAME), args.get(Keys.PASSWORD)))
                    .setDirectory(directory)
                    .setURI(args.get(Keys.URL))
                    .call();
            call.close();
            ActionResult actionResult = new ActionResult();
            actionResult.put(Keys.DIR, directory);
            return actionResult;
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
        return new ActionResult();
    }

    private File getDirectory(Arguments args) {
        return new File(args.get("baseDir", "") + getSubDirectoryPath(args.get("url")));
    }

    String getSubDirectoryPath(String url) {
        URI uri = URI.create(url);
        String path = uri.getPath();
        if (path.endsWith(".git")) {
            path = path.replace(".git", "");
        }
        return path;
    }

    public String name() {
        return "git-clone";
    }
}
