package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import tests.properties.BrowserPropertiesS;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static io.qameta.allure.Allure.step;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        String remoteBrowserUser = System.getProperty("remote_browser_user");
        String remoteBrowserPassword = System.getProperty("remote_browser_password");

        step("Настраиваем тестируемую страницу", () -> {
            Configuration.baseUrl = "https://demoqa.com";
            Configuration.browser = System.getProperty("browser", "chrome");
            Configuration.browserSize = System.getProperty("browser_size", "3840x2160");
           // String remoteBrowserAddress = System.getProperty("remote_browser", "selenoid.autotests.cloud/wd/hub");

            Configuration.remote = "https://" + remoteBrowserUser + ":" + remoteBrowserPassword + "@" + System.getProperty("remote_browser");
            System.out.println("u/p " + Configuration.remote);

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
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