package com.taraskrasitskyi.ebay.ui.elements.menus.topmenu;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import com.taraskrasitskyi.ebay.ui.pages.BasePage;
import com.taraskrasitskyi.ebay.ui.pages.CategoryPage;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class TopMenu extends BasePage {

    private final String TOP_MENU_CONTAINER = "//li[@class='hl-cat-nav__js-tab']//a[text()='%s']";
    private final String MOST_POPULAR_CATEGORIES_CONTAINER = "//li[@class='hl-cat-nav__js-tab hl-cat-nav__js-show']/a[text()='%s']/..//nav[@aria-label='Most popular categories']//li/a";

    @Step("TopMenu : Open category from the top menu")
    public CategoryPage openCategoryFromTopMenu(TopMenuElement category) {
        $x(String.format(TOP_MENU_CONTAINER, category.getName())).click();
        return new CategoryPage();
    }

    @Step("TopMenu : Expand top menu element")
    public void expandTopMenuElement(TopMenuElement element) {
        actions().moveToElement($x(String.format(TOP_MENU_CONTAINER, element.getName()))).perform();
    }

    @Step("TopMenu : open most popular category {numberCategory}")
    public CategoryPage openMostPopularCategory(TopMenuElement topMenuElement, int numberCategory) {
        List<SelenideElement> mostPopularCategories = getTopMenuMostPopularCategories(topMenuElement);
        if (numberCategory < 1 || numberCategory > mostPopularCategories.size()) {
            throw new IllegalArgumentException("Number category: Illegal argument!");
        }
        try {
            mostPopularCategories.get(numberCategory - 1).click();
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }
        return new CategoryPage();
    }

    @Step("TopMenu : get Most popular subcategories from expanded category")
    public List<SelenideElement> getTopMenuMostPopularCategories(TopMenuElement category) {
        expandTopMenuElement(category);
        return $$x(String
                .format(MOST_POPULAR_CATEGORIES_CONTAINER, category.getName())).shouldHave(CollectionCondition.sizeGreaterThan(1));
    }

    @Step("TopMenu : get most popular categories count")
    public int getMostPopularCategoriesCount(TopMenuElement category) {
        int count = getTopMenuMostPopularCategories(category).size();
        closeTopMenu();
        return count;
    }

    @Step("TopMenu : close top menu")
    private void closeTopMenu() {
        actions().moveToElement($x("//a[@href='https://www.ebay.com/']")).perform();
    }
}
