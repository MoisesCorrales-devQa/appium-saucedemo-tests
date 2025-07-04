package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.*;

public class ContextMenuPage extends BasePage {

    public ContextMenuPage(WebDriver driver) {
        super(driver);
    }

    // =============== SELECTORS ===============
    private final By openMenuBtn = AppiumBy.accessibilityId("open menu");
    private By menuItemSelector(String accessibilityId) {
        return AppiumBy.accessibilityId(accessibilityId);
    }
    private By permisoBtn(String permisoId) {
        return AppiumBy.id(permisoId);
    }
    private By tituloPantalla(String tituloSelector) {
        return AppiumBy.androidUIAutomator(tituloSelector);
    }
    private final By alertTitleSelector = AppiumBy.id("android:id/alertTitle");

    private final By alertMessageSelector = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"android:id/message\")");

    public void abrirMenu() {
        click(openMenuBtn);
    }

    public void tapEnItem(String accessibilityId) {
        click(menuItemSelector(accessibilityId));
    }

    public void aceptarPermisoSiEsNecesario(String permisoId) {
        if (permisoId != null) {
            click(permisoBtn(permisoId));
        }
    }

    public boolean tituloPantallaVisible(String tituloSelectorString) {
        return isVisible(tituloPantalla(tituloSelectorString));
    }

    public String esperarPopupTitulo() {
        return getText(alertTitleSelector);
    }

    public boolean isPopupWithTextVisible(String text) {
        try {
            String actualText = getText(alertMessageSelector);
            return actualText != null && actualText.contains(text);
        } catch (Exception e) {
            return false;
        }
    }
}