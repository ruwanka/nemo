package com.aptkode.nemo.git;

import com.aptkode.nemo.api.*;
import com.aptkode.nemo.api.argument.Args;
import com.aptkode.nemo.api.key.Keys;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.net.URI;
import java.nio.file.Path;
import java.util.Optional;

public class GitCloneAction implements Action {

    public ActionResult execute(ActionContext context) {
        try {
            Args args = context.args();
            File directory = getDirectory(args);
            Git call = Git.cloneRepository()
                    .setCredentialsProvider(
                            new UsernamePasswordCredentialsProvider(args.get(Keys.USERNAME), args.get(Keys.PASSWORD)))
                    .setDirectory(directory)
                    .setURI(args.get(Keys.URL))
                    .call();
            call.close();
            ActionResult actionResult = new ActionResult();
            actionResult.put(Keys.WORK_DIR, directory);
            return actionResult;
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
        return new ActionResult();
    }

    File getDirectory(Args args) {
        Optional<File> file = args.getOptional(Keys.BASE_DIR);
        String subDirectoryPath = getSubDirectoryPath(args.get(Keys.URL));
        return file.map(value -> Path.of(value.getPath(), subDirectoryPath).toFile()).orElseGet(() -> new File(subDirectoryPath));
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
