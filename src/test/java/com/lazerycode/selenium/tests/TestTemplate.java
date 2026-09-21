package com.lazerycode.selenium.tests;

import com.aventstack.extentreports.ExtentTest;
import com.lazerycode.selenium.DriverBase;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Properties;

public class TestTemplate extends DriverBase {

    protected ExtentTest extentTest;
    public static Properties properties =
            new Properties();


    // =========================================================
    // Property File
    // =========================================================

    String propertyFile =
            System.getProperty("myproperty");


    String invFile =
            System.getProperty("user.dir")
                    + File.separator
                    + "src"
                    + File.separator
                    + "test"
                    + File.separator
                    + "java"
                    + File.separator
                    + "com"
                    + File.separator
                    + "lazerycode"
                    + File.separator
                    + "selenium"
                    + File.separator
                    + "inv"
                    + File.separator
                    + propertyFile;


    // =========================================================
    // Load Properties
    // =========================================================

    @BeforeSuite
    public void beforeSuite() throws Exception {

        System.out.println(
                "=========================================="
        );

        System.out.println(
                "Loading Property File"
        );

        System.out.println(
                "=========================================="
        );


        BufferedReader reader =
                new BufferedReader(
                        new FileReader(invFile)
                );


        properties.load(reader);


        reader.close();


        System.out.println(
                "URL: "
                        + properties.getProperty("url")
        );


        System.out.println(
                "Properties loaded successfully."
        );
    }


    // =========================================================
    // Store Properties
    // =========================================================

    @AfterSuite
    public void afterSuite() throws Exception {

        properties.store(
                new FileOutputStream(invFile),
                null
        );


        System.out.println(
                "Properties saved successfully."
        );
    }
}