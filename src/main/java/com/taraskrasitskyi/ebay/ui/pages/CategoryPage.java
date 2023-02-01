package com.taraskrasitskyi.ebay.ui.pages;

import com.codeborne.selenide.SelenideElement;
import com.taraskrasitskyi.ebay.ui.elements.CategoryCarouselElement;
import com.taraskrasitskyi.ebay.ui.elements.menus.leftsidecategoriesmenu.LeftSideCategoriesMenu;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CategoryPage extends BasePage {

    @Step("CategoryPage: Get text from last chain navigate link")
    public String getLastChainNavigateLinkText() {
        List<SelenideElement> navLinkElements = $$x("//a[@class='seo-breadcrumb-text']/span");
        if (navLinkElements.size() > 0) {
            return navLinkElements.get(navLinkElements.size() - 1).getText();
        } else {
            return "";
        }
    }

    @Step("CategoryPage: Get title banner caption")
    public String getTitleBannerCaption() {
        return $x("//h1").getText();
    }

    @Step("CategoryPage: Get left-side categories menu")
    public LeftSideCategoriesMenu getLeftSideCategoriesMenu(){
        return new LeftSideCategoriesMenu();
    }

    @Step("CategoryPage: Open category {category} from category carousel")
    public ProductsPage openCategoryFromCarousel(CategoryCarouselElement category){
        $x(String.format("//a//p[text()='%s']",category.getCategoryName())).click();
        return new ProductsPage();
    }
}
