package com.harshild.gradle.plugin.xml.parser

import org.xml.sax.InputSource
import org.xml.sax.XMLReader
import org.xml.sax.helpers.XMLFilterImpl

import javax.xml.bind.JAXBContext
import javax.xml.bind.Unmarshaller
import javax.xml.parsers.SAXParserFactory
import javax.xml.transform.sax.SAXSource

/**
 * Created by harshild on 2/6/2017.
 */
class XMLParser<T> {
    T parseXML(File file,Class<T> t){
        def (Unmarshaller unmarshaller, SAXSource source) = createParserInstance(t, file)
        unmarshaller.unmarshal(source,t).getValue();
    }

    private List createParserInstance(Class<T> t, File file) {
        Unmarshaller unmarshaller = createJAXBUnmarshaller(t)
        XMLNamespaceFilter xmlFilter = createNamespaceFilter(unmarshaller)
        SAXSource source = createSAXSource(file, xmlFilter)

        [unmarshaller, source]
    }

    private XMLReader createXMLReader() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.newSAXParser().getXMLReader();
    }

    private Unmarshaller createJAXBUnmarshaller(Class<T> t) {
        JAXBContext jaxbContext = JAXBContext.newInstance(t);
        jaxbContext.createUnmarshaller();
    }

    private SAXSource createSAXSource(File file, XMLNamespaceFilter xmlFilter) {
        InputStream inStream = new FileInputStream(file);
        new SAXSource(xmlFilter, new InputSource(inStream));
    }

    private XMLNamespaceFilter createNamespaceFilter( Unmarshaller jaxbUnmarshaller) {
        XMLReader reader = createXMLReader()
        XMLFilterImpl xmlFilter = new XMLNamespaceFilter(reader);
        reader.setContentHandler(jaxbUnmarshaller.getUnmarshallerHandler());
        xmlFilter
    }

}
