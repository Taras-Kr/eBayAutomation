package com.taraskrasitskyi.ebay.ui;

import com.taraskrasitskyi.ebay.ui.pages.HomePage;
import com.taraskrasitskyi.ebay.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SignInTest extends TestRunner {

    @Test(description = "Verify that the login link present on the home page")
    @Description(value = "Verify that the login link present on the home page")
    @TmsLink(value = "EBA-19")
    public void verifyThatSignInLinkIsAvailable() {
        var header = new HomePage().open().getHeader();
        assertThat(header.isElementDisplayed(header.getSignInLink()))
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
                .openUserNameSignInPage();
        assertThat(signInUserNamePage.getHeaderText())
                .as("Page header should be Hello")
                .isEqualTo("Hello");
        assertThat(signInUserNamePage.getSubHeaderText().contains("Sign in to eBay"))
                .as("Page sub header should contain 'Sign in to eBay'")
                .isTrue();
    }
}