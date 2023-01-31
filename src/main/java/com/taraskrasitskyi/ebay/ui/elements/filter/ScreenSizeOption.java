package com.taraskrasitskyi.ebay.ui.elements.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ScreenSizeOption implements FilterOption {
    SCREEN_SIZE_13_13_9_IN("13-13.9 in"),
    SCREEN_SIZE_16_16_9_IN("16-16.9 in");
    @Getter
    private final String optionName;

    @Override
    public String toString() {
        return getOptionName();
    }
}
