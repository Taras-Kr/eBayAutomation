package com.taraskrasitskyi.ebay.ui.elements.menus.shopbycategorymenu;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ShopByCategoryMenuItem {

    COMPUTERS_TABLETS("Computers & tablets"),
    CELL_PHONE_ACCESSORIES("Cell phones & accessories");
    @Getter
    private final String name;
    @Override
    public String toString() {
        return getName();
    }

}
