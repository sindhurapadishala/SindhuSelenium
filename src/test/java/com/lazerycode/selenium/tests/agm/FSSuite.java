package com.lazerycode.selenium.tests.agm;

import com.lazerycode.selenium.DriverBase;
import com.lazerycode.selenium.pageobjectsfactory.pageobject.agm.AGMLoginPageObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FSSuite extends DriverBase {


    AGMLoginPageObject loginPage;


    @BeforeClass
    public void Setup() throws Exception {
         loginPage = new AGMLoginPageObject();
        // Launch browser and navigate to a specific URL
        loginPage.launchBrowser("https://github.com/login");
//        Thread.sleep(2000);
        loginPage.enterUsername("bhanukeerthi1988@gmail.com");
        loginPage.enterPassword("Eshita@2707");
        loginPage.clickSignin();
    }
     @Test(description = "Creating Repository", priority = 0)

     public void createRepository() throws Exception {
         // Instantiate the page object

         //Thread.sleep(60000);
         loginPage.clickNewbutton();
         loginPage.enteReponame("Test-case-2" + System.currentTimeMillis());
         Thread.sleep(5000);
         loginPage.clickCreaterepo();
         Thread.sleep(5000);

        System.out.println("******************************************");
        System.out.println("Welcome to the Selenium 4 AGM Automation Suite");
        System.out.println("******************************************");
    }
    @Test(description = "Creating a New Branch", priority = 1)
    public void createBranch() throws Exception {

        loginPage.clickhomeButton();
        Thread.sleep(5000);
        loginPage.clickselectRepo();
        Thread.sleep(5000);
        loginPage.clickmain();
        Thread.sleep(5000);
        loginPage.clickviewAll();
        Thread.sleep(5000);
        loginPage.clicknewBranch();
        Thread.sleep(5000);
        String branchName="Test1"+System.currentTimeMillis();
        loginPage.enteBranchname(branchName);
        Thread.sleep(5000);
        loginPage.clickcreateNewbranch();
        Thread.sleep(5000);
        System.out.println("******************************************");
        System.out.println("Welcome to the Selenium 4 AGM Automation Suite");
        System.out.println("******************************************");
    }
    @Test(description = "Deleting the Repository", priority = 2)
    public void deleteRepo() throws Exception {

        loginPage.clickselectDelrepo();
        loginPage.clicksettings();
        loginPage.clickdeleteRepo();
        loginPage.clickconfirmDelrepo();
        loginPage.clickconfirmTextdelete();
        loginPage.enterRepo("bhanukeerthi1988-bit/Bhanu");
        loginPage.clickconfirmDelrepotxt();
        loginPage.clickverifyEmail();


    }

}