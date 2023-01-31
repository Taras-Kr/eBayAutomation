package com.taraskrasitskyi.ebay.ui.elements.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum RamSizeOption implements FilterOption {
    RAM_SIZE_8_GB("8 GB"),
    RAM_SIZE_16_GB("16 GB");

    @Getter
    private final String optionName;

    @Override
    public String toString() {
        return getOptionName();
    }
}
