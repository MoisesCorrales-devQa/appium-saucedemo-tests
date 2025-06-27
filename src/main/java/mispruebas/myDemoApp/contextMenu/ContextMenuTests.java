package mispruebas.myDemoApp.contextMenu;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContextMenuTests extends BaseTest {

    @BeforeEach
    public void setUpTests() {

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

        System.out.println("Comprobamos que se muestra el menú contextual");
        boolean contextMenuVisible = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ScrollView\").instance(0)")).isDisplayed();
        assertTrue(contextMenuVisible, "No se muestra el menú contextual");

    }

    @Test
    public void testCatalog() {

        String catalogItemId = "menu item catalog";
        String catalogTittleId = "new UiSelector().text(\"Products\")";

        boolean pageTitle = testContextMenuItem(catalogItemId, catalogTittleId);

        assertTrue(pageTitle, "No se muestra la pantalla de catálogo");
    }

    @Test
    public void testWebView() {

        String webViewItemId = "menu item webview";
        String webViewTittleId = "new UiSelector().text(\"Webview\")";

        boolean pageTitle = testContextMenuItem(webViewItemId,webViewTittleId);
        assertTrue(pageTitle, "No se muestra la pantalla de webView");
    }

    @Test
    public void testMenuItemQrCodeScanner() {
        String menuItemId = "menu item qr code scanner";
        String pageTitleLocator = "new UiSelector().text(\"QR Code Scanner\")";
        String QRScannerPermission = "com.android.permissioncontroller:id/permission_allow_foreground_only_button";

        boolean pageTitleDisplayed = testContextMenuItemWithPermission(menuItemId, QRScannerPermission,pageTitleLocator);
        assertTrue(pageTitleDisplayed, "No se muestra la pantalla de QR Code Scanner.");
    }

    @Test
    public void testMenuItemGeoLocation() {
        String menuItemId = "menu item geo location";
        String pageTitleLocator = "new UiSelector().text(\"Geo Location\")";
        String QRScannerPermission = "com.android.permissioncontroller:id/permission_allow_foreground_only_button";

        boolean pageTitleDisplayed = testContextMenuItemWithPermission(menuItemId, QRScannerPermission,pageTitleLocator);
        assertTrue(pageTitleDisplayed, "No se muestra la pantalla de Geo Location.");
    }

    @Test
    public void testMenuItemDrawing() {
        String menuItemId = "menu item drawing";
        String pageTitleLocator = "new UiSelector().text(\"Drawing\")";

        boolean pageTitleDisplayed = testContextMenuItem(menuItemId, pageTitleLocator);
        assertTrue(pageTitleDisplayed, "No se muestra la pantalla de Drawing.");
    }

    @Test
    public void testMenuItemAbout() {
        String menuItemId = "menu item about";
        String pageTitleLocator = "new UiSelector().text(\"About\")";

        boolean pageTitleDisplayed = testContextMenuItem(menuItemId, pageTitleLocator);
        assertTrue(pageTitleDisplayed, "No se muestra la pantalla de About.");
    }

    @Test
    public void testMenuItemResetApp() {
        final String TEXTO_ESPERADO = "Reset App State";

        String menuItemId = "menu item reset app";

        String textoActual = testContextMenuItemPopup(menuItemId);

        assertEquals(TEXTO_ESPERADO, textoActual, "El texto del alertTitle no coincide con el esperado.");
    }

    @Test
    public void testMenuItemBiometrics() {
        String menuItemId = "menu item biometrics";
        String pageTitleLocator = "new UiSelector().text(\"FingerPrint\")";

        boolean pageTitleDisplayed = testContextMenuItem(menuItemId, pageTitleLocator);
        assertTrue(pageTitleDisplayed, "No se muestra la pantalla de Biometrics.");
    }

    @Test
    public void testMenuItemLogIn() {
        String menuItemId = "menu item log in";
        String pageTitleLocator = "new UiSelector().text(\"Login\")"; // Típicamente el título de la página de inicio de sesión

        boolean pageTitleDisplayed = testContextMenuItem(menuItemId, pageTitleLocator);
        assertTrue(pageTitleDisplayed, "No se muestra la pantalla de Log In.");
    }

    @Test
    public void testMenuItemLogOut() {
        final String TEXTO_ESPERADO = "Log Out";

        String menuItemId = "menu item log out";

        String textoActual = testContextMenuItemPopup(menuItemId);

        assertEquals(TEXTO_ESPERADO, textoActual, "El texto del alertTitle no coincide con el esperado.");
    }

    @Test
    public void testMenuItemApiCalls() {
        String menuItemId = "menu item api calls";
        String pageTitleLocator = "new UiSelector().text(\"API calls\")";

        boolean pageTitleDisplayed = testContextMenuItem(menuItemId, pageTitleLocator);
        assertTrue(pageTitleDisplayed, "No se muestra la pantalla de API Calls.");
    }

    @Test
    public void testMenuItemSauceBotVideo() {
        String menuItemId = "menu item sauce bot video";
        String pageTitleLocator = "new UiSelector().text(\"SauceBot - The Beginning\").instance(0)";

        boolean pageTitleDisplayed = testContextMenuItem(menuItemId, pageTitleLocator);
        assertTrue(pageTitleDisplayed, "No se muestra la pantalla de Sauce Bot Video.");
    }

    public boolean testContextMenuItem(String itemId, String tittleId){

        // 1. Tap en el item del cm
        System.out.println("Hago tap en el item del menú contextual");
        driver.findElement(AppiumBy.accessibilityId(itemId)).click();

        // 2. Compruebo la visibilidad del título
        boolean pageTitle = driver.findElement(AppiumBy.androidUIAutomator(tittleId)).isDisplayed();

        return pageTitle;
    }

    public boolean testContextMenuItemWithPermission(String itemId, String permissionId,String tittleId){

        // 1. Tap en el item del cm
        System.out.println("Hago tap en el item del menú contextual");
        driver.findElement(AppiumBy.accessibilityId(itemId)).click();

        // 2. Tap en el botón de dar permisos
        System.out.println("Hago tap en el botón de dar permisos");
        driver.findElement(AppiumBy.id(permissionId)).click();

        // 2. Compruebo la visibilidad del título
        boolean pageTitle = driver.findElement(AppiumBy.androidUIAutomator(tittleId)).isDisplayed();

        return pageTitle;
    }

    public String testContextMenuItemPopup(String itemId){

        // 1. Tap en el item del cm
        System.out.println("Hago tap en el item del menú contextual");
        driver.findElement(AppiumBy.accessibilityId(itemId)).click();

        System.out.println("Obtenemos el tittle del popup");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement alertTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.id("android:id/alertTitle")
        ));

        System.out.println("Obtenemos el texto y comprobamos que sea correcto");
        String textoActual = alertTitleElement.getText();

        return textoActual;
    }

}
