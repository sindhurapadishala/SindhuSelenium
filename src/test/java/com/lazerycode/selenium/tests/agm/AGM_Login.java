//package com.lazerycode.selenium.tests.agm;
//
//import com.lazerycode.selenium.pageobjectsfactory.pageobject.agm.AGMLoginPageObject;
//import com.lazerycode.selenium.tests.TestTemplate;
//import org.testng.Assert;
//import org.testng.annotations.*;
//
//public class AGM_Login extends TestTemplate {
//    AGMLoginPageObject loginObj;
//
//    public AGM_Login() throws Exception {
//        super();
//    }
//
//    @BeforeTest
//    @Parameters({"browser","deploy"})
//    public void setup(@Optional("browser") String browser,@Optional("deploy")String deploy) throws Exception {
//        loginObj = new AGMLoginPageObject();
//
//        loginObj.launchBrowser(properties.getProperty("url"));
//        if(deploy.equalsIgnoreCase("yes")) {
//            try {
//                logger.info("This is freshly deployed AGM");
//                System.out.println("This is freshly deployed AGM");
//                loginObj.set_password(true, "admin", "password");
//            } catch (Exception e){
//                logger.debug("Exception caught in AGM login for fresh deployment " + e.getMessage());
//            }
//        } else {
//            Assert.fail("This AGM is not fresh deployment, still its asking for new password set!!");
//        }
//    }
//}
