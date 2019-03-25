package com.aiitec.studydemo.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * @Author: ailibin
 * @Time: 2019/02/21
 * @Description: 工具类
 * @Email: ailibin@qq.com
 */
public class ScreenUtils {

    public static DisplayMetrics metric = new DisplayMetrics();

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param context
     * @param dpValue dp值
     * @return 像素值
     */
    public static int dip2px(Context context, float dpValue) {

        final float scale = metric.density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param context
     * @param pxValue 像素值
     * @return dp值
     */
    public static int px2dip(Context context, float pxValue) {

        final float scale = metric.density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 在view还没有创建出来时测量其宽度和高度（0为宽度，1为高度）
     *
     * @param measureView
     * @return int数组
     */
    public static int[] getViewWidthAndHeight(View measureView) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        measureView.measure(w, h);
        int height = measureView.getMeasuredHeight();
        int width = measureView.getMeasuredWidth();
        int[] wAndH = {width, height};
        return wAndH;
    }



}
