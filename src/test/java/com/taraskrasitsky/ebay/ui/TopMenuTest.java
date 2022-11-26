package com.taraskrasitsky.ebay.ui;

import com.taraskrasitsky.ebay.utils.ExpectedDataReader;
import com.taraskrasitsky.ebay.utils.TestRunner;
import com.taraskrasitskyi.ebay.ui.elements.TopMenu;
import com.taraskrasitskyi.ebay.ui.pages.CategoryPage;
import com.taraskrasitskyi.ebay.ui.pages.HomePage;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;
import java.util.List;

import static com.taraskrasitskyi.ebay.ui.locators.TopMenuLocators.*;

public class TopMenuTest extends TestRunner {

    @Test(description = "Verify that an unregistered user can open category 'Electronics' from the top menu")
    @Description("Verify that an unregistered user can open category 'Electronics' from the top menu")
    @TmsLink(value = "SEL-1")
    public void verifyThatCategoryOpens() {
        CategoryPage categoryPage = new HomePage(driver)
                .getTopMenu()
                .openCategoryFromTopMenu(TOP_MENU_ELECTRONICS.getPath());
        String actualCaption = categoryPage.getTitleBannerCaption();
        softAssert.assertEquals(actualCaption, "Electronics");
        String actualLinkCaption = categoryPage.getLastChainNavigateLinkText();
        softAssert.assertEquals(actualLinkCaption, "Electronics");
        categoryPage.getHomePage();
        softAssert.assertAll();
    }

    @Test(description = "Verify that the user can open subcategories from the \"Most popular categories\" top menu item \"Electronics\"")
    @Description("Verify that the user can open subcategories from the \"Most popular categories\" top menu item \"Electronics\"")
    @TmsLink(value = "SEL-2")
    public void verifyThatMostPopularCategoriesOpens() {
        TopMenu topMenu = new HomePage(driver).getTopMenu();
        ExpectedDataReader edReader = new ExpectedDataReader("src/test/resources/expecteddata/expected_Last_Chain_Navigate_LinkText_AND_Category_Page_Caption.csv");
        List<String> expectedLinkTexts = edReader.getColumn(1);
        List<String> expectedPageCaptions = edReader.getColumn(2);
        CategoryPage categoryPage;
        int mostPopularCategoriesCount = topMenu.getMostPopularCategoriesCount(TOP_MENU_ELECTRONICS.getPath());
        for (int i = 1; i <= mostPopularCategoriesCount; i++) {
            categoryPage = topMenu.openMostPopularCategory(TOP_MENU_ELECTRONICS.getPath(), i);
            softAssert.assertEquals(categoryPage.getLastChainNavigateLinkText(), expectedLinkTexts.get(i - 1));
            softAssert.assertEquals(categoryPage.getPageCaption(), expectedPageCaptions.get(i - 1));
            categoryPage.getHomePage();
        }
        softAssert.assertAll();
    }
}
