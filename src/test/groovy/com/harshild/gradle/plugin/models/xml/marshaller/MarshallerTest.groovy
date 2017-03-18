package com.harshild.gradle.plugin.models.xml.marshaller

import com.harshild.gradle.plugin.models.xml.generate.Dependencies
import com.harshild.gradle.plugin.models.xml.generate.Dependency
import com.harshild.gradle.plugin.models.xml.parse.ProjectLicense
import com.harshild.gradle.plugin.models.xml.parse.ProjectLicenses
import com.harshild.gradle.plugin.models.xml.parse.XmlRootProject
import org.junit.Test

import java.util.Arrays

import static org.junit.Assert.assertEquals

/**
 * Created by harshild on 2/10/2017.
 */
class MarshallerTest {

    @Test
    void itShouldBeAbleToMarshallProjectToDependency(){
        XmlRootProject project =  new XmlRootProject("group",
                "artifact",
                "Test",
                "version",
                "description",
                new ProjectLicenses(Arrays.asList(
                        new ProjectLicense("",""),
                        new ProjectLicense("","")
                )),
                "http://testUrl")
        Dependency dependency = Marshaller.marshall(project)

        assertEquals("artifact",dependency.getArtifactId())

    }

    @Test
    void itShouldBeAbleToMarshallProjectsToDependencies(){
        XmlRootProject project1 =  new XmlRootProject("group",
                "artifact",
                "Test",
                "version",
                "description",
                new ProjectLicenses(Arrays.asList(
                        new ProjectLicense("",""),
                        new ProjectLicense("","")
                )),
                "http://testUrl")

        XmlRootProject project2 =  new XmlRootProject("group",
                "artifact",
                "Test",
                "version",
                "description",
                new ProjectLicenses(Arrays.asList(
                        new ProjectLicense("",""),
                        new ProjectLicense("","")
                )),
                "http://testUrl")
        Dependencies dependencies = Marshaller.marshall(project1,project2)

        assertEquals(2,dependencies.getDependency().size())

    }
}