package com.lazerycode.selenium.pageobjectsfactory.pageobject.agm;

import com.lazerycode.selenium.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

public class AGMLoginPageObject extends BasePageObject {


    @FindBy(xpath = "//input[@id='login_field']")
    private WebElement username;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement SigninButton;

    @FindBy(xpath = "//h2[text()='Home']")
    private WebElement Home;

    @FindBy(xpath = "//div[@role='navigation']//span[text()='New']")
    private WebElement NewButton;

    @FindBy(xpath = "//input[@data-component='input' and @id='repository-name-input']")
    private WebElement repoName;


    @FindBy(xpath = "//span[text()='Create repository']")
    private WebElement createRepo;

    @FindBy(xpath = "//a[text()='Test-case-2']/parent::strong")
    private WebElement Testcase;

    @FindBy(xpath = "//span[text()='Open menu']/following-sibling::a")
    private WebElement homeButton;

    @FindBy(xpath = "//div[@class='dashboard-sidebar']//span[text()='/']/parent::a[@data-hovercard-url='/bhanukeerthi1988-bit/Keerthi/hovercard']")
    private WebElement selectRepo;

    @FindBy(xpath = "//span[text()='main']")
    private WebElement main;

    @FindBy(xpath = "//div[text()='branches']")
    private WebElement viewAll;

    @FindBy(xpath = "//span[text()='New branch']")
    private WebElement newBranch;

    @FindBy(xpath = "//div[@data-component='Dialog.Body']//input[@data-component='input']")
    private WebElement branchName;

    @FindBy(xpath = "//span[text()='Create new branch']")
    private WebElement createNewbranch;

    @FindBy(xpath = "//div[@class='dashboard-sidebar']//span[text()='/']/parent::a[@data-hovercard-url='/bhanukeerthi1988-bit/Bhanu/hovercard']")
    private WebElement selectDelrepo;

    @FindBy(xpath = "//a[@data-tab-item='settings']")
    private WebElement settings;

    @FindBy(xpath = "//button[@data-show-dialog-id='repo-delete-menu-dialog']")
    private WebElement deleteRepo;

    @FindBy(xpath = "//span[text()='I want to delete this repository']")
    private WebElement confirmDelrepo;

    @FindBy(xpath = "//span[text()='I have read and understand these effects']")
    private WebElement confirmTextdelete;

    @FindBy(xpath = "//input[@data-repo-nwo='bhanukeerthi1988-bit/Bhanu']")
    private WebElement reenterRepo;

    @FindBy(xpath = "//button[@data-test-selector='repo-delete-proceed-button']//span[text()='Delete this repository']/parent::span")
    private WebElement confirmDelrepotxt;

    @FindBy(xpath = "//span[text()='Verify via email']/parent::span")
    private WebElement verifyEmail;

    @FindBy(xpath = "//button[@data-component='ActionMenu.Button' and @id='visibility-anchor-button']")
    private WebElement selectRepotype;

    public AGMLoginPageObject() throws Exception {
        super();
    }

    /**
     * Navigates to the specified URL.
     * The browser type is controlled by the -Dbrowser Maven property.
     */
    public void launchBrowser(String url) {
        driver.get(url);
    }

    public void loginToAGM(String user, String pwd, String url) {
        driver.get("https://" + url);
        username.clear();
        username.sendKeys(user);
        password.clear();
        password.sendKeys(pwd);
        SigninButton.click();
    }

    public void enterUsername(String uname) {

        username.sendKeys(uname);
        Reporter.log("Enter the Username " + uname);

    }

    public void enterPassword(String pwd) {

        password.sendKeys(pwd);
        Reporter.log("Enter the Password " + pwd);

    }

    public void clickSignin() {

        SigninButton.click();
        Reporter.log("Cicked on the signin Button ");
        boolean isElementDisplayed = driver.findElement(By.xpath("//h2[text()='Home']")).isDisplayed();

// Passes if isElementDisplayed is true; fails if false
        Assert.assertTrue(isElementDisplayed);
        if (Home.isDisplayed()) {
            Reporter.log("Logged in Succesfully", true);
        } else {
            Reporter.log("Logged in Failed", true);
        }
        Assert.assertTrue(Home.isDisplayed());
    }

    public void clickNewbutton() {

        NewButton.click();
        Reporter.log("Cicked on the New Button ");

    }

    public void enteReponame(String rName) {

        repoName.sendKeys(rName);
        Reporter.log("Enter the new Repository Name ");

    }

    public void clickCreaterepo() {

        createRepo.click();
        Reporter.log("Cicked on the create repository button ");
//        boolean isElementDisplayed = driver.findElement(By.xpath("//a[text()='Test-case-2']/parent::strong")).isDisplayed();
//
//// Passes if isElementDisplayed is true; fails if false
//        Assert.assertTrue(isElementDisplayed);
//        if(Testcase.isDisplayed()){
//            Reporter.log("Repository Opened Succesfully", true);
//        }
//        else{
//            Reporter.log("Repository Open Failed", true);
//        }
//        Assert.assertTrue(Testcase.isDisplayed());

    }

    public void clickhomeButton() {

        homeButton.click();
        Reporter.log("Cicked on the Home Button ");
    }

    public void clickselectRepo() {

        selectRepo.click();
        Reporter.log("Cicked on the selected repository ");
//        boolean isElementDisplayed = driver.findElement(By.xpath("//span[text()='main']")).isDisplayed();
//
//        Assert.assertTrue(isElementDisplayed);
//
//        if(main.isDisplayed()){
//            Reporter.log("Repository Opened Succesfully", true);
//        }
//        else{
//            Reporter.log("Repository Open Failed", true);
//        }
//        Assert.assertTrue(main.isDisplayed());
    }

    public void clickmain() {

        main.click();
        Reporter.log("Cicked on the Main ");
    }

    public void clickviewAll() {

        viewAll.click();
        Reporter.log("Cicked on the View all Braches ");
    }

    public void clicknewBranch() {

        newBranch.click();
        Reporter.log("Cicked on the New Branch ");
    }

    public void enteBranchname(String bName) {

        branchName.sendKeys(bName);
        Reporter.log("Enter the new Branch Name ");

    }

    public void clickcreateNewbranch() {

        createNewbranch.click();
        Reporter.log("Cicked on the Create New Branch ");
    }

    public void clickselectDelrepo() {

        selectDelrepo.click();
        Reporter.log("Click the Seleted Repository to delete ");
    }

    public void clicksettings() {

        settings.click();
        Reporter.log("Click the Settings button ");
    }
    public void clickdeleteRepo() {

        deleteRepo.click();
        Reporter.log("Click the Delete Repository button ");
    }


    public void clickconfirmDelrepo() {

        confirmDelrepo.click();
        Reporter.log("Click the Confirm Delete Repository button ");
    }

    public void clickconfirmTextdelete() {

        confirmTextdelete.click();
        Reporter.log("Click the Confirm Delete Repository text ");
    }
    public void enterRepo(String rrName) {

        reenterRepo.sendKeys(rrName);
        Reporter.log("ReEnter the Repo Name to confirm ");

    }

    public void clickconfirmDelrepotxt() {

        confirmDelrepotxt.click();
        Reporter.log("Click the Confirm Delete Repository text ");
    }

    public void clickverifyEmail() {

        verifyEmail.click();
        Reporter.log("Click the Verify Email button ");
    }
    public void clickselectRepotype(){

        selectRepotype.click();
        Reporter.log("click Select  the Repo type");
    }
}