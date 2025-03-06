package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private By contactLink = By.linkText("Contact");
    private By shoppingLink = By.linkText("Start Shopping »");

    public void goToContactPage() {
        driver.findElement(contactLink).click();
    }

    public void goToShopPage() {
        driver.findElement(shoppingLink).click();
    }
}
