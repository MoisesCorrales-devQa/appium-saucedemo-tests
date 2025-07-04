package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {

    private final By proceedToCheckoutBtn = AppiumBy.androidUIAutomator("new UiSelector().text(\"Products\")");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean productosVisibles() {
        return isVisible(proceedToCheckoutBtn);
    }

}
