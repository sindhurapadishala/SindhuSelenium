package com.lazerycode.selenium.listeners;

import com.aventstack.extentreports.*;
import com.lazerycode.selenium.core.ExtentManager;
import org.testng.*;

public class ExtentListener implements ITestListener {
    private static ExtentReports extent = ExtentManager.getExtent();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public synchronized void onStart(ITestContext context) {
        System.out.println("Suite Started: " + context.getName());
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        extent.flush();
    }
}