package com.dmitry.gpc.entity;

import com.dmitry.gpc.Constants;
import com.dmitry.gpc.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Order {
    List<SalesItem> items;

    public Order(List<SalesItem> items) {
        this.items = items;
    }

    public double calculateTotalTax() {
        return items
                .stream()
                .mapToDouble(t -> Util.calculateSalesTax(t, Constants.TAX, Constants.DUTY).doubleValue())
                .sum();
    }

    public String generateReceipt() {
        StringBuilder sb = new StringBuilder();
        sb.append("Receipt for order:").append(Constants.LINEBREAK);

        BigDecimal totalTax = BigDecimal.ZERO;
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (SalesItem item : items) {
            BigDecimal addedTax = Util.calculateSalesTax(item, Constants.TAX, Constants.DUTY);
            BigDecimal priceWithTax = BigDecimal.valueOf(item.getPrice()).add(addedTax);
            sb.append(item.getName()).append(": ").append(Constants.df2.format(priceWithTax)).append(Constants.LINEBREAK);
            totalTax = totalTax.add(addedTax);
            totalPrice = totalPrice.add(priceWithTax);
        }
        sb.append("Total tax: ").append(Constants.df2.format(totalTax.doubleValue())).append(Constants.LINEBREAK);
        sb.append("Total: ").append(Constants.df2.format(totalPrice.doubleValue()));
        return sb.toString();
    }

    public void addItem(SalesItem item) {
        items.add(item);
    }
}
