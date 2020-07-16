package com.aptkode.nemo.maven;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import java.io.StringReader;

public class XMLHelper {
    public static XMLEventReader createXMLEventReader(String pom) throws XMLStreamException {
        return XMLInputFactoryProvider.get().createXMLEventReader(new StringReader(pom));
    }

    public static void closeXMLEventReader(XMLEventReader eventReader) {
        if (eventReader != null) {
            try {
                eventReader.close();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        }
    }
}
