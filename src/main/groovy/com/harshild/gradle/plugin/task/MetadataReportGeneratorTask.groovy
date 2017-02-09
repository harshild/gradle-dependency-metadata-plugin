package com.harshild.gradle.plugin.task

import com.harshild.gradle.plugin.dependency.PomFileManager
import com.harshild.gradle.plugin.entity.Dependencies
import com.harshild.gradle.plugin.entity.Dependency
import com.harshild.gradle.plugin.entity.Project
import com.harshild.gradle.plugin.xml.generator.XMLGenerator
import com.harshild.gradle.plugin.xml.parser.XMLParser
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Created by harshild on 2/6/2017.
 */
class MetadataReportGeneratorTask extends DefaultTask {

    public MetadataReportGeneratorTask(){}
    public static final String INFO_MESSAGE = "\nGenerating a report with Metadata information for all Dependencies"

    @TaskAction
    def run(){
        Map<String,String> dependencyPomURLMap = new PomFileManager(project).getPomFileURL()
        def parser = new XMLParser<Project>()
        List<Project> parsedList = parser.parseXMLs(dependencyPomURLMap,Project.class)
        File file = new File(project.reportsDir.path+"/dependency-metadata.xml")
        file.getParentFile().mkdirs();
        file.createNewFile();

        List<Dependency> dep = new ArrayList<>();
        parsedList.each{ proj -> dep.add(proj.toDependency())}

        new XMLGenerator<Dependencies>().generateXML(new Dependencies(dep),file)
        println(INFO_MESSAGE)
    }

}
