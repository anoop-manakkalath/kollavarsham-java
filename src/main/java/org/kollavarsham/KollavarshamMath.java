/*
 * Kollavarsham
 * http://kollavarsham.org
 *
 * Copyright (c) 2014 - 2017 The Kollavarsham Team
 * Licensed under the MIT license.
 */
package org.kollavarsham;

import java.lang.Math;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParsePosition;

public final class KollavarshamMath {

    public static final double EPSILON = 1E-8;
    public static final double RADIAN_MULTIPLIER = 180.0 / Math.PI;

    public static boolean isNumber(String str) {
        ParsePosition pos = new ParsePosition(0);
        NumberFormat.getInstance().parse(str, pos);
        return str.length() == pos.getIndex();
    }

    public static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static Double truncateDecimals(double x, int numberofDecimals) {
        BigDecimal truncated;
        if (x > 0) {
            truncated = new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_FLOOR);
        }
        else {
            truncated = new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_CEILING);
        }
        return truncated.doubleValue();
    }

    public static Double truncate(Double num) {
        return truncateDecimals(num, 0);
    }

    public static double floor(Double num) {
        return Math.floor(num);
    }

    public static double fractional(Double num) {
//      Might need to revisit for the precision based on the junit results. (Not the delta .01 set in assertEquals)
        return num % 1;
    }

    public static long round(Double num) {
        return Math.round(num);
    }

    public static double square(double num) {
        return Math.pow(num, 2.0);
    }

    public static Boolean floatingPointEqual(Double n1, Double n2) {
        return Math.abs(n1 - n2) < EPSILON;
    }

}

