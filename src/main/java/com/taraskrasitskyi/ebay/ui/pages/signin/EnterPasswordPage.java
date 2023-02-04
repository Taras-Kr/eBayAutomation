package com.taraskrasitskyi.ebay.ui.pages.signin;

import com.codeborne.selenide.SelenideElement;
import com.taraskrasitskyi.ebay.ui.pages.BasePage;
import com.taraskrasitskyi.ebay.ui.pages.HomePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class EnterPasswordPage extends BasePage {

    @Step("EnterPasswordPage: Set password")
    public EnterPasswordPage setPassword(String password) {
        SelenideElement inputPassword = $("input#pass");
        inputPassword.click();
        inputPassword.clear();
        inputPassword.sendKeys(password);
        return this;
    }

    public HomePage pressSignInButton() {
        $("button#sgnBt").click();
        return new HomePage();
    }
}
