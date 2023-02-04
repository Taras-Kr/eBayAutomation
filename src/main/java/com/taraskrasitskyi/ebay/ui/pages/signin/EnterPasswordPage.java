package com.taraskrasitskyi.ebay.ui.pages.signin;

import com.codeborne.selenide.SelenideElement;
import com.taraskrasitskyi.ebay.ui.pages.BasePage;
import com.taraskrasitskyi.ebay.ui.pages.HomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class EnterPasswordPage extends BasePage {

    private final String PASSWORD_INPUT_CSS_SELECTOR = "input#pass";

    @Step("EnterPasswordPage: Set password")
    public EnterPasswordPage setPassword(String password) {
        SelenideElement passwordInput = $(PASSWORD_INPUT_CSS_SELECTOR);
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("EnterPasswordPage: Press 'Sign in' button")
    public HomePage pressSignInButton() {
        $("button#sgnBt").click();
        return new HomePage();
    }

    @Step("Press 'Enter' in the field for enter password")
    public HomePage pressEnterInPasswordInput(){
        $(PASSWORD_INPUT_CSS_SELECTOR).sendKeys(Keys.ENTER);
        return new HomePage();
    }

    @Step("EnterPasswordPage: Get password input type")
    public String getPasswordInputType(){
        return $(PASSWORD_INPUT_CSS_SELECTOR).getAttribute("type");
    }

}
