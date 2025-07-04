package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

    // =============== SELECTORS ===============
    private final By proceedToCheckoutBtn = AppiumBy.accessibilityId("Proceed To Checkout button");
    private final By shippingTitle = AppiumBy.androidUIAutomator("new UiSelector().text(\"Enter a shipping address\")");
    private final By fullNameField = AppiumBy.accessibilityId("Full Name* input field");
    private final By addressField = AppiumBy.accessibilityId("Address Line 1* input field");
    private final By cityField = AppiumBy.accessibilityId("City* input field");
    private final By zipField = AppiumBy.accessibilityId("Zip Code* input field");
    private final By countryField = AppiumBy.accessibilityId("Country* input field");
    private final By toPaymentBtn = AppiumBy.accessibilityId("To Payment button");
    private final By paymentTitle = AppiumBy.androidUIAutomator("new UiSelector().text(\"Enter a payment method\")");
    private final By cardNumberField = AppiumBy.accessibilityId("Card Number* input field");
    private final By expirationDateField = AppiumBy.accessibilityId("Expiration Date* input field");
    private final By securityCodeField = AppiumBy.accessibilityId("Security Code* input field");
    private final By reviewOrderBtn = AppiumBy.accessibilityId("Review Order button");
    private final By orderOverviewTitle = AppiumBy.androidUIAutomator("new UiSelector().text(\"Review your order\")");
    private final By productLabel = AppiumBy.accessibilityId("product label");
    private final By productPrice = AppiumBy.accessibilityId("product price");
    private final By placeOrderBtn = AppiumBy.accessibilityId("Place Order button");
    private final By checkoutCompleteTitle = AppiumBy.androidUIAutomator("new UiSelector().text(\"Checkout Complete\")");
    private final By orderDispatchedTitle = AppiumBy.androidUIAutomator("new UiSelector().text(\" Your order has been dispatched and will arrive as fast as the pony gallops!\")");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    // =============== Shipping Info ===============
    public void accederCheckout() {
        click(proceedToCheckoutBtn);
    }

    public boolean pantallaInformacionEnvioVisible() {
        return isVisible(shippingTitle);
    }

    public void ingresarNombre(String nombre) {
        sendKeys(fullNameField, nombre);
    }

    public void ingresarDireccion(String direccion) {
        sendKeys(addressField, direccion);
    }

    public void ingresarCiudad(String ciudad) {
        sendKeys(cityField, ciudad);
    }

    public void ingresarCodigoPostal(String postal) {
        sendKeys(zipField, postal);
    }

    public void ingresarPais(String pais) {
        sendKeys(countryField, pais);
    }

    public void confirmarInformacionEnvio() {
        click(toPaymentBtn);
    }

    // =============== Payment Info ===============
    public boolean pantallaPagoVisible() {
        return isVisible(paymentTitle);
    }

    public void ingresarNombreCompletoPago(String nombre) {
        sendKeys(fullNameField, nombre);
    }

    public void ingresarNumeroTarjeta(String num) {
        sendKeys(cardNumberField, num);
    }

    public void ingresarFechaCaducidad(String fecha) {
        sendKeys(expirationDateField, fecha);
    }

    public void ingresarCVV(String cvv) {
        sendKeys(securityCodeField, cvv);
    }

    public void confirmarDatosPago() {
        click(reviewOrderBtn);
    }

    // =============== Overview ===============
    public boolean pantallaResumenPedidoVisible() {
        return isVisible(orderOverviewTitle);
    }

    public String obtenerProductoResumen() {
        return getText(productLabel);
    }

    public String obtenerPrecioResumen() {
        return getText(productPrice);
    }

    public void confirmarPedido() {
        click(placeOrderBtn);
    }

    // =============== Complete ===============
    public boolean mensajeCheckoutCompletoVisible() {
        return isVisible(checkoutCompleteTitle);
    }

    public boolean mensajeOrderDispatchedVisible() {
        return isVisible(orderDispatchedTitle);
    }

    // =============== Utilidades ===============

    public void escribirConRetry(String accessibilityId, String texto, int maxRetries) {
        sendKeysWithRetry(AppiumBy.accessibilityId(accessibilityId), texto, maxRetries);
    }

    public void scrollHastaTexto(String mensaje) {
        scrollToText(mensaje);
    }

    public WebElement esperarMensajeErrorVisible(String mensaje) {
        return waitUntilVisible(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + mensaje + "\")"));
    }
}