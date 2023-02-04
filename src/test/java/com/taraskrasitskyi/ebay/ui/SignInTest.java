package com.taraskrasitskyi.ebay.ui;

import com.taraskrasitskyi.ebay.ui.pages.HomePage;
import com.taraskrasitskyi.ebay.utils.CredentialProperty;
import com.taraskrasitskyi.ebay.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SignInTest extends TestRunner {

    private final CredentialProperty credentialProperty = new CredentialProperty();

    @Test(description = "Verify that the login link present on the home page")
    @Description(value = "Verify that the login link present on the home page")
    @TmsLink(value = "EBA-19")
    public void verifyThatSignInLinkIsAvailable() {
        var header = new HomePage().open().getHeader();
        assertThat(header.isEnterEmailOrUserNameIsDisplayed())
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

        var header = enterEmailOrUserNamePage.setEmailOrUserName(credentialProperty.getValidEmailOrUserName())
                .pressContinueButton()
                .setPassword(credentialProperty.getValidPassword())
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

        var header = enterEmailOrUserNamePage
                .setEmailOrUserName(credentialProperty.getValidEmailOrUserName())
                .pressEnterInEmailOrUserNameInput()
                .setPassword(credentialProperty.getValidPassword())
                .pressEnterInPasswordInput()
                .getHeader();
        assertThat(header.isUserAccountButtonDisplayed())
                .as("The user account button should be displayed")
                .isTrue();
    }

    @Test(description = "Verify that the password is masked")
    @Description(value = "Verify that the password is masked")
    @TmsLink(value ="EBA-23")
    public void verifyThatPasswordIsMasked(){
       var passwordPage = new HomePage()
                .open()
                .getHeader()
                .openEnterEmailOrUserNamePage()
                .setEmailOrUserName(credentialProperty.getValidEmailOrUserName())
                .pressEnterInEmailOrUserNameInput()
                .setPassword(credentialProperty.getValidPassword());
       assertThat(passwordPage.getPasswordInputType())
               .as("Password input type should has type 'password'")
               .isEqualTo("password");
    }
}