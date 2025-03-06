package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
    private static final Map<Long, ExtentTest> testMap = new HashMap<>();
    private static ExtentReports extent;

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./extent-report.html");
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }

    public static void setExtent(ExtentReports extReport) {
        extent = extReport;
    }

    public static synchronized ExtentTest getTest() {
        return testMap.get(Thread.currentThread().getId());
    }

    public static synchronized ExtentTest startTest(String testName) {
        if (extent == null) {
            throw new IllegalStateException("ExtentReports is not initialized! Call ExtentTestManager.getInstance() first.");
        }

        ExtentTest test = extent.createTest(testName);
        testMap.put(Thread.currentThread().getId(), test);
        return test;
    }
}
