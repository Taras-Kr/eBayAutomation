package com.taraskrasitskyi.ebay.ui.elements.menus.leftsidecategoriesmenu;

import com.taraskrasitskyi.ebay.ui.pages.ProductsPage;
import com.taraskrasitskyi.ebay.ui.pages.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class LeftSideCategoriesMenu extends BasePage {

    private final String expandingMenuItem = "//li//span[text()=%s]";

    private boolean isExpanding(LeftSideCategoriesMenuItem item) {
        return $x(String.format(expandingMenuItem, item.getName())).exists();
    }

    @Step("LeftSideCategoriesMenu: expand menu item")
    public LeftSideCategoriesMenu expandMenuItem(LeftSideCategoriesMenuItem item) {
        if (isExpanding(item)) {
            $x(String.format(expandingMenuItem, item.getName())).click();
        }
        return this;
    }

    @Step("LeftSideCategoriesMenu: open menu item")
    public ProductsPage openMenuItem(LeftSideCategoriesMenuItemLink item) {
        $x(String.format("//li//a[text()=%s]", item.getName())).click();
        return new ProductsPage();
    }
}
