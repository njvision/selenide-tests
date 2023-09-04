package com.selenide.qa.selenide.pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class RegistrationFormCalendar {

    private final SelenideElement dateOfBirthField = $("#dateOfBirthInput"),
            month = $(".react-datepicker__month-select"),
            year = $(".react-datepicker__year-select");

    public void pickDateFromCalendar(String day, String month, String year) {
        dateOfBirthField.click();
        this.month.selectOption(month);
        this.year.selectOption(year);
        $(".react-datepicker__day--0" + day + ":not(.react-datepicker__day--outside-month)").click();
    }
}
