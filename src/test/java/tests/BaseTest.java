package tests;

import utils.ExtentTestManager;
import utils.ScreenshotUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.lang.reflect.Method;
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeSuite
    public void setupExtentReport() {
        ExtentReports extent = ExtentTestManager.getInstance();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("Jupiter Toys Test Execution");

        extent.attachReporter(sparkReporter);
        ExtentTestManager.setExtent(extent); // Ensure ExtentReports instance is available globally
    }

    @BeforeMethod
    public void setup(Method method) {

        WebDriverManager.chromedriver().clearResolutionCache().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://jupiter.cloud.planittesting.com");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        ExtentTest test = ExtentTestManager.getTest();

        if (test != null) {
            if (result.getStatus() == ITestResult.FAILURE) {
                ScreenshotUtil.captureAndAttachScreenshot(driver, test, "Test Failed: " + result.getThrowable());
                test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                test.log(Status.PASS, "Test Passed");
            } else if (result.getStatus() == ITestResult.SKIP) {
                test.log(Status.SKIP, "Test Skipped");
            }
        }

        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void tearDownExtentReport() {
        ExtentTestManager.getInstance().flush();
    }
}
