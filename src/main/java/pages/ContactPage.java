package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ContactPage {
    WebDriver driver;
    WebDriverWait wait;

    public ContactPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(30)); // Explicit wait
    }

    private By submitButton = By.xpath("//a[text()='Submit']");
    private By errorMessages = By.cssSelector(".help-inline");
    public By forenameField = By.id("forename");
    public By emailField = By.id("email");
    public By messageField = By.id("message");
    private By successMessage = By.cssSelector(".alert.alert-success");
    private By popupModal = By.cssSelector(".popup.modal");


    public void clickSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }
    // Method to verify if error messages are displayed
    public boolean areErrorMessagesDisplayed() {
        List<WebElement> errors = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(errorMessages));
        return errors.size() > 0;
    }

    // Method to populate mandatory fields
    public void populateMandatoryFields(String forename, String email, String message) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Ensure waits are properly initialized

        wait.until(ExpectedConditions.visibilityOfElementLocated(forenameField)).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(forenameField)).sendKeys(forename);

        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);

        wait.until(ExpectedConditions.visibilityOfElementLocated(messageField)).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageField)).sendKeys(message);
    }

    // Method to check if errors are gone after filling mandatory fields
    public boolean areErrorsGone() {
        List<WebElement> errors = driver.findElements(errorMessages);
        return errors.size() == 0; // Return true if no error messages are found
    }

    // Method to validate success message getting displayed
    public boolean isSubmissionSuccessful() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(popupModal));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
    }
}
