package com.lazerycode.selenium.tests.agm;

import com.lazerycode.selenium.DriverBase;
import com.lazerycode.selenium.pageobjectsfactory.pageobject.agm.AGMLoginPageObject;
import org.testng.annotations.Test;

public class FSSuite extends DriverBase {

    @Test(description = "Simplified Welcome Test")
    public void welcomeTest() throws Exception {
        // Instantiate the page object
        AGMLoginPageObject loginPage = new AGMLoginPageObject();

        // Launch browser and navigate to a specific URL
        loginPage.launchBrowser("https://github.com/");
        String reponame="sindhu";
        //a[@data-testid="repo-name-link"].getText();
        System.currentTimeMillis();
        System.out.println(reponame);
        System.out.println("******************************************");
        System.out.println("Welcome to the Selenium 4 AGM Automation Suite");
        System.out.println("******************************************");
    }
}