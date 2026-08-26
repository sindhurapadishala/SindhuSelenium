package com.lazerycode.selenium;

import com.lazerycode.selenium.config.DriverFactory;
import com.lazerycode.selenium.listeners.ExtentListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Listeners({ExtentListener.class})
public class DriverBase {

    private static List<DriverFactory> webDriverThreadPool = Collections.synchronizedList(new ArrayList<DriverFactory>());
    private static ThreadLocal<DriverFactory> driverFactory;

    @BeforeSuite(alwaysRun = true)
    public static void instantiateDriverObject() {
        driverFactory = new ThreadLocal<DriverFactory>() {
            @Override
            protected DriverFactory initialValue() {
                DriverFactory factory = new DriverFactory();
                webDriverThreadPool.add(factory);
                return factory;
            }
        };
    }

    public static WebDriver getDriver() throws Exception {
        return driverFactory.get().getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public static void closeDriverObjects() throws Exception {
        // This closes the browser after every test method
        for (DriverFactory factory : webDriverThreadPool) {
            WebDriver driver = factory.getDriver();
            if (driver != null) {
                driver.quit();
            }
        }
    }
}