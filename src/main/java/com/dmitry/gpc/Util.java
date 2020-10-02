package com.dmitry.gpc;

import com.dmitry.gpc.entity.SalesItem;

import java.math.BigDecimal;

public class Util {

    public static BigDecimal calculateSalesTax(SalesItem item, int taxPercent, int dutyPercent) {
        int salesTax = 0;
        if(! item.isTaxExempt()) {
            salesTax += taxPercent;
        }
        if(item.isImported()) {
            salesTax += dutyPercent;
        }

        if(salesTax == 0) {
            return BigDecimal.ZERO;
        }

        return roundUpToFiveDecimal(BigDecimal.valueOf(item.getPrice()).
                multiply(BigDecimal.valueOf(salesTax)).divide(BigDecimal.valueOf(100D)));
    }

    public static BigDecimal roundUpToFiveDecimal(BigDecimal value) {
        long intVal = value.multiply(BigDecimal.valueOf(100)).longValue();
        long ost = intVal % 5;
        if(ost != 0) {
            intVal += 5 - ost;
        }
        return BigDecimal.valueOf(intVal).divide(BigDecimal.valueOf(100));
    }

}
