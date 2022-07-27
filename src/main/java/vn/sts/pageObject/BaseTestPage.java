package vn.sts.pageObject;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.sts.utils.ElementFinder;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
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

    protected void screenshot(String selector, String elementName) {
        File scrFile = findElement(selector).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("./captured/" + elementName + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void waitUntilPageLoaded() {
        waitDriver.until((Function<WebDriver, Object>) driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    public String getPageTitle() {
        waitDriver.until((Function<WebDriver, Object>) driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        String title = driver.getTitle();
        LOGGER.info("Current Page Title: " + title);
        return title;
    }

    public void switchToDefaultContent() {
        this.sleep(this.getDummyWaitNumber());
        driver.switchTo().defaultContent();
    }

    public void switchToTab(int position) {
        this.sleep(this.getDummyWaitNumber());
        driver.switchTo().window(driver.getWindowHandles().toArray()[position].toString());
    }

    public void openNewTab() {
        this.sleep(this.getDummyWaitNumber());
        driver.switchTo().newWindow(WindowType.TAB);
    }

    public void scrollToElement(String element) {
        this.sleep(this.getDummyWaitNumber());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", findElement(element));
    }

    public boolean hover(String element) {
        this.sleep(this.getDummyWaitNumber());
        try {
            Actions action = new Actions(driver);
            action.moveToElement(findElement(element)).perform();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
