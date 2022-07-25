package vn.sts.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    public WebDriver driver;

    private WebElement element;

    public LoginPage(WebDriver dr) {
        driver = dr;
    }

    public WebElement ip_username() {
        element = driver.findElement(By.xpath("//input[@id='usr']"));
        return element;
    }

    public WebElement ip_password() {
        element = driver.findElement(By.xpath("//input[@id='pwd']"));
        return element;
    }

    public WebElement btn_login() {
        element = driver.findElement(By.xpath("//input[@type='submit']"));
        return element;
    }

    public WebElement txt_successfully() {
        element = driver.findElement(By.xpath("//h3[@class='success']"));
        return element;
    }
}
