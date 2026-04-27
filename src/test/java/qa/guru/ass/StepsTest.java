package qa.guru.ass;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsTest extends TestBase {

    private static final String REPOSITORY = "AbramovaSS/HomeworkAllureReports_9";
    private static final int ISSUE = 1;

    @BeforeAll
    static void testPrecondition() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.browser = "chrome";
        Configuration.pageLoadStrategy = "eager";
    }

    @BeforeEach
    public void beforeEachTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @DisplayName("Проверка названия Issue в репозитории: Lambda-шаги через step")
    @Test
    public void testLambdaStep() {

        step("Перейти на главную страницу веб-сервиса GitHub", () -> {
            open("");
        });
        step("Найти репозиторий " + REPOSITORY, () -> {
            $(".search-input-container").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").submit();
        });
        step("Нажать на ссылку репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Нажать на таб Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверить наличие названия Issue с номером " + ISSUE, () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });
    }

    @DisplayName("Проверка названия Issue в репозитории: Шаги с аннотацией @Step")
    @Test
    public void testAnnotatedStep() {

        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE);

    }


}
