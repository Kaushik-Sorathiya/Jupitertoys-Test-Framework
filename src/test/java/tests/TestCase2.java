package tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ContactPage;
import pages.HomePage;
import utils.ExtentTestManager;
import utils.ScreenshotUtil;

public class TestCase2 extends BaseTest {

    @DataProvider(name = "contactFormData")
    public Object[][] getContactFormData() {
        return new Object[][]{
                {"Alice", "alice@example.com", "Hello, this is Alice!"},
                {"Bob", "bob@example.com", "Greetings from Bob."},
                {"Charlie", "charlie@example.com", "Charlie here, testing the form."},
                {"David", "david@example.com", "Hey, this is David’s message."},
                {"Emma", "emma@example.com", "Hi, Emma checking the submission."}
        };
    }

    @Test(dataProvider = "contactFormData")
    public void TestCase2(String forename, String email, String message) {

        ExtentTest test = ExtentTestManager.startTest("Test case 2: " + forename);

        HomePage homePage = new HomePage(driver);
        homePage.goToContactPage();
        test.log(Status.INFO, "Navigated to Contact Page");

        ContactPage contactPage = new ContactPage(driver);
        contactPage.populateMandatoryFields(forename, email, message);
        test.log(Status.INFO, "Populated mandatory fields - Name: " + forename + ", Email: " + email);

        ScreenshotUtil.captureAndAttachScreenshot(driver, test, "Screenshot: Populated mandatory fields");

        contactPage.clickSubmit();
        test.log(Status.INFO, "Clicked Submit button");

        boolean isSuccessMessageDisplayed = contactPage.isSubmissionSuccessful();
        Assert.assertTrue(isSuccessMessageDisplayed, "Success message should be displayed");
        test.log(Status.PASS, "Successfully submitted the contact form");

        ScreenshotUtil.captureAndAttachScreenshot(driver, test, "Screenshot: Successful form submission");
    }

}
