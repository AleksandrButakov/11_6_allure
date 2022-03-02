package homework;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {

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
    void testIssueSearch() {
        // подключаем logger для красивого отображения шагов в отчете
        SelenideLogger.addListener("allure", new AllureSelenide());

        // введем запрос для поиска репозитория
        $("[data-test-selector='nav-search-input']").click();
        $("[data-test-selector='nav-search-input']").sendKeys(REPOSITORY);
        $("[data-test-selector='nav-search-input']").submit();

        // клик по ссылке на репозиторий
        $(byLinkText("AleksandrButakov/11_7_allure")).click();

        // проверка наличия вкладки Issue
        $(By.partialLinkText("Issue")).click();

    }

}
