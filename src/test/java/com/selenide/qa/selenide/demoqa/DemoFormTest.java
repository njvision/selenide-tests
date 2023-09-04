package com.selenide.qa.selenide.demoqa;

import com.codeborne.selenide.Configuration;
import com.selenide.qa.selenide.pages.RegistrationFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;

public class DemoFormTest {

    public static final String FULLNAME = "Bob Fisher";
    private static final String EMAIL = "bob.fisher@mail.com";
    public static final String COUNTRY = "Moldova Republic Of";

    @BeforeAll
    static void beforeAll() {
        browser = "edge";
        browserSize = "1920x1080";
    }

    @Test
    void textBoxValidEmail() {
        open("https://demoqa.com/text-box");
        $("#userName").setValue(FULLNAME);
        $("#userEmail").setValue(EMAIL);
        $("#currentAddress").setValue(COUNTRY);
        $("#permanentAddress").setValue(COUNTRY);
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
        open("https://demoqa.com/text-box");
        $("#userName").setValue(FULLNAME);
        $("#userEmail").setValue("bob.fisher@mail");
        $("#currentAddress").setValue(COUNTRY);
        $("#permanentAddress").setValue(COUNTRY);
        $("#submit").pressEnter();
        $("#output").shouldBe(empty);
        $("#userEmail").shouldHave(cssValue("border-bottom-color", "rgba(255, 0, 0, 1)"));
    }

    @Test
    void practiceFormValidData() {
        Configuration.holdBrowserOpen = true;
        RegistrationFormPage registrationFormPage = new RegistrationFormPage().openPage();
        $("#firstName").setValue("Bob");
        $("#lastName").setValue("Fisher");
        $("#userEmail").setValue(EMAIL);
        $("#genterWrapper").$(byText("Male")).ancestor("div")
                .$("label.custom-control-label").click();
        $("#userNumber").setValue("1234567890");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("June");
        $(".react-datepicker__year-select").selectOption("2013");
        $(".react-datepicker__day--0" + "05" + ":not(.react-datepicker__day--outside-month)").click();
        $("#subjectsContainer").click();
        $("#subjectsInput").setValue("h").pressEnter();
        $$("#hobbiesWrapper div").get(1).$(byText("Sports")).click();
        $$("#hobbiesWrapper div").get(1).$(byText("Music")).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/ruby-png-transparent.png"));
        $("#currentAddress").setValue(COUNTRY);
        $("#stateCity-wrapper").$(byText("Select State")).click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#stateCity-wrapper").$(byText("Select City")).click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $("#submit").submit();
        $(".modal-content").shouldNotBe(hidden);
        $$(".modal-body table tbody tr td")
                .shouldHave(texts("Student Name", "Bob Fisher", "Student Email",
                        EMAIL, "Gender", "Male", "Mobile", "1234567890", "Date of Birth", "05 June,2013",
                        "Subjects", "Hindi", "Hobbies", "Sports, Music", "Picture", "ruby-png-transparent.png",
                        "Address", COUNTRY, "State and City", "NCR Delhi"));
    }
}
