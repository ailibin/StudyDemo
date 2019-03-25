package com.aiitec.studydemo.test;

import org.junit.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * @Author: $user
 * @Time: ${date} ${time}
 * @Description: 模板注释
 * @Email: ailibin@qq.com
 */
public class Test {

    public static void main(String[] args) throws Exception {
        char[] testCharArray = new char[]{'h', 'e', 'l', 'l', 'o'};
//        reverseString(testCharArray);
//        testDecimal(12);
//        testDecimal(12.111111);
//        testDecimal(12.01111);
//        testDecimal(12.0000000);
//        formatDouble(12.01);
//        formatDecimal(3.1415927);
//        testDecimalMix2(299792458.0000);
//        testDecimalMix3(299792458.0000);
//        testDecimalMix(299792458.1415927);
//        doubleTrans(12.110);
//        doubleTrans2(12);
//        tokenTransform("http://url.com/image.jpg?token=d9caa6e02c990b0a");
        tokenTransform("http://url.com/image.jpg&token=d9caa6e02c990b0a");
    }

    public static void testMap() {

    }

    //double类型如果小数点后为零则显示整数否则保留两位小数,后台输出什么就是什么,
    private static String testDecimal(double decimal) {
        //12.0
//        System.out.println(decimal);
//        //12.0
//        System.out.println(Double.toString(decimal));
//        //12.0
//        System.out.println(String.valueOf(decimal));
        DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
        //12
//        System.out.println(decimalFormat.format(decimal));
        return decimalFormat.format(decimal);
    }

    private static String testDecimal1(double decimal) {
        System.out.println(new DecimalFormat(",###").format(decimal));
        return new DecimalFormat(",###").format(decimal);
    }


    private static void formatDecimal(double pi) {

        //取一位整数
        System.out.println(new DecimalFormat("0").format(pi));//3
        //取一位整数和两位小数
        System.out.println(new DecimalFormat("0.00").format(pi));//3.14
        //取两位整数和三位小数，整数不足部分以0填补。
        System.out.println(new DecimalFormat("00.000").format(pi));//03.142
        //取所有整数部分
        System.out.println(new DecimalFormat("#").format(pi));//3
        //以百分比方式计数，并取两位小数
        System.out.println(new DecimalFormat("#.##%").format(pi));//314.16%

//        long c=299792458;//光速
//        double c = 299792458.0000;//光速
        double c = 2993;//光速
        //显示为科学计数法，并取五位小数
        System.out.println(new DecimalFormat("#.#####E0").format(c));//2.99792E8
        //显示为两位整数的科学计数法，并取四位小数
        System.out.println(new DecimalFormat("00.####E0").format(c));//29.9792E7
        //每三位以逗号进行分隔。
        System.out.println(new DecimalFormat(",###").format(c));//299,792,458
        //将格式嵌入文本
        System.out.println(new DecimalFormat("光速大小为每秒,###米").format(c)); //光速大小为每秒299,792,458米
    }

    private static void testDecimalMix(double decimal) {
        String content = testDecimal(decimal);
        System.out.println(content);
        double doubleContent = Double.parseDouble(content);
        System.out.println(doubleContent);
        testDecimal1(doubleContent);
    }

    private static void testDecimalMix2(double decimal) {
        NumberFormat nf = new DecimalFormat("$,###.##");
//        NumberFormat nf1 = NumberFormat.getCurrencyInstance(Locale.US);
        NumberFormat nf1 = NumberFormat.getCurrencyInstance(Locale.CHINA);
        //格式化
//        System.out.println(nf1.format(123456789));
        String testStr = nf.format(123456789.0);
        System.out.println(testStr);
//        System.out.println(new DecimalFormat("#,###.##").format(decimal));
    }

    private static void testDecimalMix3(double decimal) {
        NumberFormat nf = new DecimalFormat("$,###.##");
        //格式化
        String testStr = nf.format(123456789.0);
        System.out.println(testStr);
    }


    /**
     * @param d double类型如果小数点后为零显示整数否则保留两位小数 返回String 比输入12.01111 输出12.02 四舍五入
     * @return
     */
    public static String formatDouble(double d) {
        BigDecimal bg = new BigDecimal(d).setScale(2, RoundingMode.HALF_UP);
        double num = bg.doubleValue();
        if (Math.round(num) - num == 0) {
            System.out.println(String.valueOf((long) num));
            return String.valueOf((long) num);
        }
        System.out.println(String.valueOf(num));
        return String.valueOf(num);
    }

    /**
     * double类型如果小数点后为零显示整数否则保留 返回String
     *
     * @param num
     * @return
     */
    public static String doubleTrans(double num) {
        //只保留小数点后6位
        String number1 = String.format("%.6f", num);
        //类型转换
        double number2 = Double.parseDouble(number1);
        if (Math.round(number2) - number2 == 0) {
            System.out.println(String.valueOf((long) number2));
            return String.valueOf((long) number2);
        }
        System.out.println(number2);
        return String.valueOf(number2);
    }


    /**
     * double类型如果小数点后为零显示整数否则保留 返回String
     *
     * @param num
     * @return
     */
    public static String doubleTrans2(double num) {
        System.out.println(String.valueOf(new DecimalFormat("#0.000000").format(num)));
        return String.valueOf(new DecimalFormat("#0.000000").format(num));
    }


    /**
     * 反转一个字符数组:
     * Input: ["h","e","l","l","o"]
     * Output: ["o","l","l","e","h"]
     *
     * @param s
     */
    public static void reverseString(char[] s) {

        //将一个char数组转换成String
        String str = String.valueOf(s);
        String result = "";
        char[] actualResult;
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
            result = sb.toString();
        }
        actualResult = result.toCharArray();
        Assert.assertArrayEquals("是否相等", new char[]{'o', 'l', 'l', 'e', 'h'}, actualResult);
//        Log.e("ailibin", result);
    }

    /**
     * 去掉token关键的string
     *
     * @param url http://url.com/image.jpg?token=d9caa6e02c990b0a
     */
    public static String tokenTransform(String url) {

        String tokenParam = "";
        int tokenKeyIndex = url.indexOf("?token=") >= 0 ? url.indexOf("?token=") : url.indexOf("&token=");
        if (tokenKeyIndex != -1) {
            int nextAndIndex = url.indexOf("&", tokenKeyIndex + 1);
            if (nextAndIndex != -1) {
                tokenParam = url.substring(tokenKeyIndex + 1, nextAndIndex + 1);
            } else {
                tokenParam = url.substring(tokenKeyIndex);
            }
        }
        String newUrl = url.replace(tokenParam, "");
        System.out.println(newUrl + "\n" + "  **************");
        return newUrl;

    }


}
