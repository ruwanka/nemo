package com.aptkode.nemo.maven;

import com.aptkode.nemo.api.ActionContext;
import com.aptkode.nemo.api.ActionResult;
import org.apache.commons.io.FileUtils;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Optional;

public class MavenUpdateDependencyAction extends BaseMavenAction {
    @Override
    public ActionResult execute(ActionContext context) {
        String groupId = context.arguments().get("groupId");
        String artifactId = context.arguments().get("artifactId");
        String version = context.arguments().get("version");
        File pomFile = getPomFile(context);
        try {
            String pom = Files.readString(pomFile.toPath());
            XMLEventReader xmlEventReader = XMLHelper.createXMLEventReader(pom);
            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement() &&
                        xmlEvent.asStartElement().getName().getLocalPart().equals("groupId") &&
                        xmlEventReader.getElementText().equals(groupId)) {
                    xmlEventReader.nextEvent(); // end groupId
                    xmlEvent = xmlEventReader.nextEvent();
                    if (xmlEvent.isStartElement() &&
                            xmlEvent.asStartElement().getName().getLocalPart().equals("artifactId") &&
                            xmlEventReader.getElementText().equals(artifactId)) {
                        xmlEventReader.nextEvent(); // end artifactId
                        XMLEvent versionTagStartEvt = xmlEventReader.nextEvent();
                        if (versionTagStartEvt.isStartElement() && versionTagStartEvt.asStartElement().getName().getLocalPart().equals("version")) {
                            XMLEvent versionTagEndEvt = xmlEventReader.nextEvent();
                            XMLEvent endVersionTagStartEvt = xmlEventReader.nextEvent();
                            pom = pom.substring(0, versionTagEndEvt.getLocation().getCharacterOffset()) +
                                    version +
                                    pom.substring(endVersionTagStartEvt.getLocation().getCharacterOffset());
                        }
                    }
                }
            }
            FileUtils.writeStringToFile(pomFile, pom, Charset.defaultCharset());
        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }
        ActionResult actionResult = new ActionResult();
        actionResult.put("pomFile", pomFile);
        actionResult.put("dir", getBaseDir(pomFile, context));
        return actionResult;
    }

    private File getBaseDir(File pomFile, ActionContext context) {
        Optional<ActionResult> optionalActionResult = context.previousResult();
        if(optionalActionResult.isPresent()){
            ActionResult actionResult = optionalActionResult.get();
            if(actionResult.get("dir") != null){
                return actionResult.get("dir");
            } else{
                return pomFile.toPath().getParent().toFile();
            }
        }
        return null;
    }

    @Override
    public String name() {
        return "mvn-dependency-update";
    }
}
