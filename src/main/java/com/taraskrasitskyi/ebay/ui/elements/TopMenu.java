package com.taraskrasitskyi.ebay.ui.elements;

import com.taraskrasitskyi.ebay.ui.pages.BasePage;
import com.taraskrasitskyi.ebay.ui.pages.CategoryPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.taraskrasitskyi.ebay.ui.locators.BasePageLocators.HOME_PAGE_LINK;
import static com.taraskrasitskyi.ebay.ui.locators.TopMenuLocators.TOP_MENU_MOST_POPULAR_CATEGORIES;

public class TopMenu extends BasePage {
    public TopMenu(WebDriver driver) {
        super(driver);
    }

    @Step("Open category from the top menu")
    public CategoryPage openCategoryFromTopMenu(By category) {
        driver.findElement(category).click();
        return new CategoryPage(driver);
    }

    @Step("Expand top menu element")
    public void expandTopMenuElement(By topMenuElement) {
        Actions moveCursorToElement = new Actions(driver);
        moveCursorToElement.moveToElement(driver.findElement(topMenuElement)).perform();
    }

    @Step("Open most popular category {numberCategory}")
    public CategoryPage openMostPopularCategory(By topMenuElement, int numberCategory) {
        List<WebElement> mostPopularCategories = getTopMenuMostPopularCategories(topMenuElement);
        if (numberCategory < 1 || numberCategory > mostPopularCategories.size()) {
            throw new IllegalArgumentException("Number category: Illegal argument!");
        }
        try {
            mostPopularCategories.get(numberCategory - 1).click();
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }
        return new CategoryPage(driver);
    }

    @Step("Get Most popular subcategories from opened category")
    public List<WebElement> getTopMenuMostPopularCategories(By topMenuElement) {
        expandTopMenuElement(topMenuElement);
        String newXPath = topMenuElement.toString()
                .substring(10)
                .replace("hl-cat-nav__js-tab", "hl-cat-nav__js-tab hl-cat-nav__js-show");
        topMenuElement = By.xpath(newXPath);
        String xpath = topMenuElement.toString().substring(10) + TOP_MENU_MOST_POPULAR_CATEGORIES;
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        return driver.findElements(By.xpath(xpath));
    }

    @Step("Get most popular categories count")
    public int getMostPopularCategoriesCount(By topMenuElement){
        int count = getTopMenuMostPopularCategories(topMenuElement).size();
        closeTopMenu();
        return count;
    }

    @Step("Close top menu")
    private void closeTopMenu() {
        Actions moveCursor = new Actions(driver);
        moveCursor.moveToElement(driver.findElement(HOME_PAGE_LINK.getPath())).perform();
    }
}
