package ru.anbn.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;

public class SelenideTest {

    @BeforeEach
    void beforeEach() {
        open("https://github.com");
    }

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }

    @Test
    public void testIssueSearch() {
        // подключаем logger для красивого отображения шагов в отчете
        SelenideLogger.addListener("allure", new AllureSelenide());

        $(".header-search-input").click();
        $(".header-search-input").sendKeys("eroshenkoam/allure");
        $(".header-search-input").submit();

        // клик по ссылке
        $(byLinkText("eroshenkoam/allure-example")).click();
        // кликаем на вледенный в тег <а> тег <span> со словом Issues
        $(By.partialLinkText("Issues")).click();
        // проверим что элемент с индексом #68 присутствует
        $(withText("#68")).should(Condition.exist);


        //sleep(2000);

    }

}
