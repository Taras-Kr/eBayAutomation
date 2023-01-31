package com.taraskrasitskyi.ebay.ui.elements.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ProcessorOption implements FilterOption {

    PROC_OPT_INTEL_CORE_I5_7TH_GEN ("Intel Core i5 7th Gen."),
    PROC_OPT_MD_RYZEN_7 ("AMD Ryzen 7");

    @Getter
    private final  String optionName;

    @Override
    public String toString() {
        return getOptionName();
    }
}
