package com.lazerycode.selenium;

import com.lazerycode.selenium.config.DriverFactory;
import com.lazerycode.selenium.listeners.ExtentListener;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Listeners({ExtentListener.class})
public class DriverBase {

    private static List<DriverFactory> webDriverThreadPool =
            Collections.synchronizedList(
                    new ArrayList<DriverFactory>()
            );

    private static ThreadLocal<DriverFactory> driverFactory;


    // =========================================================
    // CREATE DRIVER FACTORY
    // =========================================================

    @BeforeSuite(alwaysRun = true)
    public static void instantiateDriverObject() {

        driverFactory =
                new ThreadLocal<DriverFactory>() {

                    @Override
                    protected DriverFactory initialValue() {

                        DriverFactory factory =
                                new DriverFactory();

                        webDriverThreadPool.add(factory);

                        return factory;
                    }
                };
    }


    // =========================================================
    // GET DRIVER
    // =========================================================

    public static WebDriver getDriver() throws Exception {

        return driverFactory
                .get()
                .getDriver();
    }


    // =========================================================
    // CLOSE BROWSER AFTER COMPLETE CLASS
    // =========================================================

    @AfterClass(alwaysRun = true)
    public static void closeDriverObjects() throws Exception {

        System.out.println(
                "=========================================="
        );

        System.out.println(
                "Closing browser after test class..."
        );

        System.out.println(
                "=========================================="
        );


        for (DriverFactory factory : webDriverThreadPool) {

            try {

                WebDriver driver =
                        factory.getDriver();

                if (driver != null) {

                    driver.quit();
                }

            } catch (Exception e) {

                System.out.println(
                        "Error while closing browser: "
                                + e.getMessage()
                );
            }
        }
    }
}