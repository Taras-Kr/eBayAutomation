package com.taraskrasitskyi.ebay.ui.elements;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum CategoryCarouselElement {
    MEN("Men"),
    COATS_JACKETS_VESTS ("Coats, Jackets & Vests");
    @Getter
    private String categoryName;

    @Override
    public String toString() {
        return getCategoryName();
    }
}
