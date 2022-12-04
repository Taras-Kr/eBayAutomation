package com.taraskrasitskyi.ebay.ui.elements.menus.shopbycategorymenu;

import com.codeborne.selenide.SelenideElement;
import com.taraskrasitskyi.ebay.ui.pages.BasePage;
import com.taraskrasitskyi.ebay.ui.pages.CategoryPage;
import io.qameta.allure.Step;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class ShopByCategoryMenu extends BasePage {

    @Step("ShopByCategoryMenu : get categories list")
    public List<SelenideElement> getMenuMainItemsList() {
        return $$x("//h3[@class='gh-sbc-parent']");
    }

    @Step("ShopByCategoryMenu : get categories count in the menu")
    public int categoriesCount() {
        List<SelenideElement> categories = getMenuMainItemsList();
        getHeader().openShopByCategoryMenu();
        return categories.size();
    }

    @Step("ShopByCategories : open category")
    public CategoryPage openCategory(SelenideElement category) {
        category.$("a").click();
        return new CategoryPage();
    }

    @Step("ShopByCategories : open category")
    public CategoryPage openCategory(ShopByCategoryMenuItem category){
        $x(String.format("//a[text()='%s']", category.getName())).click();
        return new CategoryPage();
    }

    @Step("ShopByCategory : open 'See All Categories'")
    public CategoryPage openSeeAllCategories() {
        $x("//a[@id='gh-shop-see-all']").click();
        return new CategoryPage();
    }

    @Step("ShopByCategoryMenu : get subcategories lists")
    public List<SelenideElement> getSubCategoriesLists() {
        return $$("h3.gh-sbc-parent + ul");
    }

    @Step("ShopByCategoryMenu : get subcategory items list")
    public List<SelenideElement> getSubCategoryItemsList(SelenideElement categoryList) {
        return categoryList.$$("li");
    }
}
