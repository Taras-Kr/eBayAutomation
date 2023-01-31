package com.taraskrasitskyi.ebay.ui;

import com.taraskrasitskyi.ebay.ui.elements.filter.BrandOption;
import com.taraskrasitskyi.ebay.ui.elements.filter.ProcessorOption;
import com.taraskrasitskyi.ebay.ui.elements.filter.RamSizeOption;
import com.taraskrasitskyi.ebay.ui.elements.filter.ScreenSizeOption;
import com.taraskrasitskyi.ebay.ui.pages.HomePage;
import com.taraskrasitskyi.ebay.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.taraskrasitskyi.ebay.ui.elements.filter.FilterName.*;
import static com.taraskrasitskyi.ebay.ui.elements.menus.leftsidecategoriesmenu.LeftSideCategoriesMenuItem.LAPTOPS_NOTEBOOKS;
import static com.taraskrasitskyi.ebay.ui.elements.menus.leftsidecategoriesmenu.LeftSideCategoriesMenuItemLink.CELL_PHONES_SMARTPHONES;
import static com.taraskrasitskyi.ebay.ui.elements.menus.leftsidecategoriesmenu.LeftSideCategoriesMenuItemLink.PC_LAPTOPS_NOTEBOOKS;
import static com.taraskrasitskyi.ebay.ui.elements.menus.shopbycategorymenu.ShopByCategoryMenuItem.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FilteringTest extends TestRunner {

    @Test(description = "Verify that the filter options count increases when the user selects options in the filter")
    @Description(value = "Verify that the filter options count increases when the user selects options in the filter")
    @TmsLink(value = "EBA-4")
    public void verifyThatFilterOptionsCountIncreases() {
        var categoryPage = new HomePage()
                .open()
                .getHeader()
                .openShopByCategoryMenu()
                .openCategory(COMPUTERS_TABLETS);
        assertThat(categoryPage.getPageCaption())
                .as("Category page should be opened")
                .isEqualTo("Computers & Accessories");
        var productsPage = categoryPage
                .getLeftSideCategoriesMenu()
                .expandMenuItem(LAPTOPS_NOTEBOOKS)
                .openMenuItem(PC_LAPTOPS_NOTEBOOKS);
        assertThat(productsPage.getPageCaption())
                .as("Products page should be opened")
                .isEqualTo("PC Laptops & Netbooks");
        var filter = productsPage
                .openAllFilters();

        int selectedFilterCount = 0;
        assertThat(filter.getSelectedFiltersCount())
                .as(String.format("Selected filter count should be '%s'", selectedFilterCount))
                .isEqualTo(selectedFilterCount);
        List<String> options = Arrays.asList("13-13.9 in", "16-16.9 in", "Dell", "LG");

        filter
                .selectFilter(SCREEN_SIZE)
                .selectOption(ScreenSizeOption.SCREEN_SIZE_13_13_9_IN);
        assertThat(filter.isOptionChecked(ScreenSizeOption.SCREEN_SIZE_13_13_9_IN))
                .as("Filter option should be checked")
                .isEqualTo(true);
        selectedFilterCount++;
        assertThat(filter.getSelectedFiltersCount())
                .as(String.format("Selected filter count should be '%s'", selectedFilterCount))
                .isEqualTo(selectedFilterCount);

        filter.selectOption(ScreenSizeOption.SCREEN_SIZE_16_16_9_IN);
        assertThat(filter.isOptionChecked(ScreenSizeOption.SCREEN_SIZE_16_16_9_IN))
                .as("Filter option should be checked")
                .isEqualTo(true);
        selectedFilterCount++;
        assertThat(filter.getSelectedFiltersCount())
                .as(String.format("Selected filter option count should be '%s'", selectedFilterCount))
                .isEqualTo(selectedFilterCount);

        filter
                .selectFilter(BRAND)
                .selectOption(BrandOption.BRAND_DELL);
        assertThat(filter.isOptionChecked(BrandOption.BRAND_DELL))
                .as("Filter option should be checked")
                .isEqualTo(true);
        selectedFilterCount++;
        assertThat(filter.getSelectedFiltersCount())
                .as(String.format("Selected filter option count should be '%s'", selectedFilterCount))
                .isEqualTo(selectedFilterCount);

        filter.selectOption(BrandOption.BRAND_LG);
        assertThat(filter.isOptionChecked(BrandOption.BRAND_LG))
                .as("Filter option should be checked")
                .isEqualTo(true);
        selectedFilterCount++;
        assertThat(filter.getSelectedFiltersCount())
                .as(String.format("Selected filter option count should be '%s'", selectedFilterCount))
                .isEqualTo(selectedFilterCount);

        assertThat(options)
                .as("Selected filter options should be equal to options for select")
                .isEqualTo(filter.getSelectedFilters());
    }

    @Test(description = "Verify that the option check box is unselected when the user deletes the filter from the selected filter options list")
    @Description(value = "Verify that the option check box is unselected when the user deletes the filter from the selected filter options list")
    @TmsLink(value = "EBA-1")
    public void verifyThatFilterOptionsUnselects() {
        var categoryPage = new HomePage()
                .open()
                .getHeader()
                .openShopByCategoryMenu()
                .openCategory(COMPUTERS_TABLETS);
        assertThat(categoryPage.getPageCaption())
                .as("Category page should be opened")
                .isEqualTo("Computers & Accessories");

        var productsPage = categoryPage
                .getLeftSideCategoriesMenu()
                .expandMenuItem(LAPTOPS_NOTEBOOKS)
                .openMenuItem(PC_LAPTOPS_NOTEBOOKS);
        assertThat(productsPage.getPageCaption())
                .as("Products page should be opened")
                .isEqualTo("PC Laptops & Netbooks");

        List<String> filterOptions = new ArrayList<>();
        var filter = productsPage.openAllFilters();
        filter
                .selectFilter(RAM_SIZE)
                .selectOption(RamSizeOption.RAM_SIZE_16_GB)
                .selectOption(RamSizeOption.RAM_SIZE_8_GB);
        filterOptions.add("16 GB");
        filterOptions.add("8 GB");
        assertThat(filter.getSelectedFiltersCount())
                .as("Filter options should be selected")
                .isEqualTo(2);
        assertThat(filter.getSelectedFilters())
                .as("Filter options should be selected")
                .isEqualTo(filterOptions);

        filter
                .selectFilter(PROCESSOR)
                .selectOption(ProcessorOption.PROC_OPT_INTEL_CORE_I5_7TH_GEN)
                .selectOption(ProcessorOption.PROC_OPT_MD_RYZEN_7);
        filterOptions.add("Intel Core i5 7th Gen.");
        filterOptions.add("AMD Ryzen 7");
        assertThat(filter.getSelectedFiltersCount())
                .as("Filter options should be selected")
                .isEqualTo(4);
        assertThat(filter.getSelectedFilters())
                .as("Filter options should be selected")
                .isEqualTo(filterOptions);

        filter
                .removeSelectedFilter("16 GB")
                .removeSelectedFilter("8 GB");
        filter.selectFilter(RAM_SIZE);
        assertThat(filter.isOptionChecked(RamSizeOption.RAM_SIZE_16_GB))
                .as("Filter option should be unchecked")
                .isEqualTo(false);
        assertThat(filter.isOptionChecked(RamSizeOption.RAM_SIZE_8_GB))
                .as("Filter option should be unchecked")
                .isEqualTo(false);

        filter
                .removeSelectedFilter("Intel Core i5 7th Gen.")
                .removeSelectedFilter("AMD Ryzen 7");
        filter.selectFilter(PROCESSOR);
        assertThat(filter.isOptionChecked(ProcessorOption.PROC_OPT_INTEL_CORE_I5_7TH_GEN))
                .as("Filter option should be unchecked")
                .isEqualTo(false);
        assertThat(filter.isOptionChecked(ProcessorOption.PROC_OPT_MD_RYZEN_7))
                .as("Filter option should be unchecked")
                .isEqualTo(false);
        assertThat(filter.getSelectedFiltersCount())
                .as("Selected filter options should be equal 0")
                .isEqualTo(0);
    }

    @Test(description = "Verify that an unregistered user can see the selected filter options after returning to filter from another filter")
    @Description(value = "Verify that an unregistered user can see the selected filter options after returning to filter from another filter")
    @TmsLink(value = "EBA-2")
    public void verifyThatUserCanSeeEarlySelectedFilterOptions() {
        var filter = new HomePage()
                .open()
                .getHeader()
                .openShopByCategoryMenu()
                .openCategory(COMPUTERS_TABLETS)
                .getLeftSideCategoriesMenu()
                .expandMenuItem(LAPTOPS_NOTEBOOKS)
                .openMenuItem(PC_LAPTOPS_NOTEBOOKS)
                .openAllFilters();

        filter
                .selectFilter(RAM_SIZE)
                .selectOption(RamSizeOption.RAM_SIZE_16_GB)
                .selectOption(RamSizeOption.RAM_SIZE_8_GB);
        assertThat(filter.isOptionChecked(RamSizeOption.RAM_SIZE_16_GB))
                .as("Filter option should be checked")
                .isTrue();
        assertThat(filter.isOptionChecked(RamSizeOption.RAM_SIZE_8_GB))
                .as("Filter option should be checked")
                .isTrue();

        filter
                .selectFilter(PROCESSOR)
                .selectOption(ProcessorOption.PROC_OPT_INTEL_CORE_I5_7TH_GEN)
                .selectOption(ProcessorOption.PROC_OPT_MD_RYZEN_7);
        assertThat(filter.isOptionChecked(ProcessorOption.PROC_OPT_INTEL_CORE_I5_7TH_GEN))
                .as("Filter option should be checked")
                .isTrue();
        assertThat(filter.isOptionChecked(ProcessorOption.PROC_OPT_MD_RYZEN_7))
                .as("AMD Ryzen 7")
                .isTrue();

        var softAssert = new SoftAssertions();
        filter.selectFilter(RAM_SIZE);
        softAssert.assertThat(filter.isOptionChecked(RamSizeOption.RAM_SIZE_16_GB))
                .as("Filter option 16 GB should be checked")
                .isTrue();
        softAssert.assertThat(filter.isOptionChecked(RamSizeOption.RAM_SIZE_8_GB))
                .as("Filter option 8 GB should be checked")
                .isTrue();
        softAssert.assertAll();
    }

    @Test(description = "Verify that an unregistered user can filter goods by price in range over the minimal price")
    @Description(value = "Verify that an unregistered user can filter goods by price in range over the minimal price")
    @TmsLink(value = "EBA-9")
    public void verifyThatUserCanFilteringProductsFromMinPrice() {
        var minPrice = new BigDecimal("799.99");
        var productsPage = new HomePage()
                .open()
                .getHeader()
                .openShopByCategoryMenu()
                .openCategory(CELL_PHONE_ACCESSORIES)
                .getLeftSideCategoriesMenu()
                .openMenuItem(CELL_PHONES_SMARTPHONES)
                .openAllFilters()
                .selectFilter(PRICE)
                .inputPriceFromTo(String.valueOf(minPrice), "")
                .applyFilters();
        List<BigDecimal> pricesList = productsPage.getToPricesList(productsPage.getProductsList());
        SoftAssertions softAssert = new SoftAssertions();
        for (BigDecimal price : pricesList) {
            softAssert
                    .assertThat(price)
                    .as("Product price should be greater or equal " + minPrice + ": Price - " + price)
                    .isGreaterThanOrEqualTo(minPrice);
        }
        softAssert.assertAll();
    }

    @Test(description = "Verify that an unregistered user can filter goods by price in the range under max price")
    @Description(value = "Verify that an unregistered user can filter goods by price in the range under max price")
    @TmsLink(value = "EBA-10")
    public void verifyThatUserCanFilteringProductsToMaxPrice() {
        var maxPrice = new BigDecimal("299.99");
        var productsPage = new HomePage()
                .open()
                .getHeader()
                .openShopByCategoryMenu()
                .openCategory(CELL_PHONE_ACCESSORIES)
                .getLeftSideCategoriesMenu()
                .openMenuItem(CELL_PHONES_SMARTPHONES)
                .openAllFilters()
                .selectFilter(PRICE)
                .inputPriceFromTo("", maxPrice.toString())
                .applyFilters();
        List<BigDecimal> pricesList = productsPage.getFromPricesList(productsPage.getProductsList());
        SoftAssertions softAssert = new SoftAssertions();
        for (BigDecimal price : pricesList) {
            softAssert
                    .assertThat(price)
                    .as("Product price should be less or equal " + maxPrice + ": Price - " + price)
                    .isLessThanOrEqualTo(maxPrice);
        }
        softAssert.assertAll();
    }
}
