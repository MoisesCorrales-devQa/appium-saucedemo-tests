package stepdefinitions;

import io.appium.java_client.AppiumBy;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;
import static stepdefinitions.Hooks.driver;

public class ContextMenuSteps {

    static class Selection{
        String itemId = "";
        String tituloSelector = "";
        String permisoId = null;
        boolean popup = false;
        String textoPopup = "";
    }

    Selection selection = new Selection();

    @Given("el usuario abre la app y accede al menú contextual")
    public void accesoAlMenuContextual() {
        driver.findElement(AppiumBy.accessibilityId("open menu")).click();
    }

    @When("el user accede al item {string}")
    public void elUserAccedeAlItem(String nombreItem) {

        switch (nombreItem.toLowerCase()) {
            case "catálogo":
                selection.itemId = "menu item catalog";
                selection.tituloSelector = "new UiSelector().text(\"Products\")";
                break;
            case "webview":
                selection.itemId = "menu item webview";
                selection.tituloSelector = "new UiSelector().text(\"Webview\")";
                break;
            case "qr":
                selection.itemId = "menu item qr code scanner";
                selection.tituloSelector = "new UiSelector().text(\"QR Code Scanner\")";
                selection.permisoId = "com.android.permissioncontroller:id/permission_allow_foreground_only_button";
                break;
            case "geolocalización":
                selection.itemId = "menu item geo location";
                selection.tituloSelector = "new UiSelector().text(\"Geo Location\")";
                selection.permisoId = "com.android.permissioncontroller:id/permission_allow_foreground_only_button";
                break;
            case "dibujo":
                selection.itemId = "menu item drawing";
                selection.tituloSelector = "new UiSelector().text(\"Drawing\")";
                break;
            case "acerca de":
                selection.itemId = "menu item about";
                selection.tituloSelector = "new UiSelector().text(\"About\")";
                break;
            case "reset app":
                selection.itemId = "menu item reset app";
                selection.popup = true;
                selection.textoPopup = "Reset App State";
                break;
            case "biometría":
                selection.itemId = "menu item biometrics";
                selection.tituloSelector = "new UiSelector().text(\"FingerPrint\")";
                break;
            case "iniciar sesión":
                selection.itemId = "menu item log in";
                selection.tituloSelector = "new UiSelector().text(\"Login\")";
                break;
            case "cerrar sesión":
                selection.itemId = "menu item log out";
                selection.popup = true;
                selection.textoPopup = "Log Out";
                break;
            case "api":
                selection.itemId = "menu item api calls";
                selection.tituloSelector = "new UiSelector().text(\"API calls\")";
                break;
            case "video sauce bot":
                selection.itemId = "menu item sauce bot video";
                selection.tituloSelector = "new UiSelector().text(\"SauceBot - The Beginning\").instance(0)";
                break;
            default:
                throw new IllegalArgumentException("Item desconocido: " + nombreItem);
        }


        tapEnElItem(selection.itemId);

    }

    public void tapEnElItem(String itemId) {
        System.out.println("Hago tap en el item del menú contextual");
        driver.findElement(AppiumBy.accessibilityId(itemId)).click();
    }

    @Then("se muestra la pantalla de {string}")
    public void testContextMenuItemScreen(String itemId) {

        if (selection.permisoId != null) {
            driver.findElement(AppiumBy.id(selection.permisoId)).click();
        }

        boolean pageTitle = driver.findElement(AppiumBy.androidUIAutomator(selection.tituloSelector)).isDisplayed();

        assertTrue(pageTitle);
    }

    @Then("se muestra el popup de {string}")
    public void testPopupContextMenuItemScreen(String itemId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement alertTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.id("android:id/alertTitle")
        ));

        String textoActual = alertTitleElement.getText();
        assertTrue(textoActual.equals(selection.textoPopup));

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
