package com.taraskrasitskyi.ebay.ui.elements.menus.leftsidecategoriesmenu;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum LeftSideCategoriesMenuItem {

    LAPTOPS_NOTEBOOKS ("Laptops & Netbooks");

    @Getter
    private final String name;
    @Override
    public String toString() {
        return getName();
    }
}
