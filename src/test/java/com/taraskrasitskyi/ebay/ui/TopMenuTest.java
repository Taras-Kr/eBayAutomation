package com.taraskrasitskyi.ebay.ui;

import com.taraskrasitskyi.ebay.ui.elements.TopMenuElement;
import com.taraskrasitskyi.ebay.ui.pages.CategoryPage;
import com.taraskrasitskyi.ebay.ui.pages.HomePage;
import com.taraskrasitskyi.ebay.utils.ExpectedDataReader;
import com.taraskrasitskyi.ebay.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.List;

public class TopMenuTest extends TestRunner {

    @Test(description = "Verify that an unregistered user can open category 'Electronics' from the top menu")
    @Description("Verify that an unregistered user can open category 'Electronics' from the top menu")
    @TmsLink(value = "SEL-1")
    public void verifyThatCategoryOpens() {
        var category = TopMenuElement.ELECTRONICS;
        var categoryPage = new HomePage().open()
                .getTopMenu()
                .openCategoryFromTopMenu(category);
        var actualCaption = categoryPage.getTitleBannerCaption();
        var expectedCaption = "Electronics";
        var softAssert = new SoftAssertions();
        softAssert.assertThat(actualCaption)
                .as(String.format("The page page caption should be %s", expectedCaption))
                .isEqualTo(expectedCaption);
        var actualLinkCaption = categoryPage.getLastChainNavigateLinkText();
        var expectedLinkCaption = "Electronics";
        softAssert.assertThat(actualLinkCaption)
                .as(String.format("The page page caption should be %s", expectedLinkCaption))
                .isEqualTo(expectedLinkCaption);
        softAssert.assertAll();
    }

    @Test(description = "Verify that the user can open subcategories from the \"Most popular categories\" top menu item \"Electronics\"")
    @Description("Verify that the user can open subcategories from the \"Most popular categories\" top menu item \"Electronics\"")
    @TmsLink(value = "SEL-2")
    public void verifyThatMostPopularCategoriesOpens() {
        var topMenu = new HomePage()
                .open()
                .getTopMenu();
        var edReader = new ExpectedDataReader("src/test/resources/expecteddata/expected_Last_Chain_Navigate_LinkText_AND_Category_Page_Caption.csv");
        List<String> expectedLinkTexts = edReader.getColumn(1);
        List<String> expectedPageCaptions = edReader.getColumn(2);
        CategoryPage categoryPage;
        int mostPopularCategoriesCount = topMenu.getMostPopularCategoriesCount(TopMenuElement.ELECTRONICS);
        var softAssert = new SoftAssertions();
        for (int i = 1; i <= mostPopularCategoriesCount; i++) {
            categoryPage = topMenu.openMostPopularCategory(TopMenuElement.ELECTRONICS, i);
            softAssert.assertThat(categoryPage.getLastChainNavigateLinkText())
                    .as("Last chain navigation link AssertionError")
                    .isEqualTo(expectedLinkTexts.get(i - 1));
            softAssert.assertThat(categoryPage.getPageCaption())
                    .as("Page caption AssertionError")
                    .isEqualTo(expectedPageCaptions.get(i - 1));
            categoryPage.getHomePage();
        }
        softAssert.assertAll();
    }
}
