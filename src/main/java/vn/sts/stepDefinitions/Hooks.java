package vn.sts.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.sts.utils.DriverFactory;

public final class Hooks {

    private static WebDriver webDriver;

    private static final Logger LOGGER = LoggerFactory.getLogger(Hooks.class);

    public static WebDriver getDriver() {
        return webDriver;
    }

    @Before
    public void beforeScenario() {
        try {
            webDriver = DriverFactory.getDriverInstance(DriverManagerType.CHROME);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
    }

    @After
    public void afterScenario() throws InterruptedException {
        Thread.sleep(2000);
        webDriver.close();
    }
}
