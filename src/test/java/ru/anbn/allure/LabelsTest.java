package ru.anbn.allure;

import io.qameta.allure.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LabelsTest {

        // информация о человеке ответственном за тест (всегда добавлять)
        @Owner("eroshenkoam")
        // серьезность теста
        @Severity(SeverityLevel.MINOR)
        // фичастору. группируем по ней тесты и потом легче с ними разбираться
        @Feature("Tasks in the repository")
        @Story("Creating a new task")
        // название теста
        @DisplayName("Checking Issue creation for an authorized user")
        // описание теста
        @Description("The test checks the Issue creation when ...")
        // ссылка на репозиторий
        @Link(value = "Testing", url = "https://github.com")


        // это более продвинутый подход, можно использовать конструкции if и т.д.
        // те же самые задачи которые приведены вверху можно решить так ->
        @Test
        public void testDynamicLabels() {
                Allure.label("owner", "eroshenkoam");
                Allure.label("severity", SeverityLevel.CRITICAL.value());
                Allure.feature("Tasks in the repository");
                Allure.story("Deleting a new task");
                Allure.getLifecycle().updateTestCase(testCase -> {
                        testCase.setName("Checking deleting an Issue for an authorized user");
                });
                Allure.description("This test checks the Issue creation when it occurs....");
                Allure.link("Testing", "https://github.com");
        }


        // это более продвинутый подход, можно использовать конструкции if и т.д.
        // те же самые задачи которые приведены вверху можно решить так ->
        @Test
        public void testParameters() {
                Allure.parameter("Region", "Moscov area");
                Allure.parameter("City", "Moscov");

        }

}