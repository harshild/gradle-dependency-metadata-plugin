package com.harshild.gradle.plugin.xml.generator

import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller
/**
 * Created by harshild on 2/8/2017.
 */
class XMLGenerator<T> {
    void generateXML(T t, File file){
        file.createNewFile()
        JAXBContext jaxbContext=JAXBContext.newInstance(t.class)
        Marshaller marshaller=jaxbContext.createMarshaller()
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
        def fileWrite = new FileOutputStream(file)
        marshaller.marshal(t, fileWrite)
        fileWrite.close()
    }
}
