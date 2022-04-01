package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import tests.config.ApiConfig;
import tests.config.AuthConfig;
import tests.config.BrowserConfig;
import org.aeonbits.owner.ConfigFactory;
import tests.config.Project;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static io.qameta.allure.Allure.step;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        // String remoteBrowserUser = System.getProperty("remote_browser_user");
        //String remoteBrowserPassword = System.getProperty("remote_browser_password");

        step("Настраиваем тестируемую страницу", () -> {
            ApiConfig apiConfig = ConfigFactory.create(ApiConfig.class, System.getProperties());
            Configuration.baseUrl = apiConfig.baseUrl();

            AuthConfig authConfig = ConfigFactory.create(AuthConfig.class, System.getProperties());

            BrowserConfig browserConfig = ConfigFactory.create(BrowserConfig.class, System.getProperties());
            Configuration.browser = browserConfig.browser();
            Configuration.browserSize = browserConfig.browserSize();

            DesiredCapabilities capabilities = new DesiredCapabilities();
            ChromeOptions chromeOptions = new ChromeOptions();

            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--disable-infobars");
            chromeOptions.addArguments("--disable-popup-blocking");
            chromeOptions.addArguments("--disable-notifications");
            chromeOptions.addArguments("--lang=en-en");

            if (Project.isRemoteWebDriver()) {
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("enableVideo", true);
                Configuration.remote = "https://" + authConfig.username() + ":" + authConfig.password() + "@" + browserConfig.remoteBrowser();
            }

            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
            Configuration.browserCapabilities = capabilities;
        });
    }

    @AfterEach
    void addAttachments() {
        step("Прикрепляем атачи результата теста", () -> {
//        Attach.attachAsText("Add message to end of th test", "some_message");
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            Attach.browserConsoleLogs();
            Attach.addVideo();
            closeWebDriver();
        });
    }
}