package com.harshild.gradle.plugin.task

import com.harshild.gradle.plugin.dependency.PomFileManager
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Created by harshild on 2/6/2017.
 */
class MetadataReportGeneratorTask extends DefaultTask {


    public static final String INFO_MESSAGE = "\nGenerating a report with Metadata information for all Dependencies"

    @TaskAction
    def run(){
        println(INFO_MESSAGE)
    }

}
