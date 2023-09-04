package com.selenide.qa.selenide.pages.components;

import com.codeborne.selenide.SelenideElement;

import java.util.Map;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationFormModalResult {

    private final SelenideElement modal = $(".modal-content"),
            content = $(".table-responsive");

    public void checkPresenceOfModal() {
        modal.shouldNotBe(hidden);
    }

    public void checkContentOfModal(Map<String, String> records) {
        for(Map.Entry<String, String> record : records.entrySet()) {
            content.$(byText(record.getKey())).parent().shouldHave(text(record.getValue()));
        }
    }
}
