package com.lazerycode.selenium.tests;

import com.lazerycode.selenium.DriverBase;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Properties;

/**
 * Created by gvchaitanya on 09/11/17.
 */
public class TestTemplate extends DriverBase{
    public static Properties properties = new Properties();
    String propertyFile = System.getProperty("myproperty");
    String invFile = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator + "com"
            + File.separator + "lazerycode" + File.separator + "selenium" + File.separator + "inv" + File.separator + propertyFile;

    /**
     * Loads the inventory file for the test
     * @param context
     * @throws Exception
     */
    @BeforeSuite
    public void beforeSuite(ITestContext context) throws Exception{
       BufferedReader reader = new BufferedReader(new FileReader(invFile));
       properties.load(reader);
       System.out.println("WORKING!!" + properties.getProperty("url"));
    }

    @AfterSuite
    public void afterSuite(ITestContext context) throws Exception{
        properties.store(new FileOutputStream(invFile), null);
    }


}
