package com.harshild.gradle.plugin.fileHandler.reader;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;


/**
 * Created by harshild on 2/8/2017.
 */
public class XMLNamespaceFilter extends XMLFilterImpl {
    public XMLNamespaceFilter(XMLReader arg0) {
        super(arg0);
    }
    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes)
            throws SAXException {
        super.startElement("", localName, qName,
                attributes);
    }
}
