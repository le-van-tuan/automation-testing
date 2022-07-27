package vn.sts.utils;

import org.openqa.selenium.WebDriver;
import vn.sts.stepDefinitions.Hooks;

public class BaseTestStep {

    protected WebDriver driver;

    public BaseTestStep() {
        this.driver = Hooks.getDriver();
    }
}
