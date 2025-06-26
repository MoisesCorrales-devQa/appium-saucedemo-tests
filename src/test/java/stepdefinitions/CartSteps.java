package stepdefinitions;

import io.appium.java_client.AppiumBy;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static stepdefinitions.Hooks.driver;

public class CartSteps {

    private String productNameAdded;
    private double calculatedTotal;

    @Given("que el usuario está en la pantalla del catálogo")
    public void el_usuario_esta_en_catalogo() {
        WebElement catalogTitle = driver.findElement(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"Products\")"));
        assertTrue(catalogTitle.isDisplayed());
    }

    @When("el usuario añade el primer producto al carrito")
    public void el_usuario_anade_producto() {
        productNameAdded = addProductToCartAndVerify(0);
    }

    @Then("el producto debe estar visible en el carrito")
    public void el_producto_debe_estarse_en_el_carrito() {
        List<WebElement> productLabels = driver.findElements(
                AppiumBy.accessibilityId("product label"));

        boolean found = productLabels.stream()
                .anyMatch(el -> el.getText().equals(productNameAdded));

        assertTrue(found);
    }

    @Given("que el usuario añadió un producto al carrito")
    public void el_usuario_añade_un_producto_para_eliminar() {
        productNameAdded = addProductToCartAndVerify(0);
    }

    @When("el usuario elimina el producto del carrito")
    public void el_usuario_elimina_producto() {
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Remove Item\")")).click();
    }

    @Then("el carrito debe estar vacío")
    public void el_carrito_debe_estar_vacio() {
        WebElement emptyLabel = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"No Items\")"));
        assertTrue(emptyLabel.isDisplayed());
    }

    @When("el usuario añade múltiples productos al carrito")
    public void el_usuario_anade_multiples_productos() {
        for (int i = 0; i < 2; i++) {
            addProductToCartAndVerify(i);
            driver.findElement(AppiumBy.accessibilityId("open menu")).click();
            driver.findElement(AppiumBy.accessibilityId("menu item catalog")).click();
        }
        addProductToCartAndVerify(2);
    }

    @Then("la suma total debe coincidir con el total mostrado en el carrito")
    public void el_total_debe_coincidir() {
        List<WebElement> productPrices = driver.findElements(AppiumBy.accessibilityId("product price"));
        double total = 0;
        for (WebElement price : productPrices) {
            total += Double.parseDouble(price.getText().replace("$", ""));
        }

        WebElement totalElement = driver.findElement(AppiumBy.accessibilityId("total price"));
        double totalInApp = Double.parseDouble(totalElement.getText().replace("$", ""));

        assertEquals(total, totalInApp, 0.01);
    }

    private String addProductToCartAndVerify(int itemId) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement firstProduct = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"store item\").instance(" + itemId + ")")));

        WebElement productNameElement = firstProduct.findElement(AppiumBy.className("android.widget.TextView"));
        String productName = productNameElement.getText();
        firstProduct.click();

        WebElement productScreenTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().textContains(\"" + productName + "\")")));
        assertEquals(productName, productScreenTitle.getText());

        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"Add To Cart\")")));
        addToCartButton.click();

        WebElement cartIcon = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.accessibilityId("cart badge")));
        cartIcon.click();

        WebElement cartScreen = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.accessibilityId("cart screen")));
        assertTrue(cartScreen.isDisplayed());

        return productName;
    }
}