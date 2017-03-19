package com.harshild.gradle.plugin.fileio.writer

import com.harshild.gradle.plugin.fileio.FileWriter

import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller
/**
 * Created by harshild on 2/8/2017.
 */
class XMLWriter<T>  implements FileWriter<T>{
    void generate(T t, File file){
        file.createNewFile()
        JAXBContext jaxbContext=JAXBContext.newInstance(t.class)
        Marshaller marshaller=jaxbContext.createMarshaller()
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
        def fileWrite = new FileOutputStream(file)
        marshaller.marshal(t, fileWrite)
        fileWrite.close()
    }
}
