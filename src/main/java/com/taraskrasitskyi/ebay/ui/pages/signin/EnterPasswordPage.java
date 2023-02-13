package com.taraskrasitskyi.ebay.ui.pages.signin;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.taraskrasitskyi.ebay.ui.pages.BasePage;
import com.taraskrasitskyi.ebay.ui.pages.HomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class EnterPasswordPage extends BasePage {

    private final String PASSWORD_INPUT_CSS_SELECTOR = "input#pass";
    private final String PASSWORD_ERR_MSG_HOLDER_CSS_SELECTOR = "p#errormsg";

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
        $("button#sgnBt").click();
        return resultSubmitPassword();
    }

    @Step("Press 'Enter' in the field for enter password")
    public <T extends BasePage> T pressEnterInPasswordInput() {
        $(PASSWORD_INPUT_CSS_SELECTOR).sendKeys(Keys.ENTER);
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

    private <T extends BasePage> T resultSubmitPassword(){
        //error msg appears with small delay. That why I must use Selenide.sleep(1000L)
        //I can't use any should() or shouldBe() assertion, because I don't know exactly whether error msg should be present on the page
        Selenide.sleep(1000L);
        if ($(PASSWORD_ERR_MSG_HOLDER_CSS_SELECTOR).exists()) {
            return (T) this;
        } else {
            return (T) new HomePage();
        }
    }
}
