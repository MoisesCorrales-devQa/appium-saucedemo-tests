package utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Capabilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvironmentWriter {

    public static void write(AndroidDriver driver) {
        Properties props = new Properties();

        try {
            Capabilities caps = driver.getCapabilities();

            props.setProperty("Platform", "Android");
            props.setProperty("Platform.Version", getCapability(caps, "platformVersion"));
            props.setProperty("Device.Name", getCapability(caps, "deviceName"));
            props.setProperty("Automation.Name", getCapability(caps, "automationName"));
            props.setProperty("App.Package", safe(driver.getCurrentPackage()));
            props.setProperty("App.Activity", safe(driver.currentActivity()));
        } catch (Exception e) {
            System.err.println("[Allure] No se pudieron obtener algunas capacidades del dispositivo.");
            e.printStackTrace();
        }

        props.setProperty("Test.Environment", System.getenv("TEST_ENV") != null
                ? System.getenv("TEST_ENV")
                : "Local");

        props.setProperty("OS", System.getProperty("os.name"));
        props.setProperty("Java.Version", System.getProperty("java.version"));
        props.setProperty("User", System.getProperty("user.name"));

        File resultsDir = new File("target/allure-results");
        if (!resultsDir.exists()) {
            resultsDir.mkdirs();
        }

        try (FileOutputStream fos = new FileOutputStream(
                new File(resultsDir, "environment.properties"))) {
            props.store(fos, "Allure Mobile Environment Info");
            System.out.println("[Allure] environment.properties generado correctamente.");
        } catch (IOException e) {
            System.err.println("[Allure] Error al escribir environment.properties:");
            e.printStackTrace();
        }
    }

    private static String getCapability(Capabilities caps, String key) {
        Object value = caps.getCapability(key);
        return value != null ? value.toString() : "Unknown";
    }

    private static String safe(String value) {
        return value != null ? value : "Unknown";
    }
}
