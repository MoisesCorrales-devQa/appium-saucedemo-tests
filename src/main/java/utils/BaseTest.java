package utils;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import java.util.Map;

public class BaseTest {

    protected static AndroidDriver driver;

    @BeforeAll
    public static void setUp() {
        driver = DriverFactory.createDriver();
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterEach
    public void resetAppAfterEachTest() {
        try {
            System.out.println("Esperando 3 segundos antes de borrar datos de la app");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("La espera fue interrumpida.");
        }

        if (driver != null) {
            String appPackage = "com.saucelabs.mydemoapp.rn";
            System.out.println();
            System.out.println("Borrando datos de la aplicación: " + appPackage);
            try {
                driver.executeScript("mobile: shell", Map.of("command", "pm clear " + appPackage));

                System.out.println("Datos de la aplicación borrados. Reactivando la app...");
                driver.activateApp(appPackage);

            } catch (Exception e) {
                System.err.println("Error al intentar borrar datos de la app o reactivarla: " + e.getMessage());

                throw new RuntimeException("Fallo al resetear completamente la aplicación entre tests", e);
            }
        }
    }
}
