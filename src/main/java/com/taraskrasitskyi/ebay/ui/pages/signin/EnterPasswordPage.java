package com.taraskrasitskyi.ebay.ui.pages.signin;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.taraskrasitskyi.ebay.ui.pages.BasePage;
import com.taraskrasitskyi.ebay.ui.pages.HomePage;
import com.taraskrasitskyi.ebay.ui.pages.ResetPasswordPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class EnterPasswordPage extends BasePage {

    private final String PASSWORD_INPUT_CSS_SELECTOR = "input#pass";
    private final String PASSWORD_ERR_MSG_HOLDER_CSS_SELECTOR = "p#errormsg";
    private final String SIGN_IN_BUTTON_CSS_SELECTOR = "button#sgnBt";
    private final String NEED_HELP_SIGNING_IN_LINK_CSS_SELECTOR = "a#need-help-signin-link";

    @Step("EnterPasswordPage: Set password")
    public EnterPasswordPage setPassword(String password) {
        SelenideElement passwordInput = $(PASSWORD_INPUT_CSS_SELECTOR);
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("EnterPasswordPage: Press 'Sign in' button")
    public <T extends BasePage> T pressSignInButton() {
        $(SIGN_IN_BUTTON_CSS_SELECTOR).click();
        return resultSubmitPassword();
    }

    @Step("Press 'Enter' in the field for enter password")
    public <T extends BasePage> T pressEnterInPasswordInput() {
        $(PASSWORD_INPUT_CSS_SELECTOR).pressEnter();
        return resultSubmitPassword();
    }

    @Step("EnterPasswordPage: Get password input type")
    public String getPasswordInputType() {
        return $(PASSWORD_INPUT_CSS_SELECTOR).getAttribute("type");
    }

    @Step("EnterPasswordPage: click on the 'Switch account' link")
    public EnterEmailOrUserNamePage clickOnSwitchAccountLink() {
        $("a#switch-account-anchor").click();
        return new EnterEmailOrUserNamePage();
    }

    @Step("EnterPasswordPage: Get the error message text")
    public String getErrorMessage() {
        return $(PASSWORD_ERR_MSG_HOLDER_CSS_SELECTOR).getText();
    }

    private <T extends BasePage> T resultSubmitPassword() {
        //error msg appears with small delay. That why I must use Selenide.sleep(1000L)
        //I can't use any should() or shouldBe() assertion, because I don't know exactly whether error msg should be present on the page
        Selenide.sleep(1000L);
        if ($(PASSWORD_ERR_MSG_HOLDER_CSS_SELECTOR).exists()) {
            return (T) this;
        } else {
            return (T) new HomePage();
        }
    }

    @Step("EnterPasswordPage: Check if the 'Sign in' button is enabled")
    public boolean isSignInButtonEnabled() {
        return $(SIGN_IN_BUTTON_CSS_SELECTOR).attr("disabled")!=null ? false : true;
    }

    @Step("EnterPasswordPage: Check if the 'Need help signing in help?' link is displayed")
    public boolean isNeedHelpSigningInLinkDisplayed(){
        return $(NEED_HELP_SIGNING_IN_LINK_CSS_SELECTOR).isDisplayed();
    }

    @Step("EnterPasswordPage: Open the 'Reset password' page")
    public ResetPasswordPage openResetPasswordPage(){
        $(NEED_HELP_SIGNING_IN_LINK_CSS_SELECTOR).click();
        return new ResetPasswordPage();
    }

}
