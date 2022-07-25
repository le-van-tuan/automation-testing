package vn.sts.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStep {

    @Given("^user navigates to hehe.com$")
    public void navigatePage() throws Throwable {
        System.out.println("hi");
    }

    @When("^user logs in using Username as “USER” and Password “PASSWORD”$")
    public void enterUsernameAndPassword() throws Throwable {

    }

    @Then("^login should be successful$")
    public void validateLoginSuccess() throws Throwable {

    }
}
