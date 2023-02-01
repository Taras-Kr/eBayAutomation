package com.taraskrasitskyi.ebay.ui.elements.menus.leftsidecategoriesmenu;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum LeftSideCategoriesMenuItemLink {

    PC_LAPTOPS_NOTEBOOKS("\"PC Laptops & Netbooks\""),
    CELL_PHONES_SMARTPHONES("\"Cell Phones & Smartphones\""),
    MENS_CLOTHING ("\"Men's Clothing\"");


    @Getter
    private final String name;
    @Override
    public String toString() {
        return getName();
    }
 }
