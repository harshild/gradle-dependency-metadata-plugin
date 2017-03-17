package com.harshild.gradle.plugin.fileHandler

/**
 * Created by harshild on 18/03/17.
 */
interface FileWriter<T> {
    void generate(T xmlRootProject, File outputFile)
}
