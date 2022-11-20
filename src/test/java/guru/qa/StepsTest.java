package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsTest {

    WebSteps webSteps = new WebSteps();
    public static final String REPO = "selenide/selenide-appium";
    public static final int ISSUE = 86;

    @Test
    void issueSearchStepsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () ->
                open("https://github.com"));
        step("Ищем репозиторий " + REPO, () ->
                $(".header-search-input").setValue(REPO).pressEnter());
        step("Кликаем по ссылке репозитория " + REPO, () ->
                $(linkText(REPO)).click());
        step("Открываем вкладку Issues", () ->
                $("#issues-tab").click());
        step("Проверяем наличие Issue с номером " + ISSUE, () ->
                $(withText("#" + ISSUE)).should(Condition.exist));
    }

    @Test
    public void testAnnotatedStep() {
        webSteps.openMainPage();
        webSteps.searchRepository(REPO);
        webSteps.clickOnRepositoryLink(REPO);
        webSteps.openIssuesTab();
        webSteps.checkIssueWithNumber(ISSUE);
    }
}
