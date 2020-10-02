package com.dmitry.gpc.entity;

import java.util.HashSet;
import java.util.Set;

public enum ItemCategory {
    BOOK(true), FOOD(true), MEDICAL(true), TOY(false), OTHER(false);
    private static final Set<String> allNames = new HashSet<>();
    private final boolean taxExempt;

    static {
        for(ItemCategory category : ItemCategory.values()) {
            allNames.add(category.name());
        }
    }

    ItemCategory(boolean taxExempt) {
        this.taxExempt = taxExempt;
    }

    public boolean isTaxExempt() {
        return this.taxExempt;
    }

    public static ItemCategory fromString(String name) {
        String nameUpper = name.toUpperCase();
        if(allNames.contains(nameUpper)) {
            return ItemCategory.valueOf(nameUpper);
        } else {
            return ItemCategory.OTHER;
        }
    }
}
