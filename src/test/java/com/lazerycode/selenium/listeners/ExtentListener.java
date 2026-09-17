package com.lazerycode.selenium.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.lazerycode.selenium.DriverBase;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentListener implements ITestListener {

    private static ExtentReports extent;

    private static ThreadLocal<ExtentTest> extentTest =
            new ThreadLocal<ExtentTest>();


    // =========================================================
    // EXTENT REPORT
    // =========================================================

    private static ExtentReports getExtentReports() {

        if (extent == null) {

            String timeStamp =
                    new SimpleDateFormat("yyyyMMdd_HHmmss")
                            .format(new Date());

            String reportDirectory =
                    System.getProperty("user.dir")
                            + File.separator
                            + "test-output";

            File directory =
                    new File(reportDirectory);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            String reportPath =
                    reportDirectory
                            + File.separator
                            + "ExtentReport_"
                            + timeStamp
                            + ".html";


            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter(reportPath);


            sparkReporter.config()
                    .setDocumentTitle(
                            "AGM Automation Test Report"
                    );

            sparkReporter.config()
                    .setReportName(
                            "AGM Selenium Automation Report"
                    );


            extent = new ExtentReports();

            extent.attachReporter(sparkReporter);


            // =================================================
            // SYSTEM INFORMATION
            // =================================================

            extent.setSystemInfo(
                    "Project",
                    "AGM Automation"
            );

            extent.setSystemInfo(
                    "Automation",
                    "Selenium WebDriver"
            );

            extent.setSystemInfo(
                    "Framework",
                    "TestNG"
            );

            extent.setSystemInfo(
                    "Browser",
                    "Chrome"
            );

            extent.setSystemInfo(
                    "Java Version",
                    System.getProperty("java.version")
            );

            extent.setSystemInfo(
                    "Operating System",
                    System.getProperty("os.name")
            );
        }

        return extent;
    }


    // =========================================================
    // SUITE START
    // =========================================================

    @Override
    public void onStart(ITestContext context) {

        getExtentReports();

        System.out.println(
                "=========================================="
        );

        System.out.println(
                "EXTENT REPORT STARTED"
        );

        System.out.println(
                "=========================================="
        );
    }


    // =========================================================
    // TEST START
    // =========================================================

    @Override
    public void onTestStart(ITestResult result) {

        String testName =
                result.getMethod()
                        .getMethodName();

        String description =
                result.getMethod()
                        .getDescription();

        ExtentTest test;

        if (description != null
                && !description.trim().isEmpty()) {

            test =
                    getExtentReports()
                            .createTest(
                                    testName,
                                    description
                            );

        } else {

            test =
                    getExtentReports()
                            .createTest(testName);
        }


        extentTest.set(test);


        extentTest.get().log(
                Status.INFO,
                "Test Started: " + testName
        );


        System.out.println(
                "TEST STARTED: " + testName
        );
    }


    // =========================================================
    // PASS
    // =========================================================

    @Override
    public void onTestSuccess(ITestResult result) {

        String testName =
                result.getMethod()
                        .getMethodName();


        /*
         * Screenshot is added at the END of the test.
         */

        captureAndAttachScreenshot(
                testName,
                "PASS",
                "Test Passed - Final Screenshot",
                Status.PASS
        );


        System.out.println(
                "TEST PASSED: " + testName
        );
    }


    // =========================================================
    // FAIL
    // =========================================================

    @Override
    public void onTestFailure(ITestResult result) {

        String testName =
                result.getMethod()
                        .getMethodName();


        /*
         * First show the failure.
         */

        extentTest.get().log(
                Status.FAIL,
                "Test Failed: " + testName
        );


        /*
         * Show the actual exception / stack trace.
         */

        if (result.getThrowable() != null) {

            extentTest.get().fail(
                    result.getThrowable()
            );
        }


        /*
         * Screenshot is added DIRECTLY AFTER
         * the failure/exception.
         */

        captureAndAttachScreenshot(
                testName,
                "FAIL",
                "Failure Screenshot",
                Status.FAIL
        );


        System.out.println(
                "TEST FAILED: " + testName
        );
    }


    // =========================================================
    // SKIP
    // =========================================================

    @Override
    public void onTestSkipped(ITestResult result) {

        String testName =
                result.getMethod()
                        .getMethodName();


        /*
         * Show skip information.
         */

        extentTest.get().log(
                Status.SKIP,
                "Test Skipped: " + testName
        );


        /*
         * Show skip reason if available.
         */

        if (result.getThrowable() != null) {

            extentTest.get().skip(
                    result.getThrowable()
            );
        }


        /*
         * Screenshot at the END of skipped test.
         */

        captureAndAttachScreenshot(
                testName,
                "SKIP",
                "Skipped Test Screenshot",
                Status.SKIP
        );


        System.out.println(
                "TEST SKIPPED: " + testName
        );
    }


    // =========================================================
    // SCREENSHOT
    // =========================================================

    private static void captureAndAttachScreenshot(
            String testName,
            String status,
            String screenshotName,
            Status extentStatus
    ) {

        try {

            WebDriver driver =
                    DriverBase.getDriver();


            // =================================================
            // CHECK DRIVER
            // =================================================

            if (driver == null) {

                extentTest.get().log(
                        Status.WARNING,
                        "Driver is null. Screenshot cannot be captured."
                );

                return;
            }


            if (!(driver instanceof TakesScreenshot)) {

                extentTest.get().log(
                        Status.WARNING,
                        "Driver does not support screenshots."
                );

                return;
            }


            TakesScreenshot screenshotDriver =
                    (TakesScreenshot) driver;


            // =================================================
            // BASE64 SCREENSHOT
            // =================================================

            String base64Screenshot =
                    screenshotDriver.getScreenshotAs(
                            OutputType.BASE64
                    );


            // =================================================
            // ADD SCREENSHOT DIRECTLY TO LOG
            // =================================================

            extentTest.get().log(
                    extentStatus,
                    screenshotName,
                    MediaEntityBuilder
                            .createScreenCaptureFromBase64String(
                                    base64Screenshot
                            )
                            .build()
            );


            // =================================================
            // ALSO SAVE PNG FILE
            // =================================================

            saveScreenshot(
                    screenshotDriver,
                    testName,
                    status
            );


            System.out.println(
                    screenshotName
                            + " added to Extent Report."
            );


        } catch (Exception e) {

            try {

                extentTest.get().log(
                        Status.WARNING,
                        "Screenshot capture failed: "
                                + e.getMessage()
                );

            } catch (Exception ignored) {
            }


            System.out.println(
                    "Screenshot capture failed: "
                            + e.getMessage()
            );
        }
    }


    // =========================================================
    // SAVE PHYSICAL SCREENSHOT
    // =========================================================

    private static void saveScreenshot(
            TakesScreenshot screenshotDriver,
            String testName,
            String status
    ) {

        try {

            String screenshotDirectory =
                    System.getProperty("user.dir")
                            + File.separator
                            + "test-output"
                            + File.separator
                            + "screenshots";


            File directory =
                    new File(screenshotDirectory);


            if (!directory.exists()) {

                directory.mkdirs();
            }


            String timeStamp =
                    new SimpleDateFormat(
                            "yyyyMMdd_HHmmss_SSS"
                    ).format(new Date());


            String fileName =
                    status
                            + "_"
                            + testName
                            + "_"
                            + timeStamp
                            + ".png";


            File screenshotFile =
                    new File(
                            screenshotDirectory
                                    + File.separator
                                    + fileName
                    );


            screenshotDriver
                    .getScreenshotAs(OutputType.FILE)
                    .renameTo(screenshotFile);


        } catch (Exception e) {

            System.out.println(
                    "Unable to save screenshot: "
                            + e.getMessage()
            );
        }
    }


    // =========================================================
    // SUITE FINISH
    // =========================================================

    @Override
    public void onFinish(ITestContext context) {

        if (extent != null) {

            extent.flush();
        }


        System.out.println(
                "=========================================="
        );

        System.out.println(
                "EXTENT REPORT GENERATED"
        );

        System.out.println(
                "Location:"
        );

        System.out.println(
                System.getProperty("user.dir")
                        + File.separator
                        + "test-output"
        );

        System.out.println(
                "=========================================="
        );
    }
}