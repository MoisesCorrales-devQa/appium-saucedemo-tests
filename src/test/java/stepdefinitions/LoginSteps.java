package stepdefinitions;

import io.cucumber.java.en.*;
import io.qameta.allure.*;
import org.junit.Assert;
import pages.InventoryPage;
import pages.LoginPage;

import static stepdefinitions.Hooks.driver;

@Epic("Login")
@Feature("Autenticación de usuarios y validación de credenciales")
public class LoginSteps {

    private final LoginPage loginPage = new LoginPage(driver);
    private final InventoryPage inventoryPage = new InventoryPage(driver);

    @Step("El usuario abre la app y accede a la pestaña de login")
    @Given("el usuario abre la app y accede a la pestaña de login")
    public void abrirApp() {
        loginPage.abrirMenu();
        loginPage.abrirLogin();
    }

    @Step("Introduce el email {user} y password {password}")
    @When("introduce el email {string} y password {string}")
    public void introducirCredenciales(String user, String password) {
        loginPage.ingresarUsuario(user);
        loginPage.ingresarPassword(password);
        loginPage.pulsarLogin();
    }

    @Step("Accede correctamente al panel principal")
    @Then("accede correctamente al panel principal")
    public void verificaPantallaPrincipal() {
        Assert.assertTrue(inventoryPage.productosVisibles());
    }

    @Step("Se muestra el mensaje de 'no existe ningun usuario con esas credenciales'")
    @Then("se muestra el mensaje de no existe ningun usuario con esas credenciales")
    public void verificaMensajeDeErrorNoExisteUsuario() {
        Assert.assertTrue(loginPage.mensajeErrorCredenciales());
    }

    @Step("Se muestra un mensaje de error de 'Usuario bloqueado'")
    @Then("se muestra un mensaje de error de 'Usuario bloqueado'")
    public void verificaMensajeDeErrorBloqueado() {
        Assert.assertTrue(loginPage.mensajeErrorBloqueado());
    }

    @Step("Se muestra un mensaje de error de contraseña requerida")
    @Then("se muestra un mensaje de error de contraseña requerida")
    public void verificaMensajeDeErrorContrasenyaVacia() {
        Assert.assertTrue(loginPage.mensajeErrorPasswordRequerido());
    }

    @Step("Se muestra un mensaje de error de usuario requerido")
    @Then("se muestra un mensaje de error de usuario requerido")
    public void verificaMensajeDeErrorUsuarioVacio() {
        Assert.assertTrue(loginPage.mensajeErrorUsuarioRequerido());
    }
}