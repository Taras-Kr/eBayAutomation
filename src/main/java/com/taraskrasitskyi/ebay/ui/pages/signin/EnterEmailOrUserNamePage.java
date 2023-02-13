package com.taraskrasitskyi.ebay.ui.pages.signin;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.taraskrasitskyi.ebay.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class EnterEmailOrUserNamePage extends BasePage {

    private final String EMAIL_OR_USER_NAME_INPUT_CSS_SELECTOR = "input#userid";
    private final String STAY_SIGN_IN_CHECK_BOX_CSS_SELECTOR = "input#kmsi-checkbox";
    private final String SIGN_IN_ERR_MSG_HOLDER_CSS_SELECTOR = "p#signin-error-msg";

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
    public <T extends BasePage> T pressEnterInEmailOrUserNameInput() {
        $(EMAIL_OR_USER_NAME_INPUT_CSS_SELECTOR).sendKeys(Keys.ENTER);
        return resultSubmitEmailOrUserName();
    }

    @Step("EnterEmailOrUserNamePage: Press the 'Continue' button")
    public <T extends BasePage> T pressContinueButton() {
        $("button#signin-continue-btn").click();
        return resultSubmitEmailOrUserName();
    }

    private <T extends BasePage> T resultSubmitEmailOrUserName() {
        //error msg appears with small delay. That why I must use Selenide.sleep(1000L)
        //I can't use any should() or shouldBe() assertion, because I don't know exactly whether error msg should be present on the page
        Selenide.sleep(1000L);
        if ($(SIGN_IN_ERR_MSG_HOLDER_CSS_SELECTOR).exists()) {
            return (T) this;
        } else {
            return (T) new EnterPasswordPage();
        }
    }

    public String getSignInErrMsg() {
        if ($(SIGN_IN_ERR_MSG_HOLDER_CSS_SELECTOR).isDisplayed()) {
            return $(SIGN_IN_ERR_MSG_HOLDER_CSS_SELECTOR).getText();
        }
        return null;
    }

    @Step("EnterEmailOrUserNamePage: Check if 'Stay Sign in' check box is checked")
    private boolean isStaySignInCheckBoxChecked() {
        String checkBoxValue = $(STAY_SIGN_IN_CHECK_BOX_CSS_SELECTOR).getAttribute("value");
        return checkBoxValue.equals("1");
    }

    @Step("EnterEmailOrUserNamePage: Select 'Stay Sign in' check box")
    public EnterEmailOrUserNamePage selectStaySignedInCheckBox() {
        if (!isStaySignInCheckBoxChecked()) {
            $(STAY_SIGN_IN_CHECK_BOX_CSS_SELECTOR).click();
        }
        return this;
    }

    @Step("EnterEmailOrUserNamePage: Unselect 'Stay Sign in' check box")
    public EnterEmailOrUserNamePage unselectStaySignedInCheckBox() {
        if (isStaySignInCheckBoxChecked()) {
            $(STAY_SIGN_IN_CHECK_BOX_CSS_SELECTOR).click();
        }
        return this;
    }
}
