package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ShopPage;
import utils.ExtentTestManager;
import utils.ScreenshotUtil;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TestCase3 extends BaseTest {

    @Test
    public void TestCase3() {
        ExtentTest test = ExtentTestManager.startTest("Test Case 3: Cart Subtotal and Total Validation");

        HomePage homePage = new HomePage(driver);
        homePage.goToShopPage();
        test.log(Status.INFO, "Navigated to Shop Page");

        ShopPage shopPage = new ShopPage(driver);
        // Add products to the cart
        shopPage.addProductToCart("Stuffed Frog", 2);
        test.log(Status.INFO, "Added 2 Stuffed Frog to cart");

        shopPage.addProductToCart("Fluffy Bunny", 5);
        test.log(Status.INFO, "Added 5 Fluffy Bunny to cart");

        shopPage.addProductToCart("Valentine Bear", 3);
        test.log(Status.INFO, "Added 3 Valentine Bear to cart");
        ScreenshotUtil.captureAndAttachScreenshot(driver, test, "Screenshot: Added Products to Cart");
        shopPage.goToCart();
        test.log(Status.INFO, "Navigated to Cart Page");

        // Verify subtotal and total amounts
        CartPage cartPage = new CartPage(driver);

        // Validate individual subtotals
        double stuffedFrogPrice = cartPage.getProductPrice("Stuffed Frog");
        double fluffyBunnyPrice = cartPage.getProductPrice("Fluffy Bunny");
        double valentineBearPrice = cartPage.getProductPrice("Valentine Bear");

        double expectedStuffedFrogSubtotal = stuffedFrogPrice * 2;
        double expectedFluffyBunnySubtotal = fluffyBunnyPrice * 5;
        double expectedValentineBearSubtotal = valentineBearPrice * 3;

        Assert.assertEquals(cartPage.getProductSubtotal("Stuffed Frog"), expectedStuffedFrogSubtotal, "Stuffed Frog subtotal mismatch");
        Assert.assertEquals(cartPage.getProductSubtotal("Fluffy Bunny"), expectedFluffyBunnySubtotal, "Fluffy Bunny subtotal mismatch");
        Assert.assertEquals(cartPage.getProductSubtotal("Valentine Bear"), expectedValentineBearSubtotal, "Valentine Bear subtotal mismatch");

        test.log(Status.PASS, "Subtotal values are correct for all products");

        // Validate total amount
        double expectedTotal = expectedStuffedFrogSubtotal + expectedFluffyBunnySubtotal + expectedValentineBearSubtotal;
        double actualTotal = cartPage.getTotalAmount();
        Assert.assertEquals(actualTotal, expectedTotal, "Total amount mismatch");

        test.log(Status.PASS, "Total amount is correctly calculated");

        // Capture screenshot after validation
        ScreenshotUtil.captureAndAttachScreenshot(driver, test, "Screenshot: Cart Page Validation");
    }
}


