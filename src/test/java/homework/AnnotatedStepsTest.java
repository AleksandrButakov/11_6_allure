package homework;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class AnnotatedStepsTest {

    private static final String REPOSITORY = "AleksandrButakov/11_7_allure";
    private static final String ISSUE = "Issue";

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
    public void testAnnotatedSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        WebSteps steps = new WebSteps();
        steps.searchForRepository(REPOSITORY);
        steps.openRepository(REPOSITORY);
        steps.openIssueTab(ISSUE);
    }

}