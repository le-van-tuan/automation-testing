package vn.sts.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "Features", glue = {"vn.sts.stepDefinitions"}, plugin = {"html:reports/report.html"})
public class Runner {

}
