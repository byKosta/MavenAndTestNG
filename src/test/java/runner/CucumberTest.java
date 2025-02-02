package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/cucumber",
        glue = {"cucumber","runner"}, // Исправленный путь к пакету с шагами
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json"
        },
        tags = "not @ignore"
)
public class CucumberTest extends AbstractTestNGCucumberTests {
}
