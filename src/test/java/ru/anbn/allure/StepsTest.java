package ru.anbn.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class StepsTest {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final String ISSUE_NUMBER = "#68";

    @BeforeEach
    void beforeEach() {
        //open("https://github.com");
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
            open("https://github.com");
        });

        step("Repository search " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });

        step("Opening the repository " + REPOSITORY, () -> {
            $(byLinkText(REPOSITORY)).click();
        });

        step("Go to in the Tab Issue", () -> {
            $(By.partialLinkText("Issues")).click();
            // аттачмент прикрепляет к выполненному шагу полный код страницы на момент выполнения
            Allure.addAttachment("Page Source", "text/html", WebDriverRunner.source(), "html");
        });

        step("Check that there is an Issue with the number " + ISSUE_NUMBER, () -> {
            $(withText(ISSUE_NUMBER)).should(Condition.exist);
        });

    }


    @Test
    public void testAnnotatedSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        //вот это одинаковое для 10 тестов
        WebSteps steps = new WebSteps();
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.openRepository(REPOSITORY);

        //вот отсюда разное
        steps.openIssueTab();
        steps.shouldSeeIssueWithNumber(ISSUE_NUMBER);

        // делаем скриншот экрана
        steps.takeScreenshot();
    }

}
