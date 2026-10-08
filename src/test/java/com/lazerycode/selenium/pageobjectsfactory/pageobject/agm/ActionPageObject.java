package com.lazerycode.selenium.pageobjectsfactory.pageobject.agm;

import com.lazerycode.selenium.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;

public class ActionPageObject extends BasePageObject {


    @FindBy(xpath = "//a[text()='Early Prime Deals']")
    private WebElement earlyPrimedeals;

    @FindBy(xpath = "//a[@id='nav-hamburger-menu']")
    private WebElement allMenu;

    @FindBy(xpath = "//div[@aria-label='Close menu']")
    private WebElement closeMenu;

    @FindBy(xpath = "//a[@aria-label='Amazon']")
    private WebElement amazonhomePage;

    @FindBy(xpath = "//a[@class='nav-a nav-a-2 icp-link-style-2']")
    private WebElement changeCountry;

    @FindBy(xpath = "//a[text()='Sign in']")
    private WebElement signIn;


    public ActionPageObject() throws Exception {
        super();
    }

    /**
     * Navigates to the specified URL.
     * The browser type is controlled by the -Dbrowser Maven property.
     */
    public void launchBrowser(String url) {
        driver.get(url);
    }

    public void clickamazonhomePage() {

        amazonhomePage.click();
        Reporter.log("Cicked on the Home Page ");
    }

    public void clickearlyPrimedeals() {

        earlyPrimedeals.click();
        Reporter.log("Cicked on the Early Prime deals ");
    }
    public void clickallMenu() {

        allMenu.click();
        Reporter.log("Cicked on the All Menu ");
    }

    public void clickcloseMenu() {

        closeMenu.click();
        Reporter.log("Cicked on the Close Menu ");
    }

    public void hoveronchangeCountry() {

        WebElement menu = wait.until(ExpectedConditions.visibilityOf(changeCountry));
        actions.moveToElement(changeCountry).perform();
    }

    public void clickchangeCountry() {

        WebElement menu = wait.until(ExpectedConditions.visibilityOf(changeCountry));
        actions.moveToElement(changeCountry).click().perform();
    }
    public void scrollToSignIn() {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(signIn));
        actions.scrollToElement(signIn).perform();
    }
}