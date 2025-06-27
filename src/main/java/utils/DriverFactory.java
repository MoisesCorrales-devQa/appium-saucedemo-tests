package utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverFactory {

    public static AndroidDriver createDriver() {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setDeviceName("PRFUTCXGJB4LKRWS");
        options.setPlatformVersion("14");
        options.setAutomationName("UiAutomator2");

        options.setApp("C:\\Users\\Usuario\\Desktop\\Appium\\Android-MyDemoAppRN.1.3.0.build-244.apk");

        options.setNewCommandTimeout(Duration.ofSeconds(60));

        AndroidDriver driver = null;

        try {
            URL appiumServerURL = new URL("http://127.0.0.1:4723/");
            driver = new AndroidDriver(appiumServerURL, options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error en la URL del servidor Appium");
        }

        return driver;
    }
}

