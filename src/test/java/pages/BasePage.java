package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void click(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    protected void sendKeys(By by, String text) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        el.clear();
        el.sendKeys(text);
    }

    protected String getText(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by)).getText();
    }

    protected boolean isVisible(By by) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }


    protected void scrollDown() {
        try {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"));
        } catch (Exception e) {
            // No more scroll, ignore
        }
    }

    protected void scrollToText(String text) {
        String uiAutomatorScroll = "new UiScrollable(new UiSelector().scrollable(true))" +
                ".scrollIntoView(new UiSelector().text(\"" + text + "\"))";
        driver.findElement(AppiumBy.androidUIAutomator(uiAutomatorScroll));
    }

    protected WebElement waitUntilVisible(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected WebElement waitUntilClickable(By by) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    protected WebElement waitUntilPresent(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected void sendKeysWithRetry(By by, String text, int maxRetries) {
        int attempts = 0;
        while (attempts < maxRetries) {
            try {
                WebElement el = waitUntilClickable(by);
                el.clear();
                el.sendKeys(text);
                return;
            } catch (StaleElementReferenceException e) {
                attempts++;
                try { Thread.sleep(500); } catch (InterruptedException ignored) {}
            }
        }
        throw new RuntimeException("No se pudo escribir en el elemento tras " + maxRetries + " intentos");
    }

}