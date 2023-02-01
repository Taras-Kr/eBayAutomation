package com.taraskrasitskyi.ebay.ui.pages;

import com.codeborne.selenide.CollectionCondition;
import com.taraskrasitskyi.ebay.ui.elements.CategoryCarouselElement;
import com.taraskrasitskyi.ebay.ui.elements.filter.FilterModal;
import com.taraskrasitskyi.ebay.ui.elements.Product;
import com.taraskrasitskyi.ebay.ui.elements.menus.leftsidecategoriesmenu.LeftSideCategoriesMenu;
import io.qameta.allure.Step;

import java.math.BigDecimal;
import java.util.ArrayList;
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
                .mapToObj(i->getProduct(i))
                .collect(Collectors.toList());
    }


    @Step("Products Page: Get products 'FROM' prices list. ")
    public List<BigDecimal> getFromPricesList(List<Product> productsList){
        return productsList
                .stream()
                .map(i->i.getFromPrice())
                .collect(Collectors.toList());
    }

    @Step("Products Page: Get products 'TO' prices list. ")
    public List<BigDecimal> getToPricesList(List<Product> productsList){
        return productsList
                .stream()
                .map(i->i.getToPrice())
                .collect(Collectors.toList());
    }

    @Step("ProductsPage: Get left-side categories menu")
    public LeftSideCategoriesMenu getLeftSideCategoriesMenu(){
        return new LeftSideCategoriesMenu();
    }

    @Step("ProductsPage: Open category {category} from category carousel")
    public ProductsPage openCategoryFromCarousel(CategoryCarouselElement category){
        $x(String.format("//a//p[text()='%s']",category.getCategoryName())).click();
        return new ProductsPage();
    }

    @Step("Products Page: Get minimal and maximal products price")
    public List<List<BigDecimal>> getProductsPrices(List<Product> productsList){
        List<List<BigDecimal>> productsMinMaxPrices = new ArrayList<>();
        productsMinMaxPrices.add(productsList
                .stream()
                .map(i->i.getFromPrice())
                .collect(Collectors.toList()));
        productsMinMaxPrices.add(productsList
                .stream()
                .map(i->i.getToPrice())
                .collect(Collectors.toList()));
        return productsMinMaxPrices;
    }

}
