package com.taraskrasitskyi.ebay.ui.elements.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum FilterName {
    RAM_SIZE("RAM Size"),
    PROCESSOR("Processor"),
    SCREEN_SIZE("Screen Size"),
    BRAND("Brand"),
    PRICE("Price");
    @Getter
    private final String filterName;

    @Override
    public String toString() {
        return getFilterName();
    }
}
