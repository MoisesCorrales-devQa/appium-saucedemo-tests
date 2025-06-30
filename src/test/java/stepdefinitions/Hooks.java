package stepdefinitions;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.DriverFactory;
import utils.ScenarioContext;

public class Hooks {

    public static AndroidDriver driver;

    public static ScenarioContext context;

    @Before
    public void setUp() {
        driver = DriverFactory.createDriver();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
