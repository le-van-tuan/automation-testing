package vn.sts.utils;

import org.openqa.selenium.WebDriver;
import vn.sts.stepDefinitions.Hooks;

public class BaseTestStep {

    public WebDriver driver;

    public BaseTestStep() {
        this.driver = Hooks.getDriver();
        ElementFinder.init(driver);
    }
}
