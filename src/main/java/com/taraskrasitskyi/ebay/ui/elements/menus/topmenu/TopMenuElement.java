package com.taraskrasitskyi.ebay.ui.elements.menus.topmenu;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TopMenuElement {
    ELECTRONICS("Electronics");

    @Getter
    private final String name;

    @Override
    public String toString() {
        return getName();
    }
}
