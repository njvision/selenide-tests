package com.selenide.qa.selenide.github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BestContributorTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "edge";
    }

    @Test
    void checkBestContributor() {
        Configuration.holdBrowserOpen = true;
        open("https://github.com/selenide/selenide");
        $(".BorderGrid").$(byText("Contributors")).ancestor(".BorderGrid-row")
                .$$("ul li").first().hover();
        $(".Popover-message").shouldBe(visible).$("[aria-label='User login and name']")
                .shouldHave(text("asolntsev Andrei Solntsev"));
    }
}
