package com.lazerycode.selenium.tests.agm;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.lazerycode.selenium.tests.TestTemplate;
import com.lazerycode.selenium.pageobjectsfactory.pageobject.agm.AGMLoginPageObject;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Instant;

//import static com.lazerycode.selenium.listeners.ExtentListener.extent;

public class FSSuite extends TestTemplate {


    AGMLoginPageObject loginPage;
    //test = extent.createTest("Verify Login Test");

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


        //test.log(Status.INFO, "FS Suite Test Started");


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

//    @Test(
//            description = "Creating Repository",
//            priority = 0
//    )
    public void createRepository() throws Exception {

        Thread.sleep(2000);

        loginPage.clickNewbutton();


        loginPage.enteReponame(
                "Test-case-2"
                        + System.currentTimeMillis()
        );


        Thread.sleep(2000);


        loginPage.clickCreaterepo();


        Thread.sleep(2000);


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


        Thread.sleep(2000);


        loginPage.clickselectRepo();


        Thread.sleep(2000);


        loginPage.clickmain();


        Thread.sleep(2000);


        loginPage.clickviewAll();


        Thread.sleep(2000);


        loginPage.clicknewBranch();


        Thread.sleep(2000);


        String branchName =
                "Test1"
                        + System.currentTimeMillis();


        loginPage.enteBranchname(
                branchName
        );


        Thread.sleep(2000);


        loginPage.clickcreateNewbranch();


        Thread.sleep(2000);


        System.out.println(
                "******************************************"
        );


        System.out.println(                "Branch Created Successfully"
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
        loginPage.clickhomeButton();
        Thread.sleep(5000);
        loginPage.clickselectDelrepo();
        Thread.sleep(5000);

        loginPage.clicksettings();

        Thread.sleep(2000);
        loginPage.clickdeleteRepo();
        Thread.sleep(2000);

        loginPage.clickconfirmDelrepo();

        Thread.sleep(2000);
        loginPage.clickconfirmTextdelete();
        Thread.sleep(2000);

        loginPage.enterRepo(
                "bhanukeerthi1988-bit/Testcase-1"
        );


        loginPage.clickconfirmDelrepotxt();


//        loginPage.clickverifyEmail();
    }
  //  @Test(description = "Create a Private Repository", priority = 3)
    public void createprivateRepo() throws Exception {
        loginPage.clickhomeButton();
        Thread.sleep(5000);
        loginPage.clickNewbutton();
        Thread.sleep(2000);
        loginPage.enteReponame("Testcase-1" + System.currentTimeMillis());
        Thread.sleep(2000);
        loginPage.clickselectRepotype();
        Thread.sleep(2000);
        loginPage.clickprivateRepo();
        Thread.sleep(2000);
        loginPage.clickCreaterepo();
        Thread.sleep(2000);
    }
    @DataProvider(name = "repoTypes")
    public Object[][] repoTypes() {
        return new Object[][]{
                {"TestCase-Public-" + System.currentTimeMillis(), "public"},
                {"TestCase-Private-" + System.currentTimeMillis(), "private"}
        };
    }
    @Test(dataProvider = "repoTypes", description = "Create Public & Private Repositories")
    public void createRepository(String repoName, String repoType) throws Exception {

        loginPage.clickhomeButton();

        loginPage.clickNewbutton();

        loginPage.enteReponame(repoName);

        if (repoType.equalsIgnoreCase("private")) {

            loginPage.clickselectRepotype();
            loginPage.clickprivateRepo();
        }

        loginPage.clickCreaterepo();

        System.out.println("******************************************");
        System.out.println(repoType.toUpperCase() + " Repository Created Successfully → " + repoName);
        System.out.println("******************************************");
    }


}