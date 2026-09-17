package com.lazerycode.selenium.tests;

import com.lazerycode.selenium.DriverBase;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Properties;

public class TestTemplate extends DriverBase {

    public static Properties properties = new Properties();

    String propertyFile = System.getProperty("myproperty");

    String invFile = System.getProperty("user.dir")
            + File.separator + "src"
            + File.separator + "test"
            + File.separator + "java"
            + File.separator + "com"
            + File.separator + "lazerycode"
            + File.separator + "selenium"
            + File.separator + "inv"
            + File.separator + propertyFile;

    @BeforeSuite
    public void beforeSuite() throws Exception {

        BufferedReader reader = new BufferedReader(new FileReader(invFile));

        properties.load(reader);

        reader.close();

        System.out.println("WORKING!! " + properties.getProperty("url"));
    }

    @AfterSuite
    public void afterSuite() throws Exception {

        properties.store(new FileOutputStream(invFile), null);
    }
}