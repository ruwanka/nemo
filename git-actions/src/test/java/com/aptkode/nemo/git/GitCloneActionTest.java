package com.aptkode.nemo.git;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GitCloneActionTest {
    @Test
    void getSubDirectoryPathTest(){
        String subDirectoryPath = new GitCloneAction()
                .getSubDirectoryPath("https://github.com/ruwanka/mvn-dependnecy-updater.git");
        assertEquals("/ruwanka/mvn-dependnecy-updater", subDirectoryPath);
    }
}
