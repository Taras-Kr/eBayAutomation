package com.taraskrasitskyi.ebay.ui.elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;

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

    @Step("Product: Get product price")
    public BigDecimal getPrice() {
        String itemPriceXpath = "//span[@class = 's-item__price']";
        String itemPriceInRangeXpath = "//span[@class = 'DEFAULT']";
        String stringPrice;
          if ($x(String.format("%s%s", xPath, itemPriceInRangeXpath)).exists()) {
            stringPrice = $x(String.format("%s%s", xPath, itemPriceXpath)).getText();
            stringPrice = stringPrice.substring(stringPrice.indexOf("to ") + 3);

         } else {
            stringPrice = $x(String.format("%s%s", xPath, itemPriceXpath)).getText();
        }
        stringPrice  =stringPrice
                    .replaceAll("\\$","")
                    .replaceAll(",","");
         price = BigDecimal.valueOf(Double.valueOf( stringPrice));
        return price;
    }


}
