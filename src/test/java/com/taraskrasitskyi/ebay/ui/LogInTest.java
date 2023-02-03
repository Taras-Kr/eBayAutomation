package com.taraskrasitskyi.ebay.ui;

import com.taraskrasitskyi.ebay.ui.pages.HomePage;
import com.taraskrasitskyi.ebay.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
public class LogInTest extends TestRunner {

    @Test(description = "Verify that the login link present on the home page")
    @Description(value = "Verify that the login link present on the home page")
    @TmsLink(value = "EBA-19")
    public void verifyThatSignInLinkIsAvailable(){
        var header = new HomePage().open().getHeader();
        assertThat(header.isElementDisplayed(header.getSignInLink()))
                .as("The 'Sign in' link should be displayed")
                .isTrue();
    }
}
