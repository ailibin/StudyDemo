package com.aiitec.studydemo.widget.widgets;

import android.content.Context;
import android.graphics.*;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.aiitec.studydemo.R;

/**
 * @Author: ailibin
 * @Time: 2019/02/21
 * @Description: 绘制京东小哥动画
 * @Email: ailibin@qq.com
 */
public class JDLoadingView extends View {

    private Bitmap bitmap;

    private int index = 0;

    private Paint paint;

    private long lastTime = 0;

    public JDLoadingView(Context context) {
        this(context, null);
    }

    public JDLoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JDLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public JDLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.common_icon_loation);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //第2个和第3个一样的，作图的时候重复了，所以抛弃第二个
        if (index == 2) {
            index = 3;
        }
        //计算显示第几个小哥
        if (bitmap == null) {
            return;
        }
        int left = bitmap.getWidth() / 5 * index;
        int top = 0;
        int right = bitmap.getWidth() / 5 * (index + 1);
        int bottom = bitmap.getHeight();
        //显示一个京东小哥
        canvas.drawBitmap(bitmap, new Rect(left, top, right, bottom), new Rect(0, 0, getWidth(), getHeight()), paint);
        index++;
        //显示到最后一个，再次从头开始显示
        if (index >= 5) {
            index = 0;
        }
        //100ms以后刷新
        postInvalidateDelayed(100);
    }

}
