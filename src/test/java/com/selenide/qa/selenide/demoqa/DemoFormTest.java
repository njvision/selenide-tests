package com.selenide.qa.selenide.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DemoFormTest {

    @Test
    void textBoxValidEmail() {
        Configuration.browser = "edge";
        Configuration.holdBrowserOpen = true;
        open("https://demoqa.com/text-box");
        $("#userName").setValue("Bob Fisher");
        $("#userEmail").setValue("bob.fisher@mail.com");
        $("#currentAddress").setValue("Moldova Republic Of");
        $("#permanentAddress").setValue("Moldova Republic Of");
        $("#submit").pressEnter();
        $("#output").shouldBe(visible)
                .shouldHave(exactText("""
                        Name:Bob Fisher
                        Email:bob.fisher@mail.com
                        Current Address :Moldova Republic Of
                        Permananet Address :Moldova Republic Of"""));
    }

    @Test
    void textBoxInvalidEmail() {
        Configuration.browser = "edge";
        Configuration.holdBrowserOpen = true;
        open("https://demoqa.com/text-box");
        $("#userName").setValue("Bob Fisher");
        $("#userEmail").setValue("bob.fisher@mail");
        $("#currentAddress").setValue("Moldova Republic Of");
        $("#permanentAddress").setValue("Moldova Republic Of");
        $("#submit").pressEnter();
        $("#output").shouldBe(empty);
        $("#userEmail").shouldHave(cssValue("border-bottom-color", "rgba(255, 0, 0, 1)"));
    }
}
