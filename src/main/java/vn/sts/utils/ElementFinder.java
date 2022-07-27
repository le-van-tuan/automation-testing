package vn.sts.utils;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementFinder {

    private static WebDriver driver;

    public static void initElements(WebDriver dr) {
        if (driver == null) {
            driver = dr;
        }
    }

    public static WebElement find(String selector) {
        if (StringUtils.isEmpty(selector)) {
            throw new RuntimeException("Selector should not be null ==^.^==");
        }
        if (selector.trim().startsWith("#")) {
            return findElementById(selector.trim().substring(1));
        } else if (selector.trim().startsWith("/")) {
            return findElementByXpath(selector);
        } else if (selector.trim().split(" ").length == 1 && selector.trim().startsWith(".")) {
            return findElementByClassName(selector.trim().substring(1));
        } else {
            return findElementByCssSelector(selector);
        }
    }

    public static WebElement find(By by) {
        return driver.findElement(by);
    }

    public static WebElement findElementById(String id) {
        return driver.findElement(By.id(id));
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
