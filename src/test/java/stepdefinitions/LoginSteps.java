package stepdefinitions;

import io.appium.java_client.AppiumBy;
import io.cucumber.java.en.*;

import static org.junit.Assert.assertTrue;
import static stepdefinitions.Hooks.driver;

public class LoginSteps {

    @Given("el usuario abre la app y accede a la pestaña de login")
    public void abrirApp() {
        driver.findElement(AppiumBy.accessibilityId("open menu")).click();

        driver.findElement(AppiumBy.accessibilityId("menu item log in")).click();
    }

    @When("introduce el email {string} y password {string}")
    public void introducirCredenciales(String user, String password) {
        driver.findElement(AppiumBy.accessibilityId("Username input field")).sendKeys(user);
        driver.findElement(AppiumBy.accessibilityId("Password input field")).sendKeys(password);
        driver.findElement(AppiumBy.accessibilityId("Login button")).click();
    }

    @Then("accede correctamente al panel principal")
    public void verificaPantallaPrincipal() {
        boolean productosVisibles = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Products\")")).isDisplayed();
        assertTrue(productosVisibles);
    }

    @Then("se muestra el mensaje de no existe ningun usuario con esas credenciales")
    public void verificaMensajeDeErrorNoExisteUsuario() {
        boolean errorVisible = driver.findElement
                (AppiumBy.androidUIAutomator("new UiSelector().text(\"Provided credentials do not match any user in this service.\")")).isDisplayed();
        assertTrue(errorVisible);
    }

    @Then("se muestra un mensaje de error de 'Usuario bloqueado'")
    public void verificaMensajeDeErrorBloqueado() {
        boolean errorVisible = driver.findElement
                (AppiumBy.androidUIAutomator("new UiSelector().text(\"Sorry, this user has been locked out.\")")).isDisplayed();
        assertTrue(errorVisible);
    }

    @Then("se muestra un mensaje de error de contraseña requerida")
    public void verificaMensajeDeErrorContrasenyaVacia() {
        boolean errorVisible = driver.findElement
                (AppiumBy.androidUIAutomator("new UiSelector().text(\"Password is required\")")).isDisplayed();
        assertTrue(errorVisible);
    }

    @Then("se muestra un mensaje de error de usuario requerido")
    public void verificaMensajeDeErrorUsuarioVacio() {
        boolean errorVisible = driver.findElement
                (AppiumBy.androidUIAutomator("new UiSelector().text(\"Username is required\")")).isDisplayed();
        assertTrue(errorVisible);
    }
}
