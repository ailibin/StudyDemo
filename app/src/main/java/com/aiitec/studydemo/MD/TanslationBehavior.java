package com.aiitec.studydemo.MD;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Author: ailibin
 * @Time: 2019/02/21
 * @Description: 5.0新特性demo, 自定义behavior
 * @Email: ailibin@qq.com
 */
public class TanslationBehavior extends FloatingActionButton.Behavior {

    /**
     * 关注垂直滚动 ，而且向上的时候是出来，向下是隐藏
     *
     * @param context
     * @param attrs
     */
    public TanslationBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
//        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    /**
     * 添加一个标志位
     */
    private boolean isOut = false;

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);

        ////大于0代表向上滑动，隐藏
        if (dyConsumed > 0) {
            if (!isOut) {
                // 往上滑动，是隐藏 , 加一个标志位 已经往下走了
                //获取控件距离底部的距离+控件的高度
                int translationY = ((CoordinatorLayout.LayoutParams) child.getLayoutParams()).bottomMargin
                        + child.getMeasuredHeight();
                child.animate().translationY(translationY).setDuration(500).start();
                isOut = true;
            }
        } else {
            //向下滑动
            if (isOut) {
                child.animate().translationY(0).setDuration(500).start();
                isOut = false;
            }
        }

    }
}
