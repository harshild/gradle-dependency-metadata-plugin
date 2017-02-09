package com.harshild.gradle.plugin.entity;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Created by harshild on 2/7/2017.
 */
public class Licenses
{
    private List<License> license;

    public Licenses() {
    }

    public Licenses(List<License> license) {
        this.license = license;
    }

    @XmlElement
    public List<License> getLicense ()
    {
        return license;
    }

    public void setLicense (List<License> license)
    {
        this.license = license;
    }

    @Override
    public String toString() {
        return "Licenses{" +
                "license=" + license +
                '}';
    }
}

