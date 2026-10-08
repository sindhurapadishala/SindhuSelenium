package com.lazerycode.selenium.tests.agm;

import com.lazerycode.selenium.pageobjectsfactory.pageobject.agm.ActionPageObject;

import com.lazerycode.selenium.tests.TestTemplate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.lazerycode.selenium.tests.TestTemplate.properties;

//import static com.lazerycode.selenium.listeners.ExtentListener.extent;

public class ActionSuite extends TestTemplate {


    ActionPageObject homePage;

    @BeforeClass
    public void Setup() throws Exception {

        homePage =
                new ActionPageObject();


        // Get properties

        String url =
                properties.getProperty("url");


        System.out.println(
                "URL      : " + url
        );

        homePage.launchBrowser(url);
    }

    @Test(description = "Mouse Actions : Click", priority = 0)

    public void ClickMouseActions() throws Exception {

        Thread.sleep(2000);

        homePage.clickallMenu();
        Thread.sleep(2000);
        homePage.clickcloseMenu();
        homePage.clickamazonhomePage();
        homePage.clickearlyPrimedeals();
        homePage.clickamazonhomePage();

    }

    @Test(description = "Mouse Actions : Hover", priority = 1)
    public void HoverAction() throws Exception {
        homePage.clickamazonhomePage();
        homePage.hoveronchangeCountry();
        homePage.clickchangeCountry();

    }
}