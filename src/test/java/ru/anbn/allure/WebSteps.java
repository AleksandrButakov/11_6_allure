package ru.anbn.allure;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;

public class WebSteps {

    @Step("Finding the repository {repo}")
    public void searchForRepository(String repo) {
        // введем запрос для поиска репозитория
        $("[data-test-selector='nav-search-input']").click();
        $("[data-test-selector='nav-search-input']").sendKeys(repo);
        $("[data-test-selector='nav-search-input']").submit();
    }

    @Step("Opening the repository {repo}")
    public void openRepository(String repo) {
        $(byLinkText(repo)).click();
    }

    @Step("Go to in the Tab Issue")
    public void openIssueTab(String repo) {
        $(By.partialLinkText(repo)).click();
    }

    // аттачмент скриншота экрана
    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

}