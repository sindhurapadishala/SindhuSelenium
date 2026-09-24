
package com.lazerycode.selenium.tests.agm;

import com.lazerycode.selenium.tests.TestTemplate;
import com.lazerycode.selenium.pageobjectsfactory.pageobject.agm.AGMLoginPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FSSuite extends TestTemplate {

    AGMLoginPageObject loginPage;

    // =========================================================
    // SETUP
    // =========================================================

    @BeforeClass
    public void Setup() throws Exception {

        loginPage = new AGMLoginPageObject();

        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        System.out.println(
                "******************************************"
        );

        System.out.println("URL      : " + url);
        System.out.println("Username : " + username);

        System.out.println(
                "******************************************"
        );

        // Launch Browser

        loginPage.launchBrowser(url);

        // Login

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickSignin();

        System.out.println("Browser launched and login completed.");
    }

    // =========================================================
    // TEST 1 - CREATE REPOSITORY
    // =========================================================

    @Test(
            description = "Creating Repository",
            priority = 0
    )
    public void createRepository() throws Exception {

        logInfo("Starting repository creation test");

        loginPage.clickNewbutton();
        logInfo("Clicked New Repository button");

        String repoName =
                "Test-case-2" + System.currentTimeMillis();

        loginPage.enteReponame(repoName);

        logInfo("Entered repository name: " + repoName);

        Thread.sleep(5000);

        loginPage.clickCreaterepo();
        logInfo("Clicked Create Repository button");

        Thread.sleep(5000);

        logPass("Repository Created Successfully");

    }

    // =========================================================
    // TEST 2 - CREATE BRANCH
    // =========================================================

    @Test(
            description = "Creating a New Branch",
            priority = 1
    )
    public void createBranch() throws Exception {

        logInfo("Starting branch creation test");

        loginPage.clickhomeButton();
        logInfo("Clicked Home button");

        Thread.sleep(5000);

        loginPage.clickselectRepo();
        logInfo("Selected repository");

        Thread.sleep(5000);

        loginPage.clickmain();
        logInfo("Opened main branch");

        Thread.sleep(5000);

        loginPage.clickviewAll();
        logInfo("Clicked View All branches");

        Thread.sleep(5000);

        loginPage.clicknewBranch();
        logInfo("Clicked New Branch button");

        Thread.sleep(5000);

        String branchName =
                "Test1" + System.currentTimeMillis();

        loginPage.enteBranchname(branchName);

        logInfo("Entered branch name: " + branchName);

        Thread.sleep(5000);

        loginPage.clickcreateNewbranch();

        logInfo("Clicked Create New Branch");

        Thread.sleep(5000);

        logPass("Branch Created Successfully");

    }

    // =========================================================
    // TEST 3 - DELETE REPOSITORY
    // =========================================================

    @Test(
            description = "Deleting the Repository",
            priority = 2
    )
    public void deleteRepo() throws Exception {

        logInfo("Starting repository deletion test");

        loginPage.clickselectDelrepo();
        logInfo("Selected repository for deletion");

        loginPage.clicksettings();
        logInfo("Opened repository settings");

        loginPage.clickdeleteRepo();
        logInfo("Clicked Delete Repository");

        loginPage.clickconfirmDelrepo();
        logInfo("Clicked initial delete confirmation");

        loginPage.clickconfirmTextdelete();
        logInfo("Opened final delete confirmation");

        loginPage.enterRepo(
                "bhanukeerthi1988-bit/Bhanu"
        );

        logInfo("Entered repository confirmation text");

        loginPage.clickconfirmDelrepotxt();
        logInfo("Confirmed repository deletion");

        loginPage.clickverifyEmail();

        logPass("Repository deletion confirmation completed");

    }
}