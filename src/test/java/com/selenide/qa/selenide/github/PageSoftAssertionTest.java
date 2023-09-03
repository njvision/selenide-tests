package com.selenide.qa.selenide.github;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PageSoftAssertionTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "edge";
    }

    @Test
    void checkPresenceOfJUnite5OnPageSoftAssertion() {
        Configuration.holdBrowserOpen = true;
        open("https://github.com/selenide/selenide");
        $("ul.UnderlineNav-body").find(byText("Wiki")).ancestor("li")
                .$("a").click();
        $(".markdown-body").find(byText("Soft assertions")).ancestor("li")
                .$("a").click();
        $(".markdown-body").shouldHave(Condition.text("3. Using JUnit5 extend test class:"));
    }
}
