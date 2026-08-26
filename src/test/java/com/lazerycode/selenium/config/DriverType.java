//package com.lazerycode.selenium.config;
//
//import org.openqa.selenium.MutableCapabilities;
//import org.openqa.selenium.Proxy;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.edge.EdgeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.ie.InternetExplorerOptions;
//import org.openqa.selenium.safari.SafariDriver;
//import org.openqa.selenium.safari.SafariOptions;
//
//import java.util.HashMap;
//
//public enum DriverType implements DriverSetup {
//
//    FIREFOX {
//        public MutableCapabilities getDesiredCapabilities(Proxy proxySettings) {
//            FirefoxOptions options = new FirefoxOptions();
//            options.addPreference("browser.cache.disk.enable", false);
//            options.addPreference("browser.cache.memory.enable", false);
//            options.addPreference("browser.cache.offline.enable", false);
//            return addProxySettings(options, proxySettings);
//        }
//
//        public WebDriver getWebDriverObject(MutableCapabilities capabilities) {
//            return new FirefoxDriver((FirefoxOptions) capabilities);
//        }
//    },
//    CHROME {
//        public MutableCapabilities getDesiredCapabilities(Proxy proxySettings) {
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--start-maximized");
//            options.addArguments("--no-default-browser-check");
//            HashMap<String, Object> chromePreferences = new HashMap<>();
//            chromePreferences.put("profile.password_manager_enabled", false);
//            options.setExperimentalOption("prefs", chromePreferences);
//            options.setAcceptInsecureCerts(true);
//            return addProxySettings(options, proxySettings);
//        }
//
//        public WebDriver getWebDriverObject(MutableCapabilities capabilities) {
//            return new ChromeDriver((ChromeOptions) capabilities);
//        }
//    },
//    IE {
//        public MutableCapabilities getDesiredCapabilities(Proxy proxySettings) {
//            InternetExplorerOptions options = new InternetExplorerOptions();
//            options.introduceFlakinessByIgnoringSecurityDomains();
//            options.destructivelyEnsureCleanSession();
//            options.requireWindowFocus();
//            return addProxySettings(options, proxySettings);
//        }
//
//        public WebDriver getWebDriverObject(MutableCapabilities capabilities) {
//            return new InternetExplorerDriver((InternetExplorerOptions) capabilities);
//        }
//    },
//    EDGE {
//        public MutableCapabilities getDesiredCapabilities(Proxy proxySettings) {
//            EdgeOptions options = new EdgeOptions();
//            return addProxySettings(options, proxySettings);
//        }
//
//        public WebDriver getWebDriverObject(MutableCapabilities capabilities) {
//            return new EdgeDriver((EdgeOptions) capabilities);
//        }
//    },
//    SAFARI {
//        public MutableCapabilities getDesiredCapabilities(Proxy proxySettings) {
//            SafariOptions options = new SafariOptions();
//            return addProxySettings(options, proxySettings);
//        }
//
//        public WebDriver getWebDriverObject(MutableCapabilities capabilities) {
//            return new SafariDriver(options);
//        }
//    };
//
//    protected MutableCapabilities addProxySettings(MutableCapabilities options, Proxy proxySettings) {
//        if (null != proxySettings) {
//            options.setCapability("proxy", proxySettings);
//        }
//        return options;
//    }
//}