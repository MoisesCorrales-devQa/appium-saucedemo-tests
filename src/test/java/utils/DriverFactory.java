package utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class DriverFactory {

    public static AndroidDriver createDriver() {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setDeviceName(ConfigLoader.get("deviceName"));
        options.setPlatformVersion(ConfigLoader.get("platformVersion"));
        options.setAutomationName(ConfigLoader.get("automationName"));
        options.setApp(ConfigLoader.get("appPath"));

        long timeout = Long.parseLong(ConfigLoader.get("newCommandTimeout"));
        options.setNewCommandTimeout(Duration.ofSeconds(timeout));

        AndroidDriver driver = null;

        try {
            URL appiumServerURL = new URL(ConfigLoader.get("appiumServerURL"));
            driver = new AndroidDriver(appiumServerURL, options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            EnvironmentWriter.write(driver);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error en la URL del servidor Appium");
        }

        return driver;
    }

}
