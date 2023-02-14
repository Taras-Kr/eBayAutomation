package com.taraskrasitskyi.ebay.ui.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class ResetPasswordPage extends BasePage {

    private final String RECEIVE_EMAIL_BUTTON_CSS_SELECTOR = "button#emailWithCode-btn";
    private final String RECEIVE_TEXT_BUTTON_CSS_SELECTOR = "button#text-btn";

    @Step("ResetPasswordPage: Check if 'Receive email' button is displayed")
    public boolean isReceiveEmailButtonDisplayed() {
        return $(RECEIVE_EMAIL_BUTTON_CSS_SELECTOR).isDisplayed();
    }

    @Step("ResetPasswordPage: Check if 'Receive text' button is displayed")
    public boolean isReceiveTextButtonDisplayed() {
        return $(RECEIVE_TEXT_BUTTON_CSS_SELECTOR).isDisplayed();
    }
}
