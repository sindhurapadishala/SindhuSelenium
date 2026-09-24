
package com.lazerycode.selenium.tests;

import com.lazerycode.selenium.DriverBase;
import com.lazerycode.selenium.listeners.ExtentListener;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Properties;

public class TestTemplate extends DriverBase {

    public static Properties properties = new Properties();

    // =========================================================
    // PROPERTY FILE
    // =========================================================

    String propertyFile = System.getProperty("myproperty");

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
    // LOAD PROPERTIES
    // =========================================================

    @BeforeSuite
    public void beforeSuite() throws Exception {

        System.out.println(
                "=========================================="
        );

        System.out.println("Loading Property File");

        System.out.println(
                "=========================================="
        );

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(invFile))) {

            properties.load(reader);
        }

        System.out.println(
                "URL: " + properties.getProperty("url")
        );

        System.out.println("Properties loaded successfully.");

        System.out.println(
                "=========================================="
        );
    }

    // =========================================================
    // EXTENT LOGGING METHODS
    // =========================================================

    protected void logInfo(String message) {

        System.out.println("[INFO] " + message);

        if (ExtentListener.getExtentTest() != null) {
            ExtentListener.getExtentTest().info(message);
        }
    }

    protected void logPass(String message) {

        System.out.println("[PASS] " + message);

        if (ExtentListener.getExtentTest() != null) {
            ExtentListener.getExtentTest().pass(message);
        }
    }

    protected void logFail(String message) {

        System.out.println("[FAIL] " + message);

        if (ExtentListener.getExtentTest() != null) {
            ExtentListener.getExtentTest().fail(message);
        }
    }

    protected void logWarning(String message) {

        System.out.println("[WARNING] " + message);

        if (ExtentListener.getExtentTest() != null) {
            ExtentListener.getExtentTest().warning(message);
        }
    }

    // =========================================================
    // STORE PROPERTIES
    // =========================================================

    @AfterSuite
    public void afterSuite() throws Exception {

        try (FileOutputStream outputStream =
                     new FileOutputStream(invFile)) {

            properties.store(outputStream, null);
        }

        System.out.println("Properties saved successfully.");
    }
}