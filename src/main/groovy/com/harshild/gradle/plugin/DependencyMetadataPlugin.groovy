package com.harshild.gradle.plugin

import com.harshild.gradle.plugin.task.MetadataReportGeneratorTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin

/**
 * Created by W18NM36 on 2/6/2017.
 */
class DependencyMetadataPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        project.plugins.apply(JavaPlugin.class)
        project.task('generateDependencyMetadataReport', type: MetadataReportGeneratorTask,dependsOn: ':build')
    }
}

