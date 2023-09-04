package com.selenide.qa.selenide.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import com.selenide.qa.selenide.pages.components.RegistrationFormCalendar;
import com.selenide.qa.selenide.pages.components.RegistrationFormModalResult;

import java.io.File;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationFormPage {

    RegistrationFormCalendar calendar = new RegistrationFormCalendar();
    RegistrationFormModalResult modalResult = new RegistrationFormModalResult();
    private final SelenideElement firstName = $("#firstName"),
            lastName = $("#lastName"),
            email = $("#userEmail"),
            gender = $("#genterWrapper"),
            phone = $("#userNumber"),
            upload = $("#uploadPicture"),
            address = $("#currentAddress"),
            state = $("#stateCity-wrapper"),
            buttonSubmit = $("#submit");

    private final ElementsCollection hobbies = $$("#hobbiesWrapper div");


    public RegistrationFormPage openPage() {
        open("https://demoqa.com/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    public RegistrationFormPage setUserFirstName(String name) {
        firstName.setValue(name);
        return this;
    }

    public RegistrationFormPage setUserLastName(String name) {
        lastName.setValue(name);
        return this;
    }

    public RegistrationFormPage setEmail(String email) {
        this.email.setValue(email);
        return this;
    }

    public RegistrationFormPage selectRadioButton(String gender) {
        this.gender.$(byText(gender)).ancestor("div")
                .$("label.custom-control-label").click();
        return this;
    }

    public RegistrationFormPage setPhoneNumber(String phoneNumber) {
        phone.setValue(phoneNumber);
        return this;
    }

    public RegistrationFormPage setDateOfBirth(String day, String month, String year) {
        calendar.pickDateFromCalendar(day, month, year);
        return this;
    }

    public RegistrationFormPage setSubject(String character) {
        $("#subjectsContainer").click();
        $("#subjectsInput").setValue(character).pressEnter();
        return this;
    }

    public RegistrationFormPage setHobbies(List<String> hobbies) {
        hobbies.forEach(x -> this.hobbies.get(1).$(Selectors.byText(x)).click());
        return this;
    }

    public RegistrationFormPage uploadImage(String path) {
        upload.uploadFile(new File(path));
        return this;
    }

    public RegistrationFormPage setAddress(String address) {
        this.address.setValue(address);
        return this;
    }

    public RegistrationFormPage setState(String state, String city) {
        this.state.$(byText("Select State")).click();
        this.state.$(byText(state)).click();
        this.state.$(byText("Select City")).click();
        this.state.$(byText(city)).click();
        return this;
    }

    public RegistrationFormPage submitForm() {
        buttonSubmit.submit();
        return this;
    }

    public void checkModalResults(Map<String, String> content) {
        modalResult.checkPresenceOfModal();
        modalResult.checkContentOfModal(content);
    }
}
