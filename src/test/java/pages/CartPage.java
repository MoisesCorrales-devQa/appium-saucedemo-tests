package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CartPage extends BasePage {

    // ========== SELECTORS ==========
    private final By catalogTitleSelector = AppiumBy.androidUIAutomator("new UiSelector().text(\"Products\")");
    private final By storeItemSelector(int itemId) {
        return AppiumBy.androidUIAutomator("new UiSelector().description(\"store item\").instance(" + itemId + ")");
    }
    private final By productNameInItem = By.className("android.widget.TextView");
    private final By productScreenTitleSelector(String productName) {
        return AppiumBy.androidUIAutomator("new UiSelector().textContains(\"" + productName + "\")");
    }
    private final By addToCartButtonSelector = AppiumBy.androidUIAutomator("new UiSelector().text(\"Add To Cart\")");
    private final By cartBadgeSelector = AppiumBy.accessibilityId("cart badge");
    private final By cartScreenSelector = AppiumBy.accessibilityId("cart screen");
    private final By totalPriceSelector = AppiumBy.accessibilityId("total price");
    private final By productLabelSelector = AppiumBy.accessibilityId("product label");
    private final By removeItemSelector = AppiumBy.androidUIAutomator("new UiSelector().text(\"Remove Item\")");
    private final By emptyCartLabelSelector = AppiumBy.androidUIAutomator("new UiSelector().text(\"No Items\")");
    private final By openMenuSelector = AppiumBy.accessibilityId("open menu");
    private final By menuCatalogSelector = AppiumBy.accessibilityId("menu item catalog");
    private final By productPriceSelector = AppiumBy.accessibilityId("product price");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean estaEnPantallaCatalogo() {
        return isVisible(catalogTitleSelector);
    }

    public String addProductToCartAndGo(int itemId) {
        WebElement firstProduct = waitUntilPresent(storeItemSelector(itemId));
        String productName = firstProduct.findElement(productNameInItem).getText();
        firstProduct.click();

        WebElement productScreenTitle = waitUntilVisible(productScreenTitleSelector(productName));
        if (!productName.equals(productScreenTitle.getText())) {
            throw new AssertionError("Nombre de producto no coincide");
        }

        click(addToCartButtonSelector);
        click(cartBadgeSelector);

        WebElement cartScreen = waitUntilVisible(cartScreenSelector);
        if (!cartScreen.isDisplayed()) {
            throw new AssertionError("Pantalla del carrito no visible");
        }

        return productName;
    }

    public double obtenerPrecioTotalEnCarrito() {
        String priceText = getText(totalPriceSelector);
        return Double.parseDouble(priceText.replace("$", ""));
    }

    public boolean productoEstaEnCarrito(String nombre) {
        List<WebElement> productLabels = driver.findElements(productLabelSelector);
        return productLabels.stream()
                .anyMatch(el -> el.getText().equals(nombre));
    }

    public void eliminarProductoDelCarrito() {
        click(removeItemSelector);
    }

    public boolean carritoVacio() {
        return isVisible(emptyCartLabelSelector);
    }

    public void navegarAlCatalogo() {
        click(openMenuSelector);
        click(menuCatalogSelector);
    }

    public void addMultiplesProductos(int cantidad) {
        for (int i = 0; i < cantidad - 1; i++) {
            addProductToCartAndGo(i);
            navegarAlCatalogo();
        }
        addProductToCartAndGo(cantidad - 1);
    }

    public double calcularTotalProductos() {
        Set<String> seenPrices = new HashSet<>();
        double total = 0;

        int previousCount = -1;
        int currentCount = 0;

        while (previousCount < currentCount || previousCount == -1) {
            previousCount = seenPrices.size();

            List<WebElement> prices = driver.findElements(productPriceSelector);
            for (WebElement price : prices) {
                String priceText = price.getText();
                if (seenPrices.add(priceText)) {
                    total += Double.parseDouble(priceText.replace("$", ""));
                }
            }

            currentCount = seenPrices.size();
            scrollDown();
        }
        return total;
    }

    public double obtenerTotalMostradoEnCarrito() {
        return Double.parseDouble(getText(totalPriceSelector).replace("$", ""));
    }
}