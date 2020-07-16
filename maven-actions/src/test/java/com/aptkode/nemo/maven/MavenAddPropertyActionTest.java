package com.aptkode.nemo.maven;

import com.aptkode.nemo.api.ActionContext;
import com.aptkode.nemo.api.Arguments;
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

public class MavenAddPropertyActionTest {
    @Test
    void addPropertyTest() throws IOException, XmlPullParserException {
        MavenAddPropertyAction action = new MavenAddPropertyAction();

        Map<String, String> args = new HashMap<>();
        args.put("pomFile", "G:/nemo/ruwanka/spring-cloud-config/pom.xml");
        args.put("property", "test.prop");
        args.put("value", "test-value");
        ActionContext actionContext = new ActionContext(new Arguments(args));

        action.execute(actionContext);

        MavenXpp3Reader xpp3Reader = new MavenXpp3Reader();
        Model model = xpp3Reader.read(new FileInputStream("G:/nemo/ruwanka/spring-cloud-config/pom.xml"));
        String value = (String) model.getProperties().get("test.prop");
        assertEquals("test-value", value);

        model.getProperties().remove("test.prop");
        MavenXpp3Writer writer = new MavenXpp3Writer();
        writer.write(new FileOutputStream("G:/nemo/ruwanka/spring-cloud-config/pom.xml"), model);
    }

    @Test
    void replacePropertyTest() throws IOException, XmlPullParserException {
        MavenAddPropertyAction action = new MavenAddPropertyAction();

        Map<String, String> args = new HashMap<>();
        args.put("pomFile", "src/test/resources/pom-with-no-property.xml");
        args.put("property", "existing.prop");
        args.put("value", "changed-value");
        ActionContext actionContext = new ActionContext(new Arguments(args));

        action.execute(actionContext);

        MavenXpp3Reader xpp3Reader = new MavenXpp3Reader();
        Model model = xpp3Reader.read(new FileInputStream("src/test/resources/pom-with-no-property.xml"));
        String value = (String) model.getProperties().get("existing.prop");
        assertEquals("changed-value", value);

        model.getProperties().setProperty("existing.prop", "value");
        MavenXpp3Writer writer = new MavenXpp3Writer();
        writer.write(new FileOutputStream("src/test/resources/pom-with-no-property.xml"), model);
    }
}
