package com.aptkode.nemo.maven;

import com.aptkode.nemo.api.ActionContext;
import com.aptkode.nemo.api.Arguments;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MavenUpdateDependencyActionTest {
    @Test
    void updateDependencyTest() throws IOException, XmlPullParserException {
        MavenUpdateDependencyAction action = new MavenUpdateDependencyAction();

        Map<String, String> args = new HashMap<>();
        args.put("pomFile", "src/test/resources/pom-with-no-property.xml");
        args.put("groupId", "org.springframework.cloud");
        args.put("artifactId", "spring-cloud-commons-dependencies");
        args.put("version", "0.0.1");
        ActionContext actionContext = new ActionContext(new Arguments(args));

        action.execute(actionContext);

        Dependency matchedDependency = null;
        MavenXpp3Reader xpp3Reader = new MavenXpp3Reader();
        Model model = xpp3Reader.read(new FileInputStream("src/test/resources/pom-with-no-property.xml"));
        for (Dependency dependency : model.getDependencies()) {
            if(dependency.getGroupId().equals("org.springframework.cloud") && dependency.getArtifactId().equals("spring-cloud-commons-dependencies")){
                matchedDependency = dependency;
                break;
            }
        }
        assertNotNull(matchedDependency);
        assertEquals("0.0.1", matchedDependency.getVersion());

        matchedDependency.setVersion("${spring-cloud-commons.version}");
        MavenXpp3Writer writer = new MavenXpp3Writer();
        writer.write(new FileOutputStream("src/test/resources/pom-with-no-property.xml"), model);
    }
}
