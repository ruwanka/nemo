package com.aptkode.nemo.maven;

import org.codehaus.stax2.XMLInputFactory2;

import javax.xml.stream.XMLInputFactory;

public class XMLInputFactoryProvider {

    private final static XMLInputFactory factory = createInputFactory();

    public static XMLInputFactory get(){
        return factory;
    }

    private static XMLInputFactory createInputFactory()
    {
        XMLInputFactory inputFactory = XMLInputFactory2.newInstance();
        inputFactory.setProperty( XMLInputFactory2.P_PRESERVE_LOCATION, true );
        return inputFactory;
    }

}
