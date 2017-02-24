**Dependency Metadata Plugin**

This a gradle plugin that helps you get Metadata information for all your dependencies and generates a report for it.
***
**Usage**

Update you `build.gradle` with following code
```
buildscript {
    repositories {
		mavenCentral()
    }

    dependencies {
		classpath("com.harshild.gradle:dependency-metadata-plugin:0.1")
    }
}

apply plugin: 'com.harshild.dependency-metadata'
```
***
**Tasks**

`generateDependencyMetadataReport` 

Generate an XML report under build/reports as dependency-metadata.xml
This contains dependency related metadata information

****
**License**

MIT