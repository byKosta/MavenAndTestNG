package runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/cucumber",
        glue = {"runner.order.cucumber", "runner.order","runner.hooks"},
        plugin = {"pretty"},
        tags = "not @ignore")
public class CucumberTest extends AbstractTestNGCucumberTests {
}

