package com.lazerycode.selenium.pageobjectsfactory.pageobject.agm;

import com.lazerycode.selenium.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AGMLoginPageObject extends BasePageObject {

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "signInBtn")
    private WebElement loginButton;

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
        driver.get("http://" + url);
        username.clear();
        username.sendKeys(user);
        password.clear();
        password.sendKeys(pwd);
        loginButton.click();
    }
}