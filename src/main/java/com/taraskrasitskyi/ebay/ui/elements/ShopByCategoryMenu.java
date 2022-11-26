package com.taraskrasitskyi.ebay.ui.elements;

import com.taraskrasitskyi.ebay.ui.pages.BasePage;
import com.taraskrasitskyi.ebay.ui.pages.CategoryPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.taraskrasitskyi.ebay.ui.locators.ShopByCategoriesLocators.*;

public class ShopByCategoryMenu extends BasePage {

    public ShopByCategoryMenu(WebDriver driver) {
        super(driver);
    }

    @Step("Get categories list")
    public List<WebElement> getMenuMainItemsList() {
        return driver.findElements(CATEGORIES_LIST.getPath());
    }

    public int categoriesCount() {
        List<WebElement> categories = getMenuMainItemsList();
        getHeader().openShopByCategoryMenu();
        return categories.size();
    }

    @Step("Open category")
    public CategoryPage openCategory(WebElement category) {
        category
                .findElement(CATEGORIES_LINK.getPath())
                .click();
        return new CategoryPage(driver);
    }

    @Step("Open See All Categories")
    public CategoryPage openSeeAllCategories() {
        driver
                .findElement(SEE_ALL_CATEGORIES.getPath())
                .click();
        return new CategoryPage(driver);
    }

    @Step("Get subcategories lists")
    public List<WebElement> getSubCategoriesLists() {
        return driver.findElements(SUB_CATEGORIES_LIST.getPath());
    }

    @Step("Get subcategory items list")
    public List<WebElement> getSubCategoryItemsList(WebElement categoryList) {
        return categoryList
                .findElements(SUB_CATEGORY_ITEMS_LIST.getPath());
    }
}
