package com.taraskrasitskyi.ebay.ui;

import com.taraskrasitskyi.ebay.ui.pages.HomePage;
import com.taraskrasitskyi.ebay.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.taraskrasitskyi.ebay.ui.elements.menus.leftsidecategoriesmenu.LeftSideCategoriesMenuItem.LAPTOPS_NOTEBOOKS;
import static com.taraskrasitskyi.ebay.ui.elements.menus.leftsidecategoriesmenu.LeftSideCategoriesMenuItemLink.PC_LAPTOPS_NOTEBOOKS;
import static com.taraskrasitskyi.ebay.ui.elements.menus.shopbycategorymenu.ShopByCategoryMenuItem.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FilteringTest extends TestRunner {

    @Test(description = "Verify that the filter options count increases when the user selects options in the filter")
    @Description(value = "Verify that the filter options count increases when the user selects options in the filter")
    @TmsLink(value = "SEL-9")
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
                .selectFilter("Screen Size")
                .selectOption(options.get(0));
        assertThat(filter.isOptionChecked(options.get(0)))
                .as("Filter option should be checked")
                .isEqualTo(true);
        selectedFilterCount++;
        assertThat(filter.getSelectedFiltersCount())
                .as(String.format("Selected filter count should be '%s'", selectedFilterCount))
                .isEqualTo(selectedFilterCount);

        filter.selectOption(options.get(1));
        assertThat(filter.isOptionChecked(options.get(1)))
                .as("Filter option should be checked")
                .isEqualTo(true);
        selectedFilterCount++;
        assertThat(filter.getSelectedFiltersCount())
                .as(String.format("Selected filter option count should be '%s'", selectedFilterCount))
                .isEqualTo(selectedFilterCount);

        filter
                .selectFilter("Brand")
                .selectOption(options.get(2));
        assertThat(filter.isOptionChecked(options.get(2)))
                .as("Filter option should be checked")
                .isEqualTo(true);
        selectedFilterCount++;
        assertThat(filter.getSelectedFiltersCount())
                .as(String.format("Selected filter option count should be '%s'", selectedFilterCount))
                .isEqualTo(selectedFilterCount);

        filter.selectOption(options.get(3));
        assertThat(filter.isOptionChecked(options.get(3)))
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
    @TmsLink(value = "SEL-10")
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
                .selectFilter("RAM Size")
                .selectOption("16 GB")
                .selectOption("8 GB");
        filterOptions.add("16 GB");
        filterOptions.add("8 GB");
        assertThat(filter.getSelectedFiltersCount())
                .as("Filter options should be selected")
                .isEqualTo(2);
        assertThat(filter.getSelectedFilters())
                .as("Filter options should be selected")
                .isEqualTo(filterOptions);

        filter
                .selectFilter("Processor")
                .selectOption("Intel Core i5 7th Gen.")
                .selectOption("AMD Ryzen 7");
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
        filter.selectFilter("RAM Size");
        assertThat(filter.isOptionChecked("16 GB"))
                .as("Filter option should be unchecked")
                .isEqualTo(false);
        assertThat(filter.isOptionChecked("8 GB"))
                .as("Filter option should be unchecked")
                .isEqualTo(false);

        filter
                .removeSelectedFilter("Intel Core i5 7th Gen.")
                .removeSelectedFilter("AMD Ryzen 7");
        filter.selectFilter("Processor");
        assertThat(filter.isOptionChecked("Intel Core i5 7th Gen."))
                .as("Filter option should be unchecked")
                .isEqualTo(false);
        assertThat(filter.isOptionChecked("AMD Ryzen 7"))
                .as("Filter option should be unchecked")
                .isEqualTo(false);
        assertThat(filter.getSelectedFiltersCount())
                .as("Selected filter options should be equal 0")
                .isEqualTo(0);
    }


}
