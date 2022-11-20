package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class SelenideTest {

    public static final String REPO = "selenide/selenide-appium";

    @Test
    void issueSearchTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");
        $(".header-search-input").setValue(REPO).pressEnter();
        $(linkText(REPO)).click();
        $("#issues-tab").click();
        $(withText("#86")).should(Condition.exist);
    }
}
