package com.harshild.gradle.plugin

import com.harshild.gradle.plugin.constants.CommandConstants
import com.harshild.gradle.plugin.task.MetadataReportGeneratorTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin

/**
 * Created by harshild on 2/6/2017.
 */
class DependencyMetadataPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        project.plugins.apply(JavaPlugin.class)
        project.task(CommandConstants.GENERATE_DEPENDENCY_METADATA_REPORT, type: MetadataReportGeneratorTask,dependsOn: ':build')
    }
}

