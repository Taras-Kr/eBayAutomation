package com.taraskrasitskyi.ebay.ui.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.taraskrasitskyi.ebay.ui.elements.FilterModal;
import com.taraskrasitskyi.ebay.ui.elements.Product;
import io.qameta.allure.Step;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.codeborne.selenide.Selenide.*;

public class ProductsPage extends BasePage {

    @Step("Products Page: open all filters")
    public FilterModal openAllFilters() {
        $x("//button[text()='All Filters']").click();
        return new FilterModal();
    }

    @Step("ProductsPage: Get product")
    public Product getProduct(int productNumber) {
        return new Product(String.format("(//div[@class='s-item__wrapper clearfix'])[%s]", productNumber));
    }

    @Step("Products Page: get products list")
    public List<Product> getProductsList(){
        int productsCount = $$x("//div[@class='s-item__wrapper clearfix']")
                .shouldHave(CollectionCondition.sizeGreaterThan(1))
                .size();
        return IntStream.rangeClosed(1,productsCount)
                .mapToObj(i-> getProduct(i))
                .collect(Collectors.toList());
    }

    @Step("Products Page: Get products price list")
    public List<BigDecimal> getPricesList(List<Product> productsList){
        return productsList
                .stream()
                .map(i->i.getPrice())
                .collect(Collectors.toList());
    }

}
