package vn.sts.pageObject;

import org.openqa.selenium.WebDriver;

public class LoginPage extends BaseTestPage {

    public LoginPage(WebDriver dr) {
        super(dr);
    }

    public void open() {
        this.goTo("https://selenium-training.herokuapp.com");
    }

    public void enterLoginInfo(String username, String password) {
        enterText("#session_email", username);
        enterText("#session_password", password);
    }

    public void clickLoginButton() {
        click("/html/body/div/div/div/form/input[5]");
    }
}
