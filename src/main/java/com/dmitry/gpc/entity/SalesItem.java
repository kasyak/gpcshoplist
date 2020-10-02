package com.dmitry.gpc.entity;

import com.dmitry.gpc.Constants;

public class SalesItem {
    private final String name;
    private double price;
    private final ItemCategory category;
    private boolean imported;

    public String getName() {
        return name;
    }

    public SalesItem(String name, double price, ItemCategory category, boolean imported) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.imported = imported;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public boolean isTaxExempt() {
        return this.category.isTaxExempt();
    }
    public boolean isImported() {
        return imported;
    }
    public void setImported(boolean imported) {
        this.imported = imported;
    }

    public String toPrettyString() {
        return name + " at " + Constants.df2.format(price) + (imported ? " (imported)" : "");
    }
}
