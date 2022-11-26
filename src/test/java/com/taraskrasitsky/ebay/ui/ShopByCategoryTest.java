package com.taraskrasitsky.ebay.ui;

import com.taraskrasitsky.ebay.utils.ExpectedDataReader;
import com.taraskrasitsky.ebay.utils.TestRunner;
import com.taraskrasitskyi.ebay.ui.elements.ShopByCategoryMenu;
import com.taraskrasitskyi.ebay.ui.pages.CategoryPage;
import com.taraskrasitskyi.ebay.ui.pages.HomePage;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class ShopByCategoryTest extends TestRunner {

    @Test(description = "Verify that the user can open each main category from the ShopByCategory menu")
    @Description("Verify that the user can open each main category from the ShopByCategory menu")
    @TmsLink(value = "SEL-3")
    public void verifyThatCategoriesOpen() {
        HomePage homePage = new HomePage(driver);
        int categoriesCount = homePage
                .getHeader()
                .openShopByCategoryMenu()
                .categoriesCount();
        CategoryPage categoryPage;
        ShopByCategoryMenu shopByCategoryMenu;
        ExpectedDataReader edReader = new ExpectedDataReader("src/test/resources/expecteddata/verifyThatCategoriesOpen_expected.csv");
        List<String> expectedLinkTexts = edReader.getColumn(1);
        List<String> expectedPageHeaders = edReader.getColumn(2);
        for (int i = 0; i < categoriesCount; i++) {
            shopByCategoryMenu = homePage
                    .getHeader()
                    .openShopByCategoryMenu();
            List<WebElement> categoriesList = shopByCategoryMenu.getMenuMainItemsList();
            categoryPage = shopByCategoryMenu.openCategory(categoriesList.get(i));
            softAssert.assertEquals(categoryPage.getLastChainNavigateLinkText(), expectedLinkTexts.get(i),
                    "Assert last chain links text is failed");
            softAssert.assertEquals(categoryPage.getPageCaption(), expectedPageHeaders.get(i),
                    "Assert page caption is failed");
            homePage = categoryPage.getHomePage();
        }
        softAssert.assertAll();
    }

    @Test(description = "Verify that the user can open the \"See All Categories\" link from the ShopByCategory menu")
    @Description("Verify that the user can open the \"See All Categories\" link from the ShopByCategory menu")
    @TmsLink(value = "SEL-4")
    public void verifyThatSeeAllCategoriesOpens() {
        CategoryPage seeAllCategories = new HomePage(driver)
                .getHeader()
                .openShopByCategoryMenu()
                .openSeeAllCategories();
        softAssert.assertEquals(seeAllCategories.getPageCaption(), "All Categories",
                "Assert page caption is failed");
        seeAllCategories.getHomePage();
        softAssert.assertAll();
    }


    @Test(description = "Verify that each category in the ShopByCategory menu has four subcategories")
    @Description("Verify that each category in the ShopByCategory menu has four subcategories")
    @TmsLink(value = "SEL-5")
    public void verifySubCategoriesCount() {
        ShopByCategoryMenu shopByCategoryMenu = new HomePage(driver)
                .getHeader()
                .openShopByCategoryMenu();
        List<WebElement> subCategoriesLists = shopByCategoryMenu.getSubCategoriesLists();
        List<WebElement> subCategoryItemsList;
        for (int i = 0; i < subCategoriesLists.size(); i++) {
            subCategoryItemsList = shopByCategoryMenu.getSubCategoryItemsList(subCategoriesLists.get(i));
            softAssert.assertEquals(subCategoryItemsList.size(), 4,
                    "Assert subcategories count failed");
        }
        shopByCategoryMenu.getHomePage();
        softAssert.assertAll();
    }
}
