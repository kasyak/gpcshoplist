package com.dmitry.gpc;

import static org.junit.Assert.assertEquals;

import com.dmitry.gpc.entity.ItemCategory;
import com.dmitry.gpc.entity.Order;
import com.dmitry.gpc.entity.SalesItem;
import org.junit.Test;

import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void testTaxationSalesTaxOnly() {
        SalesItem toyImport = new SalesItem("other1", 12.99, ItemCategory.TOY, false);
        assertEquals(1.3, Util.calculateSalesTax(toyImport, Constants.TAX, Constants.DUTY).doubleValue(), .001);
    }

    @Test
    public void testTaxationBoth() {
        SalesItem otherImport = new SalesItem("other2", 14.49, ItemCategory.OTHER, true);
        assertEquals(2.20, Util.calculateSalesTax(otherImport, Constants.TAX, Constants.DUTY).doubleValue(), .001);
    }

    @Test
    public void testTaxationDutyOnly() {
        SalesItem bookImport = new SalesItem("book1", 12.49, ItemCategory.BOOK, true);
        assertEquals(0.65, Util.calculateSalesTax(bookImport, Constants.TAX, Constants.DUTY).doubleValue(), 0);
    }

    @Test
    public void testTaxationNoTax() {
        SalesItem bookImport = new SalesItem("book1", 12.49, ItemCategory.BOOK, false);
        assertEquals(0, Util.calculateSalesTax(bookImport, Constants.TAX, Constants.DUTY).doubleValue(), 0);
    }

    @Test
    public void testTaxationForOrder() {
        SalesItem item1 = new SalesItem("book1", 27.99, ItemCategory.OTHER, true);
        SalesItem item2 = new SalesItem("book1", 18.99, ItemCategory.TOY, false);
        SalesItem item3 = new SalesItem("book1", 9.75, ItemCategory.BOOK, false);
        SalesItem item4 = new SalesItem("book1", 11.25, ItemCategory.MEDICAL, true);
        Order order = new Order(Arrays.asList(item1, item2, item3, item4));
        assertEquals(6.70, order.calculateTotalTax(), 0);

    }
}

