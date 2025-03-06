package utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    // Method to capture a full-page screenshot using Shutterbug and save it as a file
    public static String captureScreenshot(WebDriver driver) {

        BufferedImage screenshot = Shutterbug.shootPage(driver).getImage();

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String destinationPath = "./screenshots/Screenshot_" + timestamp + ".png";

        File destination = new File(destinationPath);
        try {
            File screenshotsDir = new File("./screenshots");
            if (!screenshotsDir.exists()) {
                screenshotsDir.mkdir();
            }

            File tempFile = new File(destinationPath);
            ImageIO.write(screenshot, "PNG", tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return destinationPath;
    }

    // Method to capture screenshot and attach it to Extent report
    public static void captureAndAttachScreenshot(WebDriver driver, ExtentTest test, String message) {
        String screenshotPath = captureScreenshot(driver);
        if (screenshotPath != null) {
            String absoluteScreenshotPath = new File(screenshotPath).getAbsolutePath();
            ExtentTestManager.getTest().log(Status.INFO, message,
                    MediaEntityBuilder.createScreenCaptureFromPath(absoluteScreenshotPath).build());
        }
    }
}


