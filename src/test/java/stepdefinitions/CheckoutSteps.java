package stepdefinitions;

import io.cucumber.java.en.*;
import io.qameta.allure.*;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import pages.CheckoutPage;

import static stepdefinitions.Hooks.driver;
import static stepdefinitions.Hooks.context;

@Epic("Checkout")
@Feature("Proceso de compra y pago")
public class CheckoutSteps {

    private final CheckoutPage checkoutPage = new CheckoutPage(driver);

    @Step("El usuario accede al proceso de checkout")
    @When("el usuario accede al proceso de checkout")
    public void elUsuarioAccedeAlProcesoDePago() {
        checkoutPage.accederCheckout();
    }

    @Step("El usuario debería ver la pantalla de información de envío")
    @Then("el usuario debería ver la pantalla de información de envío")
    public void elUsuarioVePantallaInformacionEnvio() {
        Assert.assertTrue(checkoutPage.pantallaInformacionEnvioVisible());
    }

    @Step("Introduce {nombre} como nombre")
    @And("introduce {string} como nombre")
    public void introduceNombre(String nombre) {
        checkoutPage.ingresarNombre(nombre);
    }

    @Step("Introduce {direccion} como dirección")
    @And("introduce {string} como dirección")
    public void introduceDireccion(String direccion) {
        checkoutPage.ingresarDireccion(direccion);
    }

    @Step("Introduce {ciudad} como ciudad")
    @And("introduce {string} como ciudad")
    public void introduceCiudad(String ciudad) {
        checkoutPage.ingresarCiudad(ciudad);
    }

    @Step("Introduce {codigoPostal} como código postal")
    @And("introduce {string} como código postal")
    public void introduceCodigoPostal(String codigoPostal) {
        checkoutPage.ingresarCodigoPostal(codigoPostal);
    }

    @Step("Introduce {pais} como país")
    @And("introduce {string} como país")
    public void introducePais(String pais) {
        checkoutPage.ingresarPais(pais);
    }

    @Step("Confirma la información de envío")
    @When("confirma la información de envío")
    public void confirmaInformacionEnvio() {
        checkoutPage.confirmarInformacionEnvio();
    }

    @Step("El usuario debería ver la pantalla de datos de pago")
    @Then("el usuario debería ver la pantalla de datos de pago")
    public void elUsuarioVePantallaPago() {
        Assert.assertTrue(checkoutPage.pantallaPagoVisible());
    }

    @Step("Introduce {nombreCompleto} como nombre completo en los datos de pago")
    @And("introduce {string} como nombre completo en los datos de pago")
    public void introduceNombreCompletoPago(String nombreCompleto) {
        checkoutPage.ingresarNombreCompletoPago(nombreCompleto);
    }

    @Step("Introduce {numeroTarjeta} como número de tarjeta")
    @And("introduce {string} como número de tarjeta")
    public void introduceNumeroTarjeta(String numeroTarjeta) {
        checkoutPage.ingresarNumeroTarjeta(numeroTarjeta);
    }

    @Step("Introduce {fecha} como fecha de caducidad")
    @And("introduce {string} como fecha de caducidad")
    public void introduceFechaCaducidad(String fecha) {
        checkoutPage.ingresarFechaCaducidad(fecha);
    }

    @Step("Introduce {cvv} como código CVV")
    @And("introduce {string} como código CVV")
    public void introduceCVV(String cvv) {
        checkoutPage.ingresarCVV(cvv);
    }

    @Step("Confirma los datos de pago")
    @When("confirma los datos de pago")
    public void confirmaDatosPago() {
        checkoutPage.confirmarDatosPago();
    }

    @Step("El usuario debería ver la pantalla de resumen del pedido")
    @Then("el usuario debería ver la pantalla de resumen del pedido")
    public void elUsuarioVeResumenPedido() {
        Assert.assertTrue(checkoutPage.pantallaResumenPedidoVisible());
    }

    @Step("Verifica los productos en el resumen")
    @And("verifica los productos en el resumen")
    public void verificaProductosEnResumen() {
        String productName = (String) context.getContext("addedProduct");
        String productText = checkoutPage.obtenerProductoResumen();
        Assert.assertEquals(productName, productText);
    }

    @Step("Verifica el precio total del pedido")
    @And("verifica el precio total del pedido")
    public void verificaPrecioTotal() {
        Double productPrice = (Double) context.getContext("cartPrice");
        String priceText = checkoutPage.obtenerPrecioResumen();
        Double cartPrice = Double.parseDouble(priceText.replace("$", ""));
        Assert.assertEquals(productPrice, cartPrice);
    }

    @Step("Confirma el pedido")
    @When("confirma el pedido")
    public void confirmaPedido() {
        checkoutPage.confirmarPedido();
    }

    @Step("El mensaje de éxito {mensaje} debe ser visible")
    @And("el mensaje de éxito {string} debe ser visible")
    public void mensajeExitoVisible(String mensaje) {
        Assert.assertTrue(checkoutPage.mensajeCheckoutCompletoVisible() && checkoutPage.mensajeOrderDispatchedVisible());
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
            checkoutPage.ingresarNombre("Juan Pérez");
        }
        if (!campo.equalsIgnoreCase("direccion")) {
            checkoutPage.ingresarDireccion("Calle Falsa 123");
        }
        if (!campo.equalsIgnoreCase("ciudad")) {
            checkoutPage.ingresarCiudad("Madrid");
        }
        if (!campo.equalsIgnoreCase("codigo postal")) {
            checkoutPage.ingresarCodigoPostal("28080");
        }
        if (!campo.equalsIgnoreCase("pais")) {
            checkoutPage.ingresarPais("España");
        }
    }

    @Step("Deja vacío el campo de pago {campo}")
    @And("deja vacio el campo de pago {string}")
    public void dejaVacioCampoPago(String campo) {
        if (!campo.equalsIgnoreCase("nombre tarjeta")) {
            checkoutPage.escribirConRetry("Full Name* input field", "Juan Tarjeta", 3);
        }
        if (!campo.equalsIgnoreCase("numero tarjeta")) {
            checkoutPage.escribirConRetry("Card Number* input field", "4111111111111111", 3);
        }
        if (!campo.equalsIgnoreCase("caducidad")) {
            checkoutPage.escribirConRetry("Expiration Date* input field", "12/29", 3);
        }
        if (!campo.equalsIgnoreCase("cvv")) {
            checkoutPage.escribirConRetry("Security Code* input field", "123", 3);
        }
    }

    @Step("Ve el mensaje de error {mensaje}")
    @Then("ve el mensaje de error {string}")
    public void veMensajeError(String mensaje) {
        if (mensaje.equalsIgnoreCase("Please provide your zip code.") || mensaje.equalsIgnoreCase("Please provide your country.")) {
            checkoutPage.scrollHastaTexto(mensaje);
        }
        WebElement errorElement = checkoutPage.esperarMensajeErrorVisible(mensaje);
        Assert.assertTrue(errorElement.isDisplayed());
    }
}