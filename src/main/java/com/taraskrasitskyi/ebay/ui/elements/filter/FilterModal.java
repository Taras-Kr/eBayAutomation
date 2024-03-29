package com.taraskrasitskyi.ebay.ui.elements.filter;

import com.codeborne.selenide.SelenideElement;
import com.taraskrasitskyi.ebay.ui.pages.ProductsPage;
import io.qameta.allure.Step;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class FilterModal {

    @Step("FilterModal: get active filter name")
    public String getActiveFilterName() {
        return $x("//div[@aria-selected='true']").getText();
    }

    @Step("FilterModal: select filter {filterName}")
    public FilterModal selectFilter(FilterName filterName) {
        $x(String.format("//div//span[@class='x-overlay-aspect__label' and text()='%s']", filterName.getFilterName())).click();
        return this;
    }

    @Step("FilterModal: get option check box {optionName}")
    private SelenideElement getOptionCheckBox(FilterOption optionName) {
        String xPath = String.format("//div[@class='x-refine__multi-select x-overlay-sub-panel__aspect-option']" +
                "//span[text()='%s']/../..//input", optionName.getOptionName());
        return $x(xPath);
    }

    @Step("FilterModal: verify that option {optionName} is checked")
    public boolean isOptionChecked(FilterOption optionName) {
        return getOptionCheckBox(optionName).isSelected();
    }

    @Step("FilterModal: select option {optionName} check box")
    public FilterModal selectOption(FilterOption optionName) {
        SelenideElement optionCheckBox = getOptionCheckBox(optionName);
        optionCheckBox.shouldNot(checked);
        optionCheckBox.click();
        optionCheckBox.shouldBe(checked);
        return this;
    }

    @Step("FilterModal: unselect option {optionName} check box")
    public FilterModal unselectOption(FilterOption optionName) {
        SelenideElement optionCheckBox = getOptionCheckBox(optionName);
        optionCheckBox.shouldBe(checked);
        optionCheckBox.click();
        optionCheckBox.shouldNotBe(checked);
        return this;
    }

    @Step("FilterModal: get selected filters count")
    public int getSelectedFiltersCount() {
        String informLine = $x("//span[@class='x-tray__count']").getText();
        String selectedFiltersCount = informLine.replaceAll("\\D+", "");
        return Integer.parseInt(selectedFiltersCount);
    }

    @Step("FilterModal: get selected filters")
    public List<String> getSelectedFilters() {
        List<SelenideElement> selectedFiltersList = $$x("//div[@class='x-tray__breadcrumbs']//span");
        return selectedFiltersList
                .stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());
    }

    @Step("FilterModal: remove selected filter option {filterOptionName}")
    public FilterModal removeSelectedFilter(String filterOptionName){
        $x(String.format("//button[@aria-label = 'remove filter - %s']", filterOptionName)).click();
        return this;
    }

    @Step("FilterModal: input {priceFrom} and {priceTo}")
    public FilterModal inputPriceFromTo(String priceFrom, String priceTo){
        if(!priceFrom.isEmpty()) {
            $x("//input[@class='x-textrange__input x-textrange__input--from']").setValue(priceFrom);
        }
        if(!priceTo.isEmpty()) {
            $x("//input[@class='x-textrange__input x-textrange__input--to']").setValue(priceTo);
        }
        return this;
    }

    @Step("FilterModal: Apply filters")
    public ProductsPage applyFilters(){
        $x("//button[@class = 'x-overlay-footer__apply-btn btn btn--primary']").click();
        return new ProductsPage();
    }
}
