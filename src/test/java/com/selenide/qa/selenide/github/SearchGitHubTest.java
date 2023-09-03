package com.selenide.qa.selenide.github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SearchGitHubTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "firefox";
    }

    @Test
    void searchKeyWordOnGitHub() {
        Configuration.holdBrowserOpen = true;
        open("https://github.com/");
        $(".header-search-button").click();
        $("#query-builder-test").setValue("njvision/").pressEnter();
        $("a[href='/njvision/selenide-pj']").shouldBe(visible).click();
        $("#repository-container-header").shouldHave(text("njvision / selenide-pj"));
    }
}
