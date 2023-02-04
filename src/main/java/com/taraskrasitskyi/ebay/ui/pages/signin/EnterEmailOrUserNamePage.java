package com.taraskrasitskyi.ebay.ui.pages.signin;

import com.codeborne.selenide.SelenideElement;
import com.taraskrasitskyi.ebay.ui.pages.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class EnterEmailOrUserNamePage extends BasePage {

    @Step("UserNameSignInPage: Get header text")
    public String getHeaderText(){
        return $x("//h1").getText();
    }

    @Step("EnterEmailOrUserNamePage: Get sub header text")
    public String getSubHeaderText(){
        return $("span#signin-reg-msg").getText();
    }

    @Step("EnterEmailOrUserNamePage: Set user mame or email")
    public EnterEmailOrUserNamePage setEmailOrUserName(String emailOrUserName){
        SelenideElement inputEmailOrUserName = $("input#userid");
        inputEmailOrUserName.click();
        inputEmailOrUserName.clear();
        inputEmailOrUserName.sendKeys(emailOrUserName);
        return this;
    }

    @Step("EnterEmailOrUserNamePage: Press the 'Continue' button")
    public EnterPasswordPage pressContinueButton(){
        $("button#signin-continue-btn").click();
        return  new EnterPasswordPage();
    }
}
