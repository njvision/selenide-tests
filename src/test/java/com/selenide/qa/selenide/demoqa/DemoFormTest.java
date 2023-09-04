package com.selenide.qa.selenide.demoqa;

import com.codeborne.selenide.Configuration;
import com.selenide.qa.selenide.pages.RegistrationFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Selenide.$;
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
        List<String> hobbies = List.of("Sports", "Music");
        Map<String, String> content = Map.of(
                "Student Name", "Bob Fisher",
                "Student Email", EMAIL,
                "Gender", "Male",
                "Mobile", "1234567890",
                "Date of Birth", "05 June,2013",
                "Subjects", "Hindi",
                "Hobbies", "Sports, Music",
                "Picture", "ruby-png-transparent.png",
                "Address", COUNTRY,
                "State and City", "NCR Delhi"
        );
        new RegistrationFormPage().openPage()
                .setUserFirstName("Bob")
                .setUserLastName("Fisher")
                .setEmail(EMAIL)
                .selectRadioButton("Male")
                .setPhoneNumber("1234567890")
                .setDateOfBirth("05", "June", "2013")
                .setSubject("h")
                .setHobbies(hobbies)
                .uploadImage("src/test/resources/ruby-png-transparent.png")
                .setAddress(COUNTRY)
                .setState("NCR", "Delhi")
                .submitForm()
                .checkModalResults(content);
    }
}
