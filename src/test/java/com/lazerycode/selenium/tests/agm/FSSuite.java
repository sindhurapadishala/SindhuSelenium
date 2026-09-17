package com.lazerycode.selenium.tests.agm;

import com.lazerycode.selenium.tests.TestTemplate;
import com.lazerycode.selenium.pageobjectsfactory.pageobject.agm.AGMLoginPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FSSuite extends TestTemplate {


    AGMLoginPageObject loginPage;


    // =========================================================
    // Setup
    // =========================================================

    @BeforeClass
    public void Setup() throws Exception {

        loginPage =
                new AGMLoginPageObject();


        // Get properties

        String url =
                properties.getProperty("url");


        String username =
                properties.getProperty("username");


        String password =
                properties.getProperty("password");


        System.out.println(
                "******************************************"
        );


        System.out.println(
                "URL      : " + url
        );


        System.out.println(
                "Username : " + username
        );


        System.out.println(
                "******************************************"
        );


        // =====================================================
        // Launch Browser
        // =====================================================

        loginPage.launchBrowser(url);


        // =====================================================
        // Login
        // =====================================================

        loginPage.enterUsername(username);

        loginPage.enterPassword(password);

        loginPage.clickSignin();
    }


    // =========================================================
    // Test 1 - Create Repository
    // =========================================================

    @Test(
            description = "Creating Repository",
            priority = 0
    )
    public void createRepository() throws Exception {


        loginPage.clickNewbutton();


        loginPage.enteReponame(
                "Test-case-2"
                        + System.currentTimeMillis()
        );


        Thread.sleep(5000);


        loginPage.clickCreaterepo();


        Thread.sleep(5000);


        System.out.println(
                "******************************************"
        );


        System.out.println(
                "Repository Created Successfully"
        );


        System.out.println(
                "******************************************"
        );
    }


    // =========================================================
    // Test 2 - Create Branch
    // =========================================================

    @Test(
            description = "Creating a New Branch",
            priority = 1
    )
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


        String branchName =
                "Test1"
                        + System.currentTimeMillis();


        loginPage.enteBranchname(
                branchName
        );


        Thread.sleep(5000);


        loginPage.clickcreateNewbranch();


        Thread.sleep(5000);


        System.out.println(
                "******************************************"
        );


        System.out.println(
                "Branch Created Successfully"
        );


        System.out.println(
                "******************************************"
        );
    }


    // =========================================================
    // Test 3 - Delete Repository
    // =========================================================

    @Test(
            description = "Deleting the Repository",
            priority = 2
    )
    public void deleteRepo() throws Exception {


        loginPage.clickselectDelrepo();


        loginPage.clicksettings();


        loginPage.clickdeleteRepo();


        loginPage.clickconfirmDelrepo();


        loginPage.clickconfirmTextdelete();


        loginPage.enterRepo(
                "bhanukeerthi1988-bit/Bhanu"
        );


        loginPage.clickconfirmDelrepotxt();


        loginPage.clickverifyEmail();
    }
}