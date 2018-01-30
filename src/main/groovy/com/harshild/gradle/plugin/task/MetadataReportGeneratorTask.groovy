package com.harshild.gradle.plugin.task

import com.harshild.gradle.plugin.extension.ReportExtension
import com.harshild.gradle.plugin.fileio.writer.JsonWriter
import com.harshild.gradle.plugin.metadata.MetadataExtractor
import com.harshild.gradle.plugin.models.pom.generate.Dependencies
import com.harshild.gradle.plugin.models.pom.marshaller.Marshaller
import com.harshild.gradle.plugin.models.pom.parse.XmlRootProject
import com.harshild.gradle.plugin.fileio.writer.XMLWriter
import jdk.nashorn.internal.ir.debug.JSONWriter
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Created by harshild on 2/6/2017.
 */
class MetadataReportGeneratorTask extends DefaultTask {

    MetadataReportGeneratorTask(){}
    public static final String INFO_MESSAGE = "\nGenerating a report with Metadata information for all Dependencies"

    @TaskAction
    run(){
        List<XmlRootProject> parsedList = MetadataExtractor.generateReportData(project)
        Dependencies dep = Marshaller.marshall(parsedList,
                project.name,
                project.version,
                project.group)

        def outputFormat = project.extensions.report.outputFormat
        if(outputFormat.equalsIgnoreCase('json') || outputFormat.equalsIgnoreCase('xml')) {
            def fileWriter = outputFormat.equalsIgnoreCase('json') ?
                    new JsonWriter<Dependencies>() :new XMLWriter<Dependencies>()

            File file = new File(project.reportsDir.path + "/dependency-metadata."+ outputFormat)
            file.getParentFile().mkdirs()
            file.createNewFile()
            fileWriter.generate(dep, file)
            println(INFO_MESSAGE)
        }else {
            println("INAVLID OUTPUT FORMAT")
        }
    }


}
