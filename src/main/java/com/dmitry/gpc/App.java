package com.dmitry.gpc;

import com.dmitry.gpc.entity.ItemCategory;
import com.dmitry.gpc.entity.Order;
import com.dmitry.gpc.entity.SalesItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class App {
    public static void main(String[] args) {
        List<SalesItem> items1 = loadSalesItems("input1.txt");
        System.out.println(" Order 1 ----------------------------- ");
        processOrder(items1);

        List<SalesItem> items2 = loadSalesItems("input2.txt");
        System.out.println(" Order 2 ----------------------------- ");
        processOrder(items2);

        List<SalesItem> items3 = loadSalesItems("input3.txt");
        System.out.println(" Order 3 ----------------------------- ");
        processOrder(items3);
    }

    private static void processOrder(List<SalesItem> items) {
        items.forEach(s -> System.out.println(s.toPrettyString()));
        Order order1 = new Order(items);
        System.out.println();
        System.out.println(order1.generateReceipt());
    }

    private static List<SalesItem> loadSalesItems(String fileName) {
        List<SalesItem> result = new ArrayList<>();
        InputStream stream = App.class.getClassLoader().getResourceAsStream(fileName);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            while ((line = in.readLine()) != null) {
                String[] vals = line.split(",");
                ItemCategory category = ItemCategory.fromString(vals[2]);
                SalesItem item = new SalesItem(vals[0], Double.parseDouble(vals[1]),
                        category, Boolean.parseBoolean(vals[3]));
                result.add(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading input from file");
        }

        return result;
    }
}
