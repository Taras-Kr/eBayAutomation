package com.taraskrasitskyi.ebay.ui.pages;

import com.taraskrasitskyi.ebay.ui.elements.FilterModal;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class ProductsPage extends BasePage {

    @Step("Products Page: open all filters")
    public FilterModal openAllFilters(){
        $x("//button[text()='All Filters']").click();
        return new FilterModal();
    }
}
