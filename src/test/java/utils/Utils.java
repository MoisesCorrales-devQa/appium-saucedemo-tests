package utils;
import io.appium.java_client.AppiumBy;
import static stepdefinitions.Hooks.driver;

public class Utils {

    public static void loginComo(String usuario, String contrasena) {
        driver.findElement(AppiumBy.accessibilityId("Username input field")).sendKeys(usuario);
        driver.findElement(AppiumBy.accessibilityId("Password input field")).sendKeys(contrasena);
        driver.findElement(AppiumBy.accessibilityId("Login button")).click();
    }

}
