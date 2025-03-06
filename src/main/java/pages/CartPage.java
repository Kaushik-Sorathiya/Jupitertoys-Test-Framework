package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class CartPage {
    WebDriver driver;
    WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public double getProductPrice(String productName) {
        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[contains(text(),'" + productName + "')]/following-sibling::td[1]")));
        return Double.parseDouble(priceElement.getText().replace("$", "").trim());
    }

    public int getProductQuantity(String productName) {
        WebElement quantityElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[contains(text(),'" + productName + "')]/following-sibling::td[2]/input")));
        return Integer.parseInt(quantityElement.getAttribute("value").trim());
    }

    public double getProductSubtotal(String productName) {
        WebElement subtotalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[contains(text(),'" + productName + "')]/following-sibling::td[3]")));
        return Double.parseDouble(subtotalElement.getText().replace("$", "").trim());
    }

    public double getTotalAmount() {
        WebElement totalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[contains(text(),'Total')]")));
        String totalText = totalElement.getText().trim();
        String totalNumeric = totalText.replaceAll("[^0-9.]", ""); // Remove non-numeric characters
        return Double.parseDouble(totalNumeric);
    }

}
