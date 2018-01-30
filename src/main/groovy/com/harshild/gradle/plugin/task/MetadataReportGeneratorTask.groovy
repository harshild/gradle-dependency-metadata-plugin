package com.harshild.gradle.plugin.task

import com.harshild.gradle.plugin.fileio.FileWriter
import com.harshild.gradle.plugin.fileio.writer.JsonWriter
import com.harshild.gradle.plugin.fileio.writer.XMLWriter
import com.harshild.gradle.plugin.metadata.MetadataExtractor
import com.harshild.gradle.plugin.models.pom.generate.Dependencies
import com.harshild.gradle.plugin.models.pom.marshaller.Marshaller
import com.harshild.gradle.plugin.models.pom.parse.XmlRootProject
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

        def fileWriter = getWriter(outputFormat)

        if(fileWriter!=null) {
            File file = new File(project.reportsDir.path + "/dependency-metadata."+ outputFormat)
            file.getParentFile().mkdirs()
            file.createNewFile()
            fileWriter.generate(dep, file)
            println(INFO_MESSAGE)
        }
    }

    private FileWriter<Dependencies> getWriter(outputFormat) {
        switch (outputFormat.toLowerCase()) {
            case 'json': return new JsonWriter<Dependencies>()
                break
            case 'xml': return new XMLWriter<Dependencies>()
                break
            default: println("INVALID OUTPUT FORMAT")
                return null
                break

        }
    }


}
