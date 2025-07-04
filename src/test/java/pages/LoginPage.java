package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // ========== SELECTORS ==========
    private final By openMenuBtn = AppiumBy.accessibilityId("open menu");
    private final By loginMenuItem = AppiumBy.accessibilityId("menu item log in");
    private final By usernameField = AppiumBy.accessibilityId("Username input field");
    private final By passwordField = AppiumBy.accessibilityId("Password input field");
    private final By loginBtn = AppiumBy.accessibilityId("Login button");
    private final By productsText = AppiumBy.androidUIAutomator("new UiSelector().text(\"Products\")");
    private final By errorCredenciales = AppiumBy.androidUIAutomator("new UiSelector().text(\"Provided credentials do not match any user in this service.\")");
    private final By errorBloqueado = AppiumBy.androidUIAutomator("new UiSelector().text(\"Sorry, this user has been locked out.\")");
    private final By errorPassword = AppiumBy.androidUIAutomator("new UiSelector().text(\"Password is required\")");
    private final By errorUsuario = AppiumBy.androidUIAutomator("new UiSelector().text(\"Username is required\")");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void abrirMenu() {
        click(openMenuBtn);
    }

    public void abrirLogin() {
        click(loginMenuItem);
    }

    public void ingresarUsuario(String usuario) {
        sendKeys(usernameField, usuario);
    }

    public void ingresarPassword(String password) {
        sendKeys(passwordField, password);
    }

    public void pulsarLogin() {
        click(loginBtn);
    }

    public boolean productosVisibles() {
        return isVisible(productsText);
    }

    public boolean mensajeErrorCredenciales() {
        return isVisible(errorCredenciales);
    }

    public boolean mensajeErrorBloqueado() {
        return isVisible(errorBloqueado);
    }

    public boolean mensajeErrorPasswordRequerido() {
        return isVisible(errorPassword);
    }

    public boolean mensajeErrorUsuarioRequerido() {
        return isVisible(errorUsuario);
    }
}