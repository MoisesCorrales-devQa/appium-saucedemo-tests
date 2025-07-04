package stepdefinitions;

import io.appium.java_client.AppiumBy;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.*; // <--- Importa las anotaciones de Allure
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static stepdefinitions.Hooks.driver;
import static stepdefinitions.Hooks.context;

@Epic("Checkout")
@Feature("Proceso de compra y pago")
public class CheckoutSteps {

    @Step("El usuario accede al proceso de checkout")
    @When("el usuario accede al proceso de checkout")
    public void elUsuarioAccedeAlProcesoDePago() {
        driver.findElement(AppiumBy.accessibilityId("Proceed To Checkout button")).click();
    }

    @Step("El usuario debería ver la pantalla de información de envío")
    @Then("el usuario debería ver la pantalla de información de envío")
    public void elUsuarioVePantallaInformacionEnvio() {
        boolean errorVisible = driver.findElement
                (AppiumBy.androidUIAutomator("new UiSelector().text(\"Enter a shipping address\")")).isDisplayed();
        assertTrue(errorVisible);
    }

    @Step("Introduce {nombre} como nombre")
    @And("introduce {string} como nombre")
    public void introduceNombre(String nombre) {
        driver.findElement(AppiumBy.accessibilityId("Full Name* input field")).sendKeys(nombre);
    }

    @Step("Introduce {direccion} como dirección")
    @And("introduce {string} como dirección")
    public void introduceDireccion(String direccion) {
        driver.findElement(AppiumBy.accessibilityId("Address Line 1* input field")).sendKeys(direccion);
    }

    @Step("Introduce {ciudad} como ciudad")
    @And("introduce {string} como ciudad")
    public void introduceCiudad(String ciudad) {
        driver.findElement(AppiumBy.accessibilityId("City* input field")).sendKeys(ciudad);
    }

    @Step("Introduce {codigoPostal} como código postal")
    @And("introduce {string} como código postal")
    public void introduceCodigoPostal(String codigoPostal) {
        driver.findElement(AppiumBy.accessibilityId("Zip Code* input field")).sendKeys(codigoPostal);
    }

    @Step("Introduce {pais} como país")
    @And("introduce {string} como país")
    public void introducePais(String pais) {
        driver.findElement(AppiumBy.accessibilityId("Country* input field")).sendKeys(pais);
    }

    @Step("Confirma la información de envío")
    @When("confirma la información de envío")
    public void confirmaInformacionEnvio() {
        driver.findElement(AppiumBy.accessibilityId("To Payment button")).click();
    }

    @Step("El usuario debería ver la pantalla de datos de pago")
    @Then("el usuario debería ver la pantalla de datos de pago")
    public void elUsuarioVePantallaPago() {
        boolean errorVisible = driver.findElement
                (AppiumBy.androidUIAutomator("new UiSelector().text(\"Enter a payment method\")")).isDisplayed();
        assertTrue(errorVisible);
    }

    @Step("Introduce {nombreCompleto} como nombre completo en los datos de pago")
    @And("introduce {string} como nombre completo en los datos de pago")
    public void introduceNombreCompletoPago(String nombreCompleto) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement nombreCompletoCampo = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.accessibilityId("Full Name* input field")
        ));
        nombreCompletoCampo.sendKeys(nombreCompleto);
    }

    @Step("Introduce {numeroTarjeta} como número de tarjeta")
    @And("introduce {string} como número de tarjeta")
    public void introduceNumeroTarjeta(String numeroTarjeta) {
        driver.findElement(AppiumBy.accessibilityId("Card Number* input field")).sendKeys(numeroTarjeta);
    }

    @Step("Introduce {fecha} como fecha de caducidad")
    @And("introduce {string} como fecha de caducidad")
    public void introduceFechaCaducidad(String fecha) {
        driver.findElement(AppiumBy.accessibilityId("Expiration Date* input field")).sendKeys(fecha);
    }

    @Step("Introduce {cvv} como código CVV")
    @And("introduce {string} como código CVV")
    public void introduceCVV(String cvv) {
        driver.findElement(AppiumBy.accessibilityId("Security Code* input field")).sendKeys(cvv);
    }

    @Step("Confirma los datos de pago")
    @When("confirma los datos de pago")
    public void confirmaDatosPago() {
        driver.findElement(AppiumBy.accessibilityId("Review Order button")).click();
    }

    @Step("El usuario debería ver la pantalla de resumen del pedido")
    @Then("el usuario debería ver la pantalla de resumen del pedido")
    public void elUsuarioVeResumenPedido() {
        boolean errorVisible = driver.findElement
                (AppiumBy.androidUIAutomator("new UiSelector().text(\"Review your order\")")).isDisplayed();
        assertTrue(errorVisible);
    }

    @Step("Verifica los productos en el resumen")
    @And("verifica los productos en el resumen")
    public void verificaProductosEnResumen() {
        String productName = (String) context.getContext("addedProduct");
        String productText = driver.findElement(AppiumBy.accessibilityId("product label")).getText();

        assertEquals(productName, productText);
    }

    @Step("Verifica el precio total del pedido")
    @And("verifica el precio total del pedido")
    public void verificaPrecioTotal() {
        Double productPrice = (Double) context.getContext("cartPrice");
        String priceText = driver.findElement(AppiumBy.accessibilityId("product price")).getText();

        Double cartPrice = Double.parseDouble(priceText.replace("$", ""));

        assertEquals(productPrice, cartPrice);
    }

    @Step("Confirma el pedido")
    @When("confirma el pedido")
    public void confirmaPedido() {
        driver.findElement(AppiumBy.accessibilityId("Place Order button")).click();
    }

    @Step("El mensaje de éxito {mensaje} debe ser visible")
    @And("el mensaje de éxito {string} debe ser visible")
    public void mensajeExitoVisible(String mensaje) {
        boolean checkoutCompleteVisible = driver.findElement
                (AppiumBy.androidUIAutomator("new UiSelector().text(\"Checkout Complete\")")).isDisplayed();

        boolean orderDispatchedVisible = driver.findElement
                (AppiumBy.androidUIAutomator("new UiSelector().text(\" Your order has been dispatched and will arrive as fast as the pony gallops!\")")).isDisplayed();

        assertTrue(checkoutCompleteVisible && orderDispatchedVisible);
    }

    @Step("El usuario rellena el formulario de dirección correctamente")
    @And("el usuario rellena el formulario de dirección correctamente")
    public void rellenaElFormulario() {
        introduceNombreCompletoPago("Juan Pérez");
        introduceCiudad("Madrid");
        introduceDireccion("Calle Falsa 123");
        introduceCodigoPostal("28080");
        introducePais("España");
    }

    @Step("Deja vacío el campo de dirección {campo}")
    @And("deja vacio el campo de dirección {string}")
    public void dejaVacioCampoDireccion(String campo) {
        if (!campo.equalsIgnoreCase("nombre")) {
            driver.findElement(AppiumBy.accessibilityId("Full Name* input field")).sendKeys("Juan Pérez");
        }
        if (!campo.equalsIgnoreCase("direccion")) {
            driver.findElement(AppiumBy.accessibilityId("Address Line 1* input field")).sendKeys("Calle Falsa 123");
        }
        if (!campo.equalsIgnoreCase("ciudad")) {
            driver.findElement(AppiumBy.accessibilityId("City* input field")).sendKeys("Madrid");
        }
        if (!campo.equalsIgnoreCase("codigo postal")) {
            driver.findElement(AppiumBy.accessibilityId("Zip Code* input field")).sendKeys("28080");
        }
        if (!campo.equalsIgnoreCase("pais")) {
            driver.findElement(AppiumBy.accessibilityId("Country* input field")).sendKeys("España");
        }
    }

    @Step("Deja vacío el campo de pago {campo}")
    @And("deja vacio el campo de pago {string}")
    public void dejaVacioCampoPago(String campo) {
        if (!campo.equalsIgnoreCase("nombre tarjeta")) {
            escribirConRetry("Full Name* input field", "Juan Tarjeta", 3);
        }
        if (!campo.equalsIgnoreCase("numero tarjeta")) {
            escribirConRetry("Card Number* input field", "4111111111111111", 3);
        }
        if (!campo.equalsIgnoreCase("caducidad")) {
            escribirConRetry("Expiration Date* input field", "12/29", 3);
        }
        if (!campo.equalsIgnoreCase("cvv")) {
            escribirConRetry("Security Code* input field", "123", 3);
        }
    }

    @Step("Ve el mensaje de error {mensaje}")
    @Then("ve el mensaje de error {string}")
    public void veMensajeError(String mensaje) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        if (mensaje.equalsIgnoreCase("Please provide your zip code.") || mensaje.equalsIgnoreCase("Please provide your country.")) {
            scrollHastaElementoConTexto(mensaje);
        }

        WebElement errorElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.androidUIAutomator("new UiSelector().text(\"" + mensaje + "\")")
                )
        );
        assertTrue(errorElement.isDisplayed());
    }

    @Step("Scroll hasta elemento con texto {mensaje}")
    public void scrollHastaElementoConTexto(String mensaje) {
        String uiAutomatorScroll = "new UiScrollable(new UiSelector().scrollable(true))" +
                ".scrollIntoView(new UiSelector().text(\"" + mensaje + "\"))";
        driver.findElement(AppiumBy.androidUIAutomator(uiAutomatorScroll));
    }

    @Step("Escribir con retry en el campo {accessibilityId} el texto {texto}")
    public void escribirConRetry(String accessibilityId, String texto, int maxRetries) {
        int intentos = 0;
        while (intentos < maxRetries) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                WebElement campo = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId(accessibilityId)));
                campo.clear();
                campo.sendKeys(texto);
                return; // éxito, salimos
            } catch (StaleElementReferenceException e) {
                intentos++;
                System.out.println("Elemento stale, reintentando (" + intentos + "/" + maxRetries + ")");

                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    // ignorar
                }
            }
        }
        throw new RuntimeException("No se pudo escribir en el elemento tras " + maxRetries + " intentos");
    }
}