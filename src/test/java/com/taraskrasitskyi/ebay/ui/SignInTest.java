package com.taraskrasitskyi.ebay.ui;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.taraskrasitskyi.ebay.ui.pages.HomePage;
import com.taraskrasitskyi.ebay.ui.pages.signin.EnterEmailOrUserNamePage;
import com.taraskrasitskyi.ebay.ui.pages.signin.EnterPasswordPage;
import com.taraskrasitskyi.ebay.utils.CredentialProperty;
import com.taraskrasitskyi.ebay.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.openqa.selenium.Cookie;
import org.testng.annotations.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class SignInTest extends TestRunner {

    private final CredentialProperty credentialProperty = new CredentialProperty();

    @Test(description = "Verify that the login link present on the home page")
    @Description(value = "Verify that the login link present on the home page")
    @TmsLink(value = "EBA-19")
    public void verifyThatSignInLinkIsAvailable() {
        var header = new HomePage().open().getHeader();
        assertThat(header.isEnterEmailOrUserNameLinkIsDisplayed())
                .as("The 'Sign in' link should be displayed")
                .isTrue();
    }

    @Test(description = "Verify that an unlogged user is able to open the 'Sign In UserName' page")
    @Description(value = "Verify that an unlogged user is able to open the 'Sign In UserName' page")
    @TmsLink(value = "EBA-20")
    public void verifyThatUserNameSignInPageIsOpened() {
        var signInUserNamePage = new HomePage()
                .open()
                .getHeader()
                .openEnterEmailOrUserNamePage();
        assertThat(signInUserNamePage.getHeaderText())
                .as("Page header should be 'Hello'")
                .isEqualTo("Hello");
        assertThat(signInUserNamePage.getSubHeaderText().contains("Sign in to eBay"))
                .as("Page sub header should contain 'Sign in to eBay'")
                .isTrue();
    }

    @Test(description = "Verify that an unlogged user is able to Sign In with the valid credentials" +
            " using the button 'Continue' on the 'Enter Email or username' page and the button 'Sign in' " +
            "on the 'Enter Password' page")
    @Description(value = "Verify that an unlogged user is able to Sign In with the valid credentials" +
            " using the button 'Continue' on the 'Enter Email or username' page and the button 'Sign in' " +
            "on the 'Enter Password' page")
    @TmsLink(value = "EBA-21")
    public void verifyThatUserCanSighInWithCredentialsUsingButtons() {
        var enterEmailOrUserNamePage = new HomePage()
                .open()
                .getHeader()
                .openEnterEmailOrUserNamePage();

        assertThat(enterEmailOrUserNamePage.isInputEmailOrUserNameDisplayed())
                .as("'Sign in' page is not opened. reCaptcha might be present")
                .isTrue();

        var enterPasswordPage = enterEmailOrUserNamePage
                .setEmailOrUserName(credentialProperty.getValidEmail())
                .pressContinueButton();

        assertThat(enterPasswordPage instanceof EnterPasswordPage)
                .as("'Enter password page should be opened")
                .isTrue();

        var header = ((EnterPasswordPage) enterPasswordPage).setPassword(credentialProperty.getValidPassword())
                .pressSignInButton()
                .getHeader();
        assertThat(header.isUserAccountButtonDisplayed())
                .as("The user account button should be displayed")
                .isTrue();
    }

    @Test(description = "Verify that an unlogged user is able to sign in with the valid credentials using" +
            " the 'Enter' key on the keyboard instead button 'Continue' on" +
            " the 'Enter Email or username' page and instead of using the button 'Sign in' on" +
            " the 'Enter Password' page")
    @Description(value = "Verify that an unlogged user is able to sign in with the valid credentials using" +
            " the 'Enter' key on the keyboard instead button 'Continue' on" +
            " the 'Enter Email or username' page and instead of using the button 'Sign in' on" +
            " the 'Enter Password' page")
    @TmsLink(value = "EBA-22")
    public void verifyThatUserCanSighInWithCredentialsUsingEnter() {
        var enterEmailOrUserNamePage = new HomePage()
                .open()
                .getHeader()
                .openEnterEmailOrUserNamePage();

        assertThat(enterEmailOrUserNamePage.isInputEmailOrUserNameDisplayed())
                .as("'Sign in' page is not opened. reCaptcha might be present")
                .isTrue();

        var enterPasswordPage = enterEmailOrUserNamePage
                .setEmailOrUserName(credentialProperty.getValidEmail())
                .pressEnterInEmailOrUserNameInput();

        assertThat(enterPasswordPage instanceof EnterPasswordPage)
                .as("'Password page' should be opened")
                .isTrue();

        var header = ((EnterPasswordPage) enterPasswordPage)
                .setPassword(credentialProperty.getValidPassword())
                .pressEnterInPasswordInput()
                .getHeader();

        assertThat(header.isUserAccountButtonDisplayed())
                .as("The user account button should be displayed")
                .isTrue();
    }

    @Test(description = "Verify that the password is masked")
    @Description(value = "Verify that the password is masked")
    @TmsLink(value = "EBA-23")
    public void verifyThatPasswordIsMasked() {
        var passwordPage = new HomePage()
                .open()
                .getHeader()
                .openEnterEmailOrUserNamePage()
                .setEmailOrUserName(credentialProperty.getValidEmail())
                .pressEnterInEmailOrUserNameInput();

        assertThat(passwordPage instanceof EnterPasswordPage)
                .as("'Password page should be opened'")
                .isTrue();
        ((EnterPasswordPage) passwordPage)
                .setPassword(credentialProperty.getValidPassword());

        assertThat(((EnterPasswordPage) passwordPage).getPasswordInputType())
                .as("Password input type should has type 'password'")
                .isEqualTo("password");
    }

    @Test(description = "Verify the option 'Switch account' on the 'Enter Password' page")
    @Description(value = "Verify the option 'Switch account' on the 'Enter Password' page")
    @TmsLink(value = "EBA-26")
    public void verifyThatUserCanSwitchAccount() {
        var enterPasswordPage = new HomePage()
                .open()
                .getHeader()
                .openEnterEmailOrUserNamePage()
                .setEmailOrUserName(credentialProperty.getValidEmail())
                .pressContinueButton();

        assertThat(enterPasswordPage instanceof EnterPasswordPage)
                .as("'Password page' should be opened")
                .isTrue();

        var enterEmailOrPasswordPage = ((EnterPasswordPage) enterPasswordPage).clickOnSwitchAccountLink();

        assertThat(enterEmailOrPasswordPage.getHeaderText())
                .as("'Enter email or password' page should have header 'Hello'")
                .isEqualTo("Hello");
        assertThat(enterEmailOrPasswordPage.getSubHeaderText().contains("Sign in to eBay"))
                .as("'Enter email or password' page should have sub header with 'Sign in to eBay'")
                .isTrue();
    }

    @Test(description = "Verify that a logged user is able to sign out")
    @Description(value = "Verify that a logged user is able to sign out")
    @TmsLink(value = "EBA-27")
    public void verifyThatUserCanSignOut() {
        var enterPasswordPage = new HomePage()
                .open()
                .getHeader()
                .openEnterEmailOrUserNamePage()
                .setEmailOrUserName(credentialProperty.getValidEmail())
                .pressContinueButton();

        assertThat(enterPasswordPage instanceof EnterPasswordPage)
                .as("'Password page' should be opened")
                .isTrue();

        var header = ((EnterPasswordPage) enterPasswordPage)
                .setPassword(credentialProperty.getValidPassword())
                .pressSignInButton()
                .getHeader();

        assertThat(header.isUserAccountButtonDisplayed())
                .as("The 'user account' button should be displayed")
                .isTrue();

        header = header.getUserAccountMenu().signOut();

        assertThat(header.isUserAccountButtonDisplayed())
                .as("The 'user account' button shouldn't be displayed")
                .isFalse();
    }

    @Test(description = "Verify the option 'Stay signed in' on the 'Enter Email or username' page when the option is unchecked")
    @Description(value = "Verify the option 'Stay signed in' on the 'Enter Email or username' page when the option is unchecked")
    @TmsLink(value = "EBA_25")
    public void verifyStaySignedInIsUnchecked() {
        var enterPasswordPage = new HomePage()
                .open()
                .getHeader()
                .openEnterEmailOrUserNamePage()
                .setEmailOrUserName(credentialProperty.getValidEmail())
                .unselectStaySignedInCheckBox()
                .pressContinueButton();

        assertThat(enterPasswordPage instanceof EnterPasswordPage)
                .as("'Password page' should be opened")
                .isTrue();

        var header = ((EnterPasswordPage) enterPasswordPage)
                .setPassword(credentialProperty.getValidPassword())
                .pressSignInButton()
                .getHeader();

        assertThat(header.isUserAccountButtonDisplayed())
                .as("The 'account button' should be displayed")
                .isTrue();

        //When cookies without expiration date are deleted it's simulate closing browser
        Set<Cookie> cookies = WebDriverRunner.getWebDriver().manage().getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getExpiry() == null) {
                WebDriverRunner.getWebDriver().manage().deleteCookie(cookie);
            }
        }

        Selenide.refresh();

        assertThat(header.isUserAccountButtonDisplayed())
                .as("The 'account button' shouldn't be displayed")
                .isFalse();
    }

    @Test(description = "Verify the option 'Stay signed in' on the 'Enter Email or username' page when the option is checked")
    @Description(value = "Verify the option 'Stay signed in' on the 'Enter Email or username' page when the option is checked")
    @TmsLink(value = "EBA-24")
    public void verifyThatUserStayLogged() {
        var enterPasswordPage = new HomePage()
                .open()
                .getHeader()
                .openEnterEmailOrUserNamePage()
                .setEmailOrUserName(credentialProperty.getValidEmail())
                .selectStaySignedInCheckBox()
                .pressContinueButton();

        assertThat(enterPasswordPage instanceof EnterPasswordPage)
                .as("'Password page' should be opened")
                .isTrue();

        var header = ((EnterPasswordPage) enterPasswordPage)
                .setPassword(credentialProperty.getValidPassword())
                .pressSignInButton()
                .getHeader();

        assertThat(header.isUserAccountButtonDisplayed())
                .as("The user account button should be displayed")
                .isTrue();
//When cookies without expiration date are deleted it's simulate closing browser
        Set<Cookie> cookieAll = WebDriverRunner.getWebDriver().manage().getCookies();
        for (Cookie cookie : cookieAll) {
            if (cookie.getExpiry() == null) {
                WebDriverRunner.getWebDriver().manage().deleteCookie(cookie);
            }
        }
        Selenide.refresh();

        assertThat(header.isUserAccountButtonDisplayed())
                .as("The user account button should be displayed")
                .isTrue();
    }

    @Test(description = "Verify that the error message appears when an unlogged user enters an invalid email in the 'Email or user name' field")
    @Description(value = "Verify that the error message appears when an unlogged user enters an invalid email in the 'Email or user name' field")
    @TmsLink(value = "EBA-28")
    public void verifyErrorMessageWhenInvalidEmail() {
        var enterEmailOrUserNamePage = new HomePage()
                .open()
                .getHeader()
                .openEnterEmailOrUserNamePage()
                .setEmailOrUserName(credentialProperty.getInvalidEmail())
                .pressContinueButton();

        assertThat(enterEmailOrUserNamePage instanceof EnterEmailOrUserNamePage)
                .as("'EnterEmailOrUserName' page should be active")
                .isTrue();

        assertThat(((EnterEmailOrUserNamePage) enterEmailOrUserNamePage).getSignInErrMsg().contains("Oops, that's not a match."))
                .as("Error message should contain 'Oops, that's not a match.'")
                .isTrue();
    }

    @Test(description = "Verify that the error message appears when an unlogged user leaves the field 'email or username' blank")
    @Description(value = "Verify that the error message appears when an unlogged user leaves the field 'email or username' blank")
    @TmsLink(value = "EBA-29")
    public void verifyErrorMessageWhenEmailOrUserNameBlank() {
        var enterEmailOrUserNamePage = new HomePage()
                .open()
                .getHeader()
                .openEnterEmailOrUserNamePage()
                .pressContinueButton();

        assertThat(enterEmailOrUserNamePage instanceof EnterEmailOrUserNamePage)
                .as("'Enter email or user name' should be opened")
                .isTrue();

        assertThat(((EnterEmailOrUserNamePage) enterEmailOrUserNamePage).getSignInErrMsg().contains("Oops, that's not a match."))
                .as("Error message should contain text 'Oops, that's not a match.'")
                .isTrue();
    }

    @Test(description = "Verify that the error message appears when an unlogged user enters an invalid password")
    @Description(value = "Verify that the error message appears when an unlogged user enters an invalid password")
    @TmsLink(value = "EBA-30")
    public void verifyErrorMessageWhenInvalidPassword() {
        var enterPasswordPage = new HomePage()
                .open()
                .getHeader()
                .openEnterEmailOrUserNamePage()
                .setEmailOrUserName(credentialProperty.getValidEmail())
                .pressContinueButton();

        assertThat(enterPasswordPage instanceof EnterPasswordPage)
                .as("'Enter password' page should be opened")
                .isTrue();

        ((EnterPasswordPage) enterPasswordPage)
                .setPassword(credentialProperty.getInvalidPassword())
                .pressSignInButton();

        assertThat(enterPasswordPage instanceof EnterPasswordPage)
                .as("'Enter password' page should be opened")
                .isTrue();

        //On the website the message contains mistake. It should be 'Oops, that's not a match.'
        assertThat(((EnterPasswordPage) enterPasswordPage).getErrorMessage().contains("Oops, that s not a match."))
                .as("Error message should contain text 'Oops, that s not a match.'")
                .isTrue();
    }

    @Test(description = "Verify that the button 'Sign in' is disabled  when the field  'password' is empty")
    @Description(value = "Verify that the button 'Sign in' is disabled  when the field  'password' is empty")
    @TmsLink(value = "EBA-31")
    public void verifyThatSignInButtonIsDisabledWhenPasswordIsEmpty() {
        var enterPasswordPage = new HomePage()
                .open()
                .getHeader()
                .openEnterEmailOrUserNamePage()
                .setEmailOrUserName(credentialProperty.getValidEmail())
                .pressContinueButton();

        assertThat(enterPasswordPage instanceof EnterPasswordPage)
                .as("'Enter password' page should be opened")
                .isTrue();

        assertThat(((EnterPasswordPage) enterPasswordPage).isSignInButtonEnabled())
                .as("The 'Sign in' button should be disabled")
                .isFalse();
    }

    @Test(description = "Verify that the 'Get help signing in' page opens when an unlogged user clicks on" +
            " the 'Need help signing in?' link on the 'Enter password' page")
    @Description(value = "Verify that the 'Get help signing in' page opens when an unlogged user clicks on" +
            " the 'Need help signing in?' link on the 'Enter password' page")
    @TmsLink(value = "EBA-32")
    public void verifyThatUserCanOpenResetPasswordPage() {
        var enterPasswordPage = new HomePage()
                .open()
                .getHeader()
                .openEnterEmailOrUserNamePage()
                .setEmailOrUserName(credentialProperty.getValidEmail())
                .pressContinueButton();

        assertThat(enterPasswordPage instanceof EnterPasswordPage)
                .as("'Enter password' page should be opened")
                .isTrue();

        assertThat(((EnterPasswordPage) enterPasswordPage).isNeedHelpSigningInLinkDisplayed())
                .as("The 'Need help signing in?' link should be displayed")
                .isTrue();

        var resetPasswordPage = ((EnterPasswordPage) enterPasswordPage).openResetPasswordPage();

        assertThat(resetPasswordPage.getPageCaption())
                .as("")
                .isEqualTo("Get help signing in");
        new HomePage();

        assertThat(resetPasswordPage.isReceiveEmailButtonDisplayed())
                .as("'Receive email' button should be displayed")
                .isTrue();

        assertThat(resetPasswordPage.isReceiveTextButtonDisplayed())
                .as("'Receive text' button should be displayed")
                .isTrue();
    }
}

