package vn.sts.pageObject;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.sts.utils.ElementFinder;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class BaseTestPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTestPage.class);

    protected final WebDriver driver;

    protected final WebDriverWait waitDriver;

    public BaseTestPage(WebDriver driver) {
        this.driver = driver;
        waitDriver = new WebDriverWait(this.driver, Duration.ofSeconds(20));
        ElementFinder.initElements(driver);
    }

    protected void goTo(String url) {
        this.driver.get(url);
    }

    protected void sleep(long times) {
        try {
            Thread.sleep(times);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected WebElement findElement(String element) {
        return ElementFinder.find(element);
    }

    protected WebElement findElement(By by) {
        return ElementFinder.find(by);
    }

    protected long getDummyWaitNumber() {
        return 2000;
    }

    protected void click(String element) {
        waitDriver.until(ExpectedConditions.elementToBeClickable(findElement(element)));
        findElement(element).click();
    }

    protected void enterText(String element, String text) {
        waitDriver.until(ExpectedConditions.visibilityOf(findElement(element)));
        findElement(element).sendKeys(text);
    }

    protected void clearText(String element) {
        waitDriver.until(ExpectedConditions.visibilityOf(findElement(element)));
        findElement(element).clear();
    }

    protected void screenshot(String selector, String elementName) {
        File scrFile = findElement(selector).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("./captured/" + elementName + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void waitUntilPageLoaded() {
        waitDriver.until((Function<WebDriver, Object>) driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    protected String getPageTitle() {
        waitDriver.until((Function<WebDriver, Object>) driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        String title = driver.getTitle();
        LOGGER.info("Current Page Title: " + title);
        return title;
    }

    protected void switchToDefaultContent() {
        this.sleep(this.getDummyWaitNumber());
        driver.switchTo().defaultContent();
    }

    protected void switchToTab(int position) {
        this.sleep(this.getDummyWaitNumber());
        driver.switchTo().window(driver.getWindowHandles().toArray()[position].toString());
    }

    protected void openNewTab() {
        this.sleep(this.getDummyWaitNumber());
        driver.switchTo().newWindow(WindowType.TAB);
    }

    protected void scrollToElement(String element) {
        this.sleep(this.getDummyWaitNumber());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", findElement(element));
    }

    protected boolean hover(String element) {
        this.sleep(this.getDummyWaitNumber());
        try {
            Actions action = new Actions(driver);
            action.moveToElement(findElement(element)).perform();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected void setWindowSize(int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));
    }

    /**
     * Dropdown functions
     */
    protected void selectOptionByText(String element, String text) {
        waitDriver.until(ExpectedConditions.elementToBeClickable(findElement(element)));
        Select select = new Select(findElement(element));
        select.selectByVisibleText(text);
    }

    protected List<WebElement> getSelectOptions(String element) {
        waitDriver.until(ExpectedConditions.visibilityOf(findElement(element)));
        Select select = new Select(findElement(element));
        return select.getOptions();
    }

    protected List<WebElement> getSelectedOptions(String element) {
        waitDriver.until(ExpectedConditions.visibilityOf(findElement(element)));
        Select select = new Select(findElement(element));
        return select.getAllSelectedOptions();
    }

    /**
     * Robot
     */
    protected void pressEnter() {
        this.waitUntilPageLoaded();
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    protected void pressESC() {
        this.waitUntilPageLoaded();
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Draw a border color around element
     */
    protected WebElement highLightElement(String element, String color) {
        waitDriver.until(ExpectedConditions.visibilityOf(findElement(element)));
        ((JavascriptExecutor) driver).executeScript(String.format("arguments[0].style.border='3px solid %s'", color), findElement(element));
        sleep(1);
        return findElement(element);
    }
}
