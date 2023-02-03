package com.taraskrasitskyi.ebay.ui.pages.signin;

import com.taraskrasitskyi.ebay.ui.pages.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class UserNameSignInPage extends BasePage {

    @Step("UserNameSignInPage: Get header text")
    public String getHeaderText(){
        return $x("//h1").getText();
    }

    @Step("UserNameSignInPage: Get sub header text")
    public String getSubHeaderText(){
        return $("span#signin-reg-msg").getText();
    }
}
