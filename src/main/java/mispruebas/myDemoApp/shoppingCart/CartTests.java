package mispruebas.myDemoApp.shoppingCart;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseTest;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
     * @return El nombre del producto añadido. **/
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
        // Aquí debes esperar que el elemento "No Items" sea visible, ya que la UI puede tardar en actualizarse.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement noItemsMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"No Items\")")
        ));
        assertTrue(noItemsMessage.isDisplayed(), "El mensaje 'No Items' no se muestra, el carrito no está vacío.");
    }

    @Test
    public void addMultipleElementsAndCheckTotal(){
        System.out.println("\n--- Inicia la prueba de añadir múltiples elementos y verificar total ---");

        System.out.println("Añadimos 3 elementos al carrito");
        for (int i = 0; i < 3; i++) { // Cambiado a 3 para añadir 3 elementos
            addProductToCartWithoutVerification(i);

            // Después de añadir cada producto (excepto el último), volvemos al catálogo
            // y luego al menú principal para seleccionar el catálogo de nuevo.
            // Si el último elemento se añade y estamos ya en el carrito para la verificación,
            // no necesitamos volver al catálogo.
            if (i < 2) { // Si no es el último producto que vamos a añadir
                System.out.println("Accediendo al menú lateral y volviendo al catálogo");
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                WebElement openMenuButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("open menu")));
                openMenuButton.click();

                WebElement catalogMenuItem = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("menu item catalog")));
                catalogMenuItem.click();

                WebElement productsScreenTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.androidUIAutomator("new UiSelector().text(\"Products\")")
                ));
                assertTrue(productsScreenTitle.isDisplayed(), "No se muestra la pantalla de Productos (Catálogo) después de volver del menú.");
            }
        }

        // Navegar al carrito para verificar
        System.out.println("Navegando al carrito para verificar los totales.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cartIcon = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.accessibilityId("cart badge")
        ));
        cartIcon.click();

        WebElement cartScreenTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.accessibilityId("cart screen")
        ));
        assertTrue(cartScreenTitle.isDisplayed(), "No se muestra la pantalla del carrito.");

        // Lógica de suma de precios con scroll
        Set<String> seenPrices = new HashSet<>();
        double totalCalculated = 0;
        boolean canScrollMore = true; // Controla si es posible seguir haciendo scroll
        int scrolls = 0;


        while (canScrollMore) {
            List<WebElement> productPrices = driver.findElements(AppiumBy.accessibilityId("product price"));
            for (WebElement priceElement : productPrices) {
                String priceText = priceElement.getText();
                if (seenPrices.add(priceText)) { // Añade el precio al conjunto si no ha sido visto antes
                    double price = Double.parseDouble(priceText.replace("$", ""));
                    totalCalculated += price;
                    System.out.println("Precio añadido: " + priceText + ", Total acumulado: " + totalCalculated);
                }
            }

            // Intentar hacer scroll. Si no se puede, canScrollMore se vuelve false.
            if (scrolls >= 3) break;

            boolean scrolled = scrollDown();
            scrolls++;
            if (!scrolled) {
                canScrollMore = false;
            } else {
                // Pequeña espera después del scroll para que los elementos se carguen si es necesario
                try {
                    Thread.sleep(500); // Ajusta este valor si es necesario
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        WebElement tagPrecio = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("total price")));
        double totalApp = Double.parseDouble(tagPrecio.getText().replace("$", ""));

        System.out.println("Total calculado: $" + String.format("%.2f", totalCalculated));
        System.out.println("Total mostrado en la app: $" + String.format("%.2f", totalApp));

        assertEquals(totalCalculated, totalApp, 0.01, "El precio calculado no coincide con el total mostrado en el carrito.");
        System.out.println("--- Finaliza la prueba de añadir múltiples elementos y verificar total ---");
    }

    public ProductInfo getFirstProductInList(int instanceIndex) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // 1. Encontrar el 'store item' por su índice
            WebElement productContainer = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.androidUIAutomator
                    (String.format("new UiSelector().description(\"store item\").instance(%d)", instanceIndex))));

            // 2. Dentro de este 'store item', buscar el elemento que contiene el nombre del producto.
            WebElement productNameElement = productContainer.findElement(AppiumBy.className("android.widget.TextView"));

            String productName = productNameElement.getText();

            System.out.println("Nombre del producto encontrado en el índice " + instanceIndex + ": " + productName);

            return new ProductInfo(productContainer, productName);

        } catch (Exception e) {
            System.err.println("No se pudo encontrar el producto en el índice " + instanceIndex + " o su nombre: " + e.getMessage());
            return null;
        }
    }

    private void addProductToCartWithoutVerification(int itemId) {
        try {
            ProductInfo product = getFirstProductInList(itemId);
            if (product == null || product.productElement == null) {
                throw new RuntimeException("No se pudo obtener el producto para añadir sin verificación.");
            }

            System.out.println("Añadiendo '" + product.productName + "' al carrito sin verificación.");
            product.productElement.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(
                    AppiumBy.androidUIAutomator("new UiSelector().text(\"Add To Cart\")")
            ));

            addToCartButton.click();

        } catch (Exception e) {
            throw new RuntimeException("Error al añadir producto sin verificación: " + e.getMessage());
        }
    }

    /**
     * Realiza un scroll hacia abajo en la vista actual.
     * @return true si se pudo hacer scroll, false si no.
     */
    private boolean scrollDown() {
        try {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"));
            System.out.println("Se realizó un scroll hacia abajo.");
            return true;
        } catch (Exception e) {
            System.out.println("No se pudo hacer más scroll hacia abajo o no hay elementos scrolleables.");
            return false;
        }
    }
}