package com.taraskrasitskyi.ebay.ui.pages;

import com.taraskrasitskyi.ebay.ui.elements.TopMenu;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
       super(driver);
    }

    @Step("Get top menu")
    public TopMenu getTopMenu() {
        return new TopMenu(driver);
    }

}
