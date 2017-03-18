package com.harshild.gradle.plugin.metadata

import org.junit.Test

import static org.junit.Assert.*

/**
 * Created by harshild on 10/03/17.
 */
class MetadataExtractorTest {
    @Test
    void itShouldCheckIfTheTextISURL() throws Exception {
        assertFalse(MetadataExtractor.isURL("Test a"))
        assertTrue(MetadataExtractor.isURL("a.com"))
        assertTrue(MetadataExtractor.isURL("https://a.com"))
        assertTrue(MetadataExtractor.isURL("http://a.com"))
        assertTrue(MetadataExtractor.isURL("https://www.a.com"))
        assertTrue(MetadataExtractor.isURL("http://www.a.com"))
    }

}