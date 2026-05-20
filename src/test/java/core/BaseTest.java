package core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {

    public void setUp() {
        Configuration.browser = "chrome"; // Оставляем chrome, Selenide сам подтянет Chromium в Linux
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
//        Configuration.headless = false;
//        Configuration.pageLoadStrategy = "eager";
        Configuration.headless = true;

        String remoteUrl = System.getenv("SELENIUM_REMOTE_URL");
        if (remoteUrl != null && !remoteUrl.isEmpty()) {
            Configuration.remote = remoteUrl;
        }

        SelenideLogger.addListener(
                "allure",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
        );
    }

    @BeforeEach
    public void init() {
        setUp();
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
