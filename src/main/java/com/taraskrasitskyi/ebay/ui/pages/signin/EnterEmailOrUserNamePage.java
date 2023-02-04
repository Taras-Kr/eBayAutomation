package com.taraskrasitskyi.ebay.ui.pages.signin;

import com.codeborne.selenide.SelenideElement;
import com.taraskrasitskyi.ebay.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class EnterEmailOrUserNamePage extends BasePage {

    private final String EMAIL_OR_USER_NAME_INPUT_CSS_SELECTOR = "input#userid";

    @Step("UserNameSignInPage: Get header text")
    public String getHeaderText() {
        return $x("//h1").getText();
    }

    @Step("EnterEmailOrUserNamePage: Get sub header text")
    public String getSubHeaderText() {
        return $("span#signin-reg-msg").getText();
    }

    @Step("EnterEmailOrUserNamePage: Set user mame or email")
    public EnterEmailOrUserNamePage setEmailOrUserName(String emailOrUserName) {
        SelenideElement emailOrUserNameInput = $(EMAIL_OR_USER_NAME_INPUT_CSS_SELECTOR);
        emailOrUserNameInput.click();
        emailOrUserNameInput.clear();
        emailOrUserNameInput.sendKeys(emailOrUserName);
        return this;
    }

    @Step("EnterEmailOrUserNamePage: Check if field for enter email and usr name is displayed")
    public boolean isInputEmailOrUserNameDisplayed() {
        return $(EMAIL_OR_USER_NAME_INPUT_CSS_SELECTOR).isDisplayed();
    }

    @Step("EnterEmailOrUserNamePage: Press 'Enter' in the field for enter email or user name ")
    public EnterPasswordPage pressEnterInEmailOrUserNameInput() {
        $(EMAIL_OR_USER_NAME_INPUT_CSS_SELECTOR).sendKeys(Keys.ENTER);
        return new EnterPasswordPage();
    }

    @Step("EnterEmailOrUserNamePage: Press the 'Continue' button")
    public EnterPasswordPage pressContinueButton() {
        $("button#signin-continue-btn").click();
        return new EnterPasswordPage();
    }
}
