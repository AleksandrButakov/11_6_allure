package homework;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.*;

public class LambdaStepTest {

    private static final String REPOSITORY = "AleksandrButakov/11_7_allure";

    @BeforeAll
    static void beforeAll() {
        baseUrl = "https://github.com";
        browserPosition = "0x0";
        browserSize = "1920x1080";
    }

    @BeforeEach
    void beforeEach() {
        open(baseUrl);
    }

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }

    @Test
    public void testLambdaSteps() {
        // подключаем logger для красивого отображения шагов в отчете
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open the main page", () -> {
            open(baseUrl);
        });

        step("Repository search " + REPOSITORY, () -> {
            $("[data-test-selector='nav-search-input']").click();
            $("[data-test-selector='nav-search-input']").sendKeys(REPOSITORY);
            $("[data-test-selector='nav-search-input']").submit();
        });

        step("Open the repository " + REPOSITORY, () -> {
            $(byLinkText("AleksandrButakov/11_7_allure")).click();
        });

        step("Checking for the Issue tab", () -> {
            $(By.partialLinkText("Issue")).click();
            Allure.addAttachment("Page Source", "text/html", WebDriverRunner.source(), "html");
        });

    }

}
