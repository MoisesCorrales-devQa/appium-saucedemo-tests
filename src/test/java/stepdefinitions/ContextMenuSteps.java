package stepdefinitions;

import io.cucumber.java.en.*;
import io.qameta.allure.*;
import org.junit.Assert;
import pages.ContextMenuPage;

import static stepdefinitions.Hooks.driver;

@Epic("Menú contextual")
@Feature("Navegación mediante menú contextual y popups")
public class ContextMenuSteps {

    private final ContextMenuPage contextMenuPage = new ContextMenuPage(driver);

    private static class Selection {
        String itemId = "";
        String tituloSelector = "";
        String permisoId = null;
        boolean popup = false;
        String textoPopup = "";
    }

    private Selection selection = new Selection();

    @Step("El usuario abre la app y accede al menú contextual")
    @Given("el usuario abre la app y accede al menú contextual")
    public void accesoAlMenuContextual() {
        contextMenuPage.abrirMenu();
    }

    @Step("El usuario accede al item {nombreItem}")
    @When("el user accede al item {string}")
    public void elUserAccedeAlItem(String nombreItem) {
        // Configuración de selección según el item
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

        contextMenuPage.tapEnItem(selection.itemId);
    }

    @Step("Se muestra la pantalla correcta para el item {itemId}")
    @Then("se muestra la pantalla de {string}")
    public void testContextMenuItemScreen(String itemId) {
        contextMenuPage.aceptarPermisoSiEsNecesario(selection.permisoId);

        boolean pantallaBiometria = contextMenuPage.tituloPantallaVisible(selection.tituloSelector);

        if (!pantallaBiometria && itemId.equalsIgnoreCase("biometría")) {
            // Ajusta el texto según el popup real de tu app
            String popupEsperado = "Biometrics is or not supported or not enabled";
            boolean popupVisible = contextMenuPage.isPopupWithTextVisible(popupEsperado);
            Assert.assertTrue("Ni pantalla de biometría ni popup de error detectado", popupVisible);
        } else {
            Assert.assertTrue(pantallaBiometria);
        }
    }

    @Step("Se muestra el popup correcto para el item {itemId}")
    @Then("se muestra el popup de {string}")
    public void testPopupContextMenuItemScreen(String itemId) {
        String textoActual = contextMenuPage.esperarPopupTitulo();
        Assert.assertEquals(selection.textoPopup, textoActual);
    }
}