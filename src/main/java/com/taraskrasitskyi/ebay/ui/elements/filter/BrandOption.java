package com.taraskrasitskyi.ebay.ui.elements.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum BrandOption implements FilterOption {
    BRAND_DELL("Dell"),
    BRAND_LG("LG");
    @Getter
    private final String optionName;

    @Override
    public String toString() {
        return getOptionName();
    }
}
