package com.taraskrasitskyi.ebay.ui;

import com.codeborne.selenide.SelenideElement;
import com.taraskrasitskyi.ebay.ui.elements.menus.shopbycategorymenu.ShopByCategoryMenu;
import com.taraskrasitskyi.ebay.ui.pages.CategoryPage;
import com.taraskrasitskyi.ebay.ui.pages.HomePage;
import com.taraskrasitskyi.ebay.utils.ExpectedDataReader;
import com.taraskrasitskyi.ebay.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ShopByCategoryTest extends TestRunner {

    @Test(description = "Verify that the user can open each main category from the ShopByCategory menu")
    @Description("Verify that the user can open each main category from the ShopByCategory menu")
    @TmsLink(value = "SEL-3")
    public void verifyThatCategoriesOpen() {
        var homePage = new HomePage().open();
        int categoriesCount = homePage
                .getHeader()
                .openShopByCategoryMenu()
                .categoriesCount();
        CategoryPage categoryPage;
        ShopByCategoryMenu shopByCategoryMenu;
        ExpectedDataReader edReader = new ExpectedDataReader("src/test/resources/expecteddata/verifyThatCategoriesOpen_expected.csv");
        List<String> expectedLinkTexts = edReader.getColumn(1);
        List<String> expectedPageHeaders = edReader.getColumn(2);
        var softAssert = new SoftAssertions();
        for (int i = 0; i < categoriesCount; i++) {
            shopByCategoryMenu = homePage
                    .getHeader()
                    .openShopByCategoryMenu();
            List<SelenideElement> categoriesList = shopByCategoryMenu.getMenuMainItemsList();
            categoryPage = shopByCategoryMenu.openCategory(categoriesList.get(i));
            softAssert.assertThat(categoryPage.getLastChainNavigateLinkText())
                    .as("Assert last chain links text is failed")
                    .isEqualTo(expectedLinkTexts.get(i));
            softAssert.assertThat(categoryPage.getPageCaption())
                    .as("Assert page caption is failed")
                    .isEqualTo(expectedPageHeaders.get(i));
        }
        softAssert.assertAll();
    }

    @Test(description = "Verify that the user can open the \"See All Categories\" link from the ShopByCategory menu")
    @Description("Verify that the user can open the \"See All Categories\" link from the ShopByCategory menu")
    @TmsLink(value = "SEL-4")
    public void verifyThatSeeAllCategoriesOpens() {
        var seeAllCategories = new HomePage()
                .open()
                .getHeader()
                .openShopByCategoryMenu()
                .openSeeAllCategories();
        assertThat(seeAllCategories.getPageCaption())
                .as("Assert page caption is failed")
                .isEqualTo("All Categories");
    }

    @Test(description = "Verify that each category in the ShopByCategory menu has four subcategories")
    @Description("Verify that each category in the ShopByCategory menu has four subcategories")
    @TmsLink(value = "SEL-5")
    public void verifySubCategoriesCount() {
        var softAssert = new SoftAssertions();
        var shopByCategoryMenu = new HomePage()
                .open()
                .getHeader()
                .openShopByCategoryMenu();
        List<SelenideElement> subCategoriesLists = shopByCategoryMenu.getSubCategoriesLists();
        List<SelenideElement> subCategoryItemsList;
        for (int i = 0; i < subCategoriesLists.size(); i++) {
            subCategoryItemsList = shopByCategoryMenu.getSubCategoryItemsList(subCategoriesLists.get(i));
            softAssert.assertThat(subCategoryItemsList.size())
                    .as("Count subcategories should be equal 4")
                    .isEqualTo(4);
        }
        shopByCategoryMenu.getHomePage();
        softAssert.assertAll();
    }
}
