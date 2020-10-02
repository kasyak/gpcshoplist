package com.dmitry.gpc;

import java.text.DecimalFormat;

// Normally, this would be external config values
public class Constants {
    public static final int TAX = 10;
    public static final int DUTY = 5;
    public static final DecimalFormat df2 = new DecimalFormat("#.00");

    public static final String LINEBREAK = System.getProperty("line.separator");
}
