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

public class MavenAddPropertyAction extends BaseMavenAction {

    @Override
    public ActionResult execute(ActionContext context) {
        File pomFile = getPomFile(context);
        try {
            String property = context.arguments().get("property");
            String value = context.arguments().get("value");
            String pom = Files.readString(pomFile.toPath());
            Optional<String> replacedPom = replacePropertyIfExists(pom, property, value);
            if (replacedPom.isEmpty()) {
                pom = addProperty(pom, property, value);
            } else {
                pom = replacedPom.get();
            }
            FileUtils.writeStringToFile(pomFile, pom, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ActionResult actionResult = new ActionResult();
        actionResult.put("pomFile", pomFile);
        return actionResult;
    }

    private Optional<String> replacePropertyIfExists(String pom, String property, String value) {
        XMLEventReader eventReader = null;
        try {
            eventReader = XMLHelper.createXMLEventReader(pom);
            while (eventReader.hasNext()) {
                XMLEvent xmlEvent = eventReader.nextEvent();
                if (xmlEvent.isStartElement() && xmlEvent.asStartElement().getName().getLocalPart().equals(property)) {
                    XMLEvent propertyEnd = eventReader.nextEvent();
                    XMLEvent propEndStart = eventReader.nextEvent();
                    return Optional.of(pom.substring(0, propertyEnd.getLocation().getCharacterOffset()) +
                            value +
                            pom.substring(propEndStart.getLocation().getCharacterOffset()));
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } finally {
            XMLHelper.closeXMLEventReader(eventReader);
        }
        return Optional.empty();
    }

    private String addProperty(String pom, String property, String value) {
        XMLEventReader eventReader = null;
        try {
            eventReader = XMLHelper.createXMLEventReader(pom);
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement() && event.asStartElement().getName().getLocalPart().equals("properties")) {
                    XMLEvent propertiesEndEvent = eventReader.nextEvent();
                    XMLEvent firstPropertyEvent = eventReader.nextEvent();
                    int firstPropStart = firstPropertyEvent.getLocation().getCharacterOffset();
                    int endIndex = propertiesEndEvent.getLocation().getCharacterOffset() +
                            firstPropertyEvent.getLocation().getColumnNumber();
                    return pom.substring(0, endIndex) +
                            "<" + property + ">" + value + "</" + property + ">\n\t\t" +
                            pom.substring(firstPropStart);
                }
            }
            throw new IllegalArgumentException("The POM has no properties node.");
        } catch (XMLStreamException | IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            XMLHelper.closeXMLEventReader(eventReader);
        }
        return pom;
    }

    @Override
    public String name() {
        return "mvn-add-property";
    }
}
