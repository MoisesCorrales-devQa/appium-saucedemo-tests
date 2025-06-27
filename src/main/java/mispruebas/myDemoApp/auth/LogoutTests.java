package mispruebas.myDemoApp.auth;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoutTests extends BaseTest {

    final String STANDARD_USER = "bob@example.com";
    final String STANDARD_PASSWORD = "10203040";
    final String TEXTO_ESPERADO = "You are successfully logged out.";

    @BeforeEach
    public void setupLoginTest(){

        System.out.println("");

        try {
            System.out.println("Esperando 3 segundos antes de iniciar la configuración del test...");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("La espera fue interrumpida.");
        }

        //Abrir el menu lateral
        System.out.println("Accediendo al menú lateral");
        driver.findElement(AppiumBy.accessibilityId("open menu")).click();

        //Acceder a la pantalla de login
        System.out.println("Accediendo a la pantalla de log-in");
        driver.findElement(AppiumBy.accessibilityId("menu item log in")).click();

        //Chequeamos que es la pantalla correcta
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Login\").instance(0)")).isDisplayed();
        System.out.println("Se encuentra el titulo de la pantalla");
    }

    @Test
    public void loginLogoutExitoso() {

        // 1. Logueamos al user
        loginConCredenciales(STANDARD_USER, STANDARD_PASSWORD);

        // 2. Validar que estamos en la pantalla de productos
        boolean productosVisibles = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Products\")")).isDisplayed();

        // 3. Abrir el menu lateral
        System.out.println("Accediendo al menú lateral");
        driver.findElement(AppiumBy.accessibilityId("open menu")).click();

        // 4. Acceder al popup de logout
        System.out.println("Accediendo al botón de log-out");
        driver.findElement(AppiumBy.accessibilityId("menu item log out")).click();

        // 5. Tap en log-out
        System.out.println("Haciendo tap en el botón de log-out del popup");
        driver.findElement(AppiumBy.id("android:id/button1")).click();

        // 5. Obtener el texto del popup
        System.out.println("Obtenemos el tittle del popup");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement alertTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.id("android:id/alertTitle")
        ));

        System.out.println("Obtenemos el texto y comprobamos que sea correcto");         
        String textoActual = alertTitleElement.getText();
        assertEquals(TEXTO_ESPERADO, textoActual, "El texto del alertTitle no coincide con el esperado.");
    }

    @Test
    public void loginLogoutOnCancel() {

        // 1. Logueamos al user
        loginConCredenciales(STANDARD_USER, STANDARD_PASSWORD);

        // 2. Validar que estamos en la pantalla de productos
        boolean productosVisibles = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Products\")")).isDisplayed();

        // 3. Abrir el menu lateral
        System.out.println("Accediendo al menú lateral");
        driver.findElement(AppiumBy.accessibilityId("open menu")).click();

        // 4. Acceder al popup de logout
        System.out.println("Accediendo al botón de log-out");
        driver.findElement(AppiumBy.accessibilityId("menu item log out")).click();

        // 5. Tap en log-out
        System.out.println("Haciendo tap en el botón de log-out del popup");
        driver.findElement(AppiumBy.id("android:id/button2")).click();


        System.out.println("Obtenemos el texto y comprobamos que sea correcto");
        boolean logoutVisible = driver.findElement(AppiumBy.accessibilityId("menu item log out")).isDisplayed();

        assertTrue(logoutVisible, "No se vuelve a la pantalla de logOut");
    }

    private void loginConCredenciales(String usuario, String password) {
        driver.findElement(AppiumBy.accessibilityId("Username input field")).sendKeys(usuario);
        driver.findElement(AppiumBy.accessibilityId("Password input field")).sendKeys(password);
        driver.findElement(AppiumBy.accessibilityId("Login button")).click();
    }

}

