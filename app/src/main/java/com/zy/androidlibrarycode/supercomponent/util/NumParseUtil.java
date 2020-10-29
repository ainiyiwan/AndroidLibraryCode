package com.zy.androidlibrarycode.supercomponent.util;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 数字转化
 *
 * @author robert
 */
@SuppressLint("DefaultLocale")
public class NumParseUtil {

    // 人民币数字格式转化为分 结果为字符串
    public static String parseStringRMBToFen(String value) {
        double result = 0.00d;
        try {
            result = NumParseUtil.parseDouble(value);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return String.format("%.0f", result * 100);
    }

    /**
     * 取小数情况结尾0.00将去0，但不会四舍五入操作
     *
     * @param value
     * @return
     */
    public static String parseStringPriceWithTwoDecimals(String value) {
        if (TextUtils.isEmpty(value)) {
            return "0";
        }
        //#0.000 如果后面小数是.000将保留结尾的000 ,如0.12000将还是0.12000
        //#0.### 如果后面小数是.000将去除结尾的000 ,如0.12000将还是0.12
        DecimalFormat decimalFormat = new DecimalFormat("#0.##");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);
        return decimalFormat.format(new BigDecimal(value).divide(new BigDecimal(100), 2, RoundingMode.FLOOR));
    }

    /**
     * 取小数情况结尾0.00不去0，保留2位小数，但不会四舍五入操作
     *
     * @param value
     * @return
     */
    public static String parseStringPriceWithTwoDecimals0(String value) {
        if (TextUtils.isEmpty(value)) {
            return "0.00";
        }
        //#0.000 如果后面小数是.000将保留结尾的000 ,如0.12000将还是0.12000
        //#0.### 如果后面小数是.000将去除结尾的000 ,如0.12000将还是0.12
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);
        return decimalFormat.format(new BigDecimal(value).divide(new BigDecimal(100), 2, RoundingMode.FLOOR));
    }


    // value/100 两位小数字符 （人民币显示的数字格式）
    // 去除多余的0
    public static String parsePriceStringWithTwoDecimals(String value) {
        String price = String.format("%.2f", parseDouble(value) / 100.00d);
        return getPrettyNumber(price);
    }

    // value/100 两位小数字符 （人民币显示的数字格式）
    // 去除多余的0
    public static String parsePriceStringWithTwoDecimals(double value) {
        String price = String.format("%.2f", value / 100.00);
        return getPrettyNumber(price);
    }

    public static String parseStringWithoutTwoDecimals(String value) {
        String price = String.format("%d", parseInt(value) / 100);
        return getPrettyNumber(price);
    }

    // 去除数字里多余的0
    public static String getPrettyNumber(String number) {
        String bigDecimalStr;
        try {//当number==NaN，会throw "Infinity or NaN",所以要catch
            bigDecimalStr = BigDecimal.valueOf(parseDouble(number)).stripTrailingZeros().toPlainString();
        } catch (NumberFormatException e) {
            bigDecimalStr = "-1";
        }
        if (TextUtils.isEmpty(bigDecimalStr)) {
            return "0";
        }
        if (bigDecimalStr.endsWith(".00") || bigDecimalStr.endsWith(".0")) {
            return bigDecimalStr.substring(0, bigDecimalStr.lastIndexOf("."));
        }
        return bigDecimalStr;
    }

    // 去除数字里多余的0
    public static String getPrettyNumber(double number) {
        String bigDecimalStr;
        try {//当number==NaN，会throw "Infinity or NaN",所以要catch
            bigDecimalStr = BigDecimal.valueOf(number).stripTrailingZeros().toPlainString();
        } catch (NumberFormatException e) {
            bigDecimalStr = "-1";
        }
        if (TextUtils.isEmpty(bigDecimalStr)) {
            return "0";
        }
        if (bigDecimalStr.endsWith(".00") || bigDecimalStr.endsWith(".0")) {
            return bigDecimalStr.substring(0, bigDecimalStr.lastIndexOf("."));
        }
        return bigDecimalStr;
    }


    public static String parseStringWithOneDecimals(double value) {
        return String.format("%.1f", value / 1000);
    }

    // String -->> boolean
    public static boolean parseBoolean(String value) {
        return parseBoolean(value, false);
    }

    public static String parseYuanToCent(String price) {
        return String.valueOf(new BigDecimal(price).multiply(new BigDecimal(100)).divide(new BigDecimal(1), 1, BigDecimal.ROUND_HALF_UP).intValue());
    }

    // 设置驼峰型金额
    public static String getProgressPercent(int progress) {
        java.text.NumberFormat percentFormat = java.text.NumberFormat.getPercentInstance();
        //最大小数位数
        percentFormat.setMaximumFractionDigits(0);
        //最大整数位数
        percentFormat.setMaximumIntegerDigits(3);
        //最小整数位数
        percentFormat.setMinimumIntegerDigits(1);

        return percentFormat.format(progress / 100);
    }

    /**
     * 提供精确加法计算的add方法
     *
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和
     */
    public static double add(double value1, double value2) {
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确减法运算的sub方法
     *
     * @param value1 被减数
     * @param value2 减数
     * @return 两个参数的差
     */
    public static double sub(double value1, double value2) {
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确乘法运算的mul方法
     *
     * @param value1 被乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
    public static double mul(double value1, double value2) {
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供精确的除法运算方法div
     *
     * @param value1 被除数
     * @param value2 除数
     * @param scale  精确范围
     * @return 两个参数的商
     * @throws IllegalAccessException
     */
    public static double div(double value1, double value2, int scale) throws IllegalAccessException {
        //如果精确范围小于0，抛出异常信息
        if (scale < 0) {
            throw new IllegalAccessException("精确度不能小于0");
        }
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        BigDecimal divide = b1.divide(b2, scale, BigDecimal.ROUND_DOWN);
        return divide.doubleValue();
    }

    // 测试
    public static void main(String[] args) {
        BigDecimal b1 = BigDecimal.valueOf(675.2000331878662);
        BigDecimal b2 = BigDecimal.valueOf(100);
        BigDecimal divide = b1.divide(b2, 2, BigDecimal.ROUND_DOWN);
        System.out.println(divide.doubleValue());
    }

    public static double div(String value1, String value2, int scale) throws IllegalAccessException {
        //如果精确范围小于0，抛出异常信息
        if (scale < 0) {
            throw new IllegalAccessException("精确度不能小于0");
        }
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.divide(b2, scale).doubleValue();
    }

    public static int parseDoubleToInt(double value) {
        return new BigDecimal(value).intValue();
    }

    /**
     * 字符串 ==>> long
     *
     * @param value String
     * @return long
     */
    public static long parseLong(String value) {
        return parseLong(value, 0L);
    }

    /**
     * 字符串 ==>> long
     * 指定默认值
     *
     * @param value        String
     * @param defaultValue 默认值
     * @return long
     */
    public static long parseLong(String value, long defaultValue) {
        if (TextUtils.isEmpty(value)) {
            return defaultValue;
        }
        long resultValue = defaultValue;
        try {
            resultValue = Long.parseLong(value);
        } catch (Exception e) {
            e.printStackTrace();
            return resultValue;
        }
        return resultValue;
    }

    /**
     * 字符串 ==>> double
     *
     * @param value String
     * @return double
     */
    public static double parseDouble(String value) {
        return parseDouble(TextUtils.isEmpty(value) ? "0" : value, 0.0D);
    }

    /**
     * 字符串 ==>> double
     * 指定默认值
     *
     * @param value        String
     * @param defaultValue 默认值
     * @return double
     */
    public static double parseDouble(String value, double defaultValue) {
        if (TextUtils.isEmpty(value)) {
            return defaultValue;
        }
        double resultValue;
        try {
            resultValue = Double.parseDouble(value);
        } catch (Exception e) {
            e.printStackTrace();
            resultValue = defaultValue;
        }
        return resultValue;
    }

    /**
     * 字符串 ==>> float
     *
     * @param value String
     * @return float
     */
    public static float parseFloat(String value) {
        return parseFloat(value, 0.0F);
    }

    /**
     * 字符串 ==>> float
     * 指定默认值
     *
     * @param value        String
     * @param defaultValue 默认值
     * @return float
     */
    public static float parseFloat(String value, float defaultValue) {
        if (TextUtils.isEmpty(value)) {
            return defaultValue;
        }
        float resultValue;
        try {
            resultValue = Float.parseFloat(value);
        } catch (Exception e) {
            e.printStackTrace();
            resultValue = defaultValue;
        }
        return resultValue;
    }

    /**
     * 字符串 ==>> int
     *
     * @param value String
     * @return int
     */
    public static int parseInt(String value) {
        return parseInt(value, 0);
    }

    /**
     * 字符串 ==>> int
     * 指定默认值
     *
     * @param value        String
     * @param defaultValue 默认值
     * @return int
     */
    public static int parseInt(String value, int defaultValue) {
        String otherErrorValue = "{}";
        if (TextUtils.isEmpty(value) || otherErrorValue.equals(value)) {
            return defaultValue;
        }
        int resultValue;
        try {
            resultValue = Integer.parseInt(value);
        } catch (Exception e) {
            e.printStackTrace();
            resultValue = defaultValue;
        }
        return resultValue;
    }

    /**
     * Object ==>> int
     *
     * @param object Object
     * @return int
     */
    public static int parseInt(Object object) {
        return NumParseUtil.parseInt(String.valueOf(object));
    }

    // String -->> boolean, 缺省值defaultValue
    public static boolean parseBoolean(String value, boolean defaultValue) {
        boolean resultValue = defaultValue;
        try {
            resultValue = TextUtils.isEmpty(value) ? defaultValue : Boolean.parseBoolean(value);
        } catch (Exception e) {
            e.printStackTrace();
            resultValue = defaultValue;
        }
        return resultValue;
    }

}
