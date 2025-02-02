package runner.hooks;


import io.cucumber.java.*;

public class TestHooks {

    @BeforeAll
    public static void setupAll() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            // Логирование ошибок или скриншоты
        }
    }
}
