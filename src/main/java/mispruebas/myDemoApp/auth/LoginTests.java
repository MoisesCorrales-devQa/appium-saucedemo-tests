package mispruebas.myDemoApp.auth;
/*
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTests extends BaseTest {

    final String STANDARD_USER = "bob@example.com";
    final String STANDARD_PASSWORD = "10203040";

    final String ERROR_USER = "error@example.com";
    final String ERROR_PASSWORD = "10203040";

    final String BLOCKED_USER = "alice@example.com";
    final String BLOCKED_PASSWORD = "10203040";

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
    public void loginExitoso() {

        // 1. Logueamos al user
        loginConCredenciales(STANDARD_USER, STANDARD_PASSWORD);

        // 4. Validar que estamos en la pantalla de productos
        boolean productosVisibles = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Products\")")).isDisplayed();
        assertTrue(productosVisibles, "No se redirigió a la pantalla de productos");
    }

    @Test
    public void loginFallido() {

        // 1. Logueamos al usuario
        loginConCredenciales(ERROR_USER, ERROR_PASSWORD);

        // 2. Validar que se muestra el mensaje de error
        boolean errorVisible = driver.findElement
                (AppiumBy.androidUIAutomator("new UiSelector().text(\"Provided credentials do not match any user in this service.\")")).isDisplayed();
        System.out.println("Mensaje de error encontrado");
        assertTrue(errorVisible, "No se muestra el mensaje de error");
    }

    @Test
    public void loginBloqueado() {

        // 1. Ingresar usuario
        driver.findElement(AppiumBy.accessibilityId("Username input field")).sendKeys(BLOCKED_USER);

        // 2. Ingresar contraseña
        driver.findElement(AppiumBy.accessibilityId("Password input field")).sendKeys(BLOCKED_PASSWORD);

        // 3. Tap en el botón Login
        driver.findElement(AppiumBy.accessibilityId("Login button")).click();

        // 4. Validar que se muestra el mensaje de error
        boolean errorVisible = driver.findElement
                (AppiumBy.androidUIAutomator("new UiSelector().text(\"Sorry, this user has been locked out.\")")).isDisplayed();
        System.out.println("Mensaje de error encontrado");
        assertTrue(errorVisible, "No se muestra el mensaje de usuario bloqueado");
    }

    @Test
    public void usernameEmpty(){

        // 1. Tap en el botón Login
        driver.findElement(AppiumBy.accessibilityId("Login button")).click();

        System.out.println("Buscamos el mensaje de error de 'User is required'");
        // 2. Validar que se muestra el mensaje de error
        boolean errorVisible = driver.findElement
                (AppiumBy.androidUIAutomator("new UiSelector().text(\"Username is required\")")).isDisplayed();
        System.out.println("Mensaje de error encontrado");
        assertTrue(errorVisible, "No se muestra el mensaje de usuario bloqueado");
    }

    @Test
    public void passwordEmpty(){

        // 1. Ingresar usuario
        driver.findElement(AppiumBy.accessibilityId("Username input field")).sendKeys(BLOCKED_USER);

        // 2. Tap en el botón Login
        driver.findElement(AppiumBy.accessibilityId("Login button")).click();

        // 3. Validar que se muestra el mensaje de error
        System.out.println("Buscamos el mensaje de error de 'Password is required'");
        boolean errorVisible = driver.findElement
                (AppiumBy.androidUIAutomator("new UiSelector().text(\"Password is required\")")).isDisplayed();
        System.out.println("Mensaje de error encontrado");
        assertTrue(errorVisible, "No se muestra el mensaje de usuario bloqueado");
    }


    private void loginConCredenciales(String usuario, String password) {
        driver.findElement(AppiumBy.accessibilityId("Username input field")).sendKeys(usuario);
        driver.findElement(AppiumBy.accessibilityId("Password input field")).sendKeys(password);
        driver.findElement(AppiumBy.accessibilityId("Login button")).click();
    }

}
*/
