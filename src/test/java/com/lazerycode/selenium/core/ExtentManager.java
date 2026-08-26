package com.lazerycode.selenium.core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter; // Updated for newer versions
import java.io.File;

public class ExtentManager {

    private static ExtentReports extent;
    private static String logDirectory = "target" + File.separator + "reports";

    public static ExtentReports getExtent() {
        if (extent != null)
            return extent;

        File dir = new File(logDirectory);
        if (!dir.exists()) dir.mkdirs();

        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(logDirectory + File.separator + "TestReport.html");
        spark.config().setDocumentTitle("AGM Automation Report");
        spark.config().setReportName("Regression Results");

        extent.attachReporter(spark);
        return extent;
    }
}