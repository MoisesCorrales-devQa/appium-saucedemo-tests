package mispruebas.myDemoApp;
/*
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseTest;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTests extends BaseTest {

    public static class ProductInfo {
        public WebElement productElement;
        public String productName;

        public ProductInfo(WebElement element, String name) {
            this.productElement = element;
            this.productName = name;
        }
    }

    // Variable para almacenar la información del producto añadido si es necesario entre métodos
    private ProductInfo addedProductInfo;

    @BeforeEach
    void setup() {
        System.out.println("Comprobamos que se muestra la pantalla de catalogo");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productsScreenTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"Products\")")
        ));
        assertTrue(productsScreenTitle.isDisplayed(), "No se muestra la pantalla de Productos (Catálogo).");
    }

    /**
     * Método para añadir un producto al carrito y verificar su adición.
     * Este método puede ser llamado por otros tests.
     * @return El nombre del producto añadido.

    private String addProductToCartAndVerify(int itemId) {

        System.out.println("Obtenemos el primer elemento de la lista y hacemos click");
        ProductInfo primerItem = getFirstProductInList(itemId);

        if (primerItem == null || primerItem.productElement == null) {
            assertTrue(false, "No se pudo obtener el primer producto de la lista.");
            return null; // O lanzar una excepción específica
        }

        primerItem.productElement.click();

        System.out.println("Comprobamos que se muestra la pantalla del producto");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().textContains(\"" + primerItem.productName + "\")")
        ));

        System.out.println("Obtenemos el texto y comprobamos que sea correcto");
        String textoActual = productTitleElement.getText();
        assertEquals(primerItem.productName, textoActual, "El título de la pantalla del producto no coincide.");

        System.out.println("Tap en añadir al carro");
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"Add To Cart\")")
        ));
        addToCartButton.click();

        System.out.println("Navegamos al carrito.");
        // Reemplaza con el ID o selector correcto del icono del carrito
        WebElement cartIcon = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.accessibilityId("cart badge")
        ));
        cartIcon.click();

        System.out.println("Comprobamos que se muestra la pantalla del carrito.");
        WebElement cartScreenTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.accessibilityId("cart screen")
        ));
        assertTrue(cartScreenTitle.isDisplayed(), "No se muestra la pantalla del carrito.");

        System.out.println("Verificamos que el producto añadido esté en el carrito.");
        List<WebElement> productLabels = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                AppiumBy.accessibilityId("product label")
        ));

        boolean found = productLabels.stream()
                .anyMatch(el -> el.getText().equals(primerItem.productName));

        assertTrue(found, "El producto '" + primerItem.productName + "' no se encontró en el carrito.");

        this.addedProductInfo = primerItem;
        return primerItem.productName;
    }

    @Test
    public void testAddToCart() {
        System.out.println("--- Inicia la lógica de añadir al carrito y verificar ---");

        String productNameInCart = addProductToCartAndVerify(0);

        System.out.println("Producto '" + productNameInCart + "' verificado en el carrito correctamente.");
        System.out.println("--- Finaliza la lógica de añadir al carrito y verificar ---");

    }

    @Test
    public void testRemoveFromCart() throws InterruptedException {
        System.out.println("\n--- Inicia la prueba de eliminar del carrito ---");

        System.out.println("Primero añadimos un item al carrito");
        // Paso 1: Añadir un producto al carrito (reutilizando el método)
        String productNameToAdd = addProductToCartAndVerify(0);
        if (productNameToAdd == null) {
            assertTrue(false, "Fallo al añadir el producto al carrito. No se puede continuar con la eliminación.");
        }

        System.out.println("Hacemos tap en remove item");
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Remove Item\")")).click();

        System.out.println("Chequeamos que el carrito está vacío");
        boolean visibileTittle = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"No Items\")")).isDisplayed();

        assertTrue(visibileTittle);
    }

    @Test
    public void addMultipleElementsAndCheckTotal(){



        System.out.println("Añadimos 3 elementos al carrito");
        for (int i = 0; i < 2; i++) {
            addProductToCartAndVerify(i);

            System.out.println("Accediendo al menú lateral");
            driver.findElement(AppiumBy.accessibilityId("open menu")).click();

            String catalogItemId = "menu item catalog";
            String catalogTittleId = "new UiSelector().text(\"Products\")";
            driver.findElement(AppiumBy.accessibilityId(catalogItemId)).click();
            assertTrue(driver.findElement(AppiumBy.androidUIAutomator(catalogTittleId)).isDisplayed());
        }
        addProductToCartAndVerify(2);

        List<WebElement> productPrices = driver.findElements(AppiumBy.accessibilityId("product price"));
        double total = 0;

        for (WebElement prices : productPrices) {
            String trimmedPrice = prices.getText().replace("$", "");
            double price = Double.parseDouble(trimmedPrice);
            total += price;
        }

        WebElement tagPrecio = driver.findElement(AppiumBy.accessibilityId("total price"));

        double totalApp = Double.parseDouble(tagPrecio.getText().replace("$", ""));

        assertEquals(totalApp, total, "El precio no coincide con la suma total");

    }

    public ProductInfo getFirstProductInList(int instanceIndex) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // 1. Encontrar el primer 'store item'
            WebElement firstProductContainer = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.androidUIAutomator
                    (String.format("new UiSelector().description(\"store item\").instance(%d)", instanceIndex))));

            // 2. Dentro de este 'store item', buscar el elemento que contiene el nombre del producto.
            WebElement productNameElement = firstProductContainer.findElement(AppiumBy.className("android.widget.TextView"));

            String productName = productNameElement.getText();

            System.out.println("Nombre del primer producto encontrado: " + productName);

            return new ProductInfo(firstProductContainer, productName);

        } catch (Exception e) {
            System.err.println("No se pudo encontrar el primer elemento de la lista o su nombre: " + e.getMessage());
            return null;
        }
    }


}*/