package com.taraskrasitskyi.ebay.ui.elements;


import io.qameta.allure.Step;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import static com.codeborne.selenide.Selenide.$x;

@RequiredArgsConstructor
public class Product {

    @NonNull
    private final String xPath;
    private String title;
    private BigDecimal price;

    @Step("Product: Get product title")
    public String getTitle() {
        title = $x(String.format("%s%s", xPath, "//h3")).getText();
        return title;
    }

    private String getStringPrice() {
        return $x(String.format("%s%s", xPath, "//span[@class = 's-item__price']")).getText();
    }

    //Some products have two prices 'FROM' an 'TO'. It returns 'FROM' price. If product has only one price it will be returned
    @Step("Product: Get 'FROM' Product Price")
    public BigDecimal getFromPrice() {
        String strPrice = getStringPrice();
        if (strPrice.contains("to ")) {
            strPrice = strPrice.substring(0, strPrice.indexOf("to ") - 1);

        }
        strPrice = strPrice
                .replaceAll("\\$", "")
                .replaceAll(",", "");
        price = BigDecimal.valueOf(Double.parseDouble(strPrice));
        return price;
    }

    //Some products have two prices 'FROM' an 'TO'. It returns 'TO' price. If product has only one price it will be returned
    @Step("Product: Get 'TO' Product Price")
    public BigDecimal getToPrice() {
        String strPrice = getStringPrice();
        if (strPrice.contains("to ")) {
            strPrice = strPrice.substring(strPrice.indexOf("to ") + 3);

        }
        strPrice = strPrice
                .replaceAll("\\$", "")
                .replaceAll(",", "");
        price = BigDecimal.valueOf(Double.parseDouble(strPrice));
        return price;
    }




}
