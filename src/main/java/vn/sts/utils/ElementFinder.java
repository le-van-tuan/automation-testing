package vn.sts.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementFinder {

    private static WebDriver driver;

    public static void init(WebDriver wd) {
        if (driver == null) {
            driver = wd;
        }
    }

    public static WebElement findElementById(String id) {
        return driver.findElement(By.id(id));
    }

    public static WebElement findElementByName(String name) {
        return driver.findElement(By.name(name));
    }

    public static WebElement findElementByClassName(String className) {
        return driver.findElement(By.className(className));
    }

    public static WebElement findElementByCssSelector(String cssSelector) {
        return driver.findElement(By.cssSelector(cssSelector));
    }

    public static WebElement findElementByXpath(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }
}
