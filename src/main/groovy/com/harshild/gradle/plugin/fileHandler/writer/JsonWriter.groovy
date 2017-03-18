package com.harshild.gradle.plugin.fileHandler.writer

import com.harshild.gradle.plugin.fileHandler.FileWriter
import groovy.json.JsonOutput

/**
 * Created by harshild on 17/03/17.
 */
class JsonWriter<T> implements FileWriter<T>{
    @Override
    void generate(T xmlRootProject, File outputFile) {
        outputFile.createNewFile()
        outputFile.write(JsonOutput.toJson(xmlRootProject))
    }
}
