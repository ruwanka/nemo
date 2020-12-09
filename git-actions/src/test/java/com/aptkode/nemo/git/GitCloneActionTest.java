package com.aptkode.nemo.git;

import com.aptkode.nemo.api.argument.Args;
import com.aptkode.nemo.api.argument.Argument;
import com.aptkode.nemo.api.argument.FileArgument;
import com.aptkode.nemo.api.argument.StringArgument;
import com.aptkode.nemo.api.key.Keys;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GitCloneActionTest {
    @Test
    void getSubDirectoryPathTest(){
        String subDirectoryPath = new GitCloneAction()
                .getSubDirectoryPath("https://github.com/ruwanka/mvn-dependnecy-updater.git");
        assertEquals("/ruwanka/mvn-dependnecy-updater", subDirectoryPath);
    }

    @Test
    void getDirectoryTest(){
        Argument<File> fileArgument = new FileArgument();
        fileArgument.setKey(Keys.BASE_DIR.key());
        fileArgument.setValue(new File("G:\\somepath"));

        Argument<String> stringArgument = new StringArgument();
        stringArgument.setKey(Keys.URL.key());
        stringArgument.setValue("https://github.com/ruwanka/mvn-dependnecy-updater.git");

        Args args = new Args();
        args.put(fileArgument);
        args.put(stringArgument);

        File directory = new GitCloneAction().getDirectory(args);
        assertEquals("G:\\somepath\\ruwanka\\mvn-dependnecy-updater", directory.toString());
    }
}
