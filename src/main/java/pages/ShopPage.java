package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShopPage {
    WebDriver driver;
    WebDriverWait wait;

    public ShopPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addProductToCart(String productName, int quantity) {
        for (int i = 0; i < quantity; i++) {
            WebElement productBuyButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//h4[contains(text(),'" + productName + "')]/following-sibling::p/a[contains(text(),'Buy')]")));
            productBuyButton.click();
        }
    }

    public void goToCart() {
        WebElement cartLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-cart")));
        cartLink.click();
    }
}

