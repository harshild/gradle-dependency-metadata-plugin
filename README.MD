**Dependency Metadata Plugin**
[![Build Status](https://travis-ci.org/harshild/gradle-dependency-metadata-plugin.svg?branch=master)](https://travis-ci.org/harshild/gradle-dependency-metadata-plugin) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.harshild.gradle/dependency-metadata-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.harshild.gradle/dependency-metadata-plugin)

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

**Details Available**

* Name
* Artifact Id
* Group Id
* Version
* Homepage URL
* Description
* License [ License Type , License URL ]

These details are fetched from POM and then merged with details from Manifest.

***Merge Strategy***

* For array fields like License any detail found is appended
* For other fields if details not found in POM only then Manifest data is put in the report

****

**Tasks**

`generateDependencyMetadataReport` 

Generate an XML report under build/reports as dependency-metadata.xml
This contains dependency related metadata information

****

**Configurations**

```
report{

}
``` 

|  Configuration | Description | Possible Values |Default Value| 
|---|---|---|---|
|includeManifestData |includes or excludes usage of Manifest.MF for metadata information | true / false | true |
|outputFormat | set the output format | xml / json | xml |

****

**License**

MIT

***Dependencies***

Junit - EPL 1.0 - TestCompile