package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import pages.ContactPage;
import pages.HomePage;
import utils.ExtentTestManager;
import utils.ScreenshotUtil;

public class TestCase1 extends BaseTest {

    @Test
    public void TestCase1() {

        ExtentTestManager.startTest("Test case 1:");
        var test = ExtentTestManager.getTest();

        HomePage homePage = new HomePage(driver);
        homePage.goToContactPage();
        test.log(Status.INFO, "Navigated to Contact Page");

        ContactPage contactPage = new ContactPage(driver);
        contactPage.clickSubmit(); // Click Submit without filling fields
        test.log(Status.INFO, "Clicked Submit without filling mandatory fields");

        // Verify if error messages are displayed
        boolean errorsDisplayed = contactPage.areErrorMessagesDisplayed();
        Assert.assertTrue(errorsDisplayed, "Error messages should be displayed");
        test.log(Status.PASS, "Error messages are displayed as expected");

        // Capture and attach screenshot after verifying error messages
        ScreenshotUtil.captureAndAttachScreenshot(driver, test, "Screenshot: Verify if error messages are displayed");

        contactPage.populateMandatoryFields("John Doe","johndoe@example.com","This is a test message."); // Populate mandatory fields
        test.log(Status.INFO, "Populated mandatory fields");

        // Verify that the error messages are gone
        boolean errorsGone = contactPage.areErrorsGone();
        Assert.assertTrue(errorsGone, "Error messages should be gone after filling mandatory fields");
        test.log(Status.PASS, "Errors are gone after populating mandatory fields");

        // Capture and attach screenshot after verifying that errors are gone
        ScreenshotUtil.captureAndAttachScreenshot(driver, test, "Screenshot: Validate that the error messages are gone");
    }
}
