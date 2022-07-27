package vn.sts.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import vn.sts.pageObject.LoginPage;
import vn.sts.utils.BaseTestStep;

public class LoginStep extends BaseTestStep {

    private final LoginPage loginPage;

    public LoginStep() {
        super();
        loginPage = new LoginPage(driver);
    }

    @Given("^Open the Chrome and launch the application$")
    public void open_the_Chrome_and_launch_the_application() throws Throwable {
        this.loginPage.open();
    }

    @When("^Enter the Username and Password$")
    public void enter_the_Username_and_Password() throws Throwable {
        this.loginPage.enterLoginInfo("robin@gmail.com", "123456");
    }

    @Then("^Login successfully$")
    public void Reset_the_credential() throws Throwable {
        this.loginPage.clickLoginButton();
    }
}
