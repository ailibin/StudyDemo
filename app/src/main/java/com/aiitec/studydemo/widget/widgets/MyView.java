package com.aiitec.studydemo.widget.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import com.aiitec.studydemo.R;
import com.aiitec.studydemo.util.ScreenUtils;

/**
 * @Author: ailibin
 * @Time: 2019/02/21
 * @Description: 圆环自定义过程
 * @Email: ailibin@qq.com
 */
public class MyView extends View {

    private float radius;
    private int color;
    /**
     * 是否填充，还是透明
     */
    private boolean isFill;
    private float centerTextSize;
    private int centerTextColor;
    private int orientation;
    private int shape;

    /**
     * 文字居中
     */
    public static final int MODE_CENTER = 1;
    /**
     * 控件形状,默认矩形
     */
    public static final int SHAPE_RECT = 1;

    private Paint textPaint;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        color = typedArray.getColor(R.styleable.MyView_color, Color.RED);
        radius = typedArray.getDimension(R.styleable.MyView_radius, 0);
        isFill = typedArray.getBoolean(R.styleable.MyView_circleFill, false);
        centerTextSize = typedArray.getDimension(R.styleable.MyView_centerTextSize, 13f);
        centerTextColor = typedArray.getColor(R.styleable.MyView_centerTextColor, Color.GRAY);
        orientation = typedArray.getInt(R.styleable.MyView_centerTextAlign, MODE_CENTER);
        shape = typedArray.getInt(R.styleable.MyView_shape, SHAPE_RECT);

        //记得回收资源
        typedArray.recycle();
        textPaint = new Paint();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //自定义文字内容
        textPaint.setTextSize(centerTextSize);
        textPaint.setColor(centerTextColor);
        textPaint.setAntiAlias(true);
        //空心,没有填充
        if (isFill) {
            textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        } else {
            textPaint.setStyle(Paint.Style.STROKE);
        }
        String text = "";
        switch (orientation) {
            case 0:
                //左边，圆心在文字的左边
                textPaint.setTextAlign(Paint.Align.LEFT);
                text = " LEFT";
                break;
            case 1:
                //中间，圆心在文字的中间
                textPaint.setTextAlign(Paint.Align.CENTER);
                text = " CENTER";
                break;
            case 2:
                //右边，圆心在文字的右边
                textPaint.setTextAlign(Paint.Align.RIGHT);
                text = " RIGHT";
                break;
            default:
                break;
        }

        int dp100 = ScreenUtils.dip2px(getContext(), 100);
        int dp200 = ScreenUtils.dip2px(getContext(), 200);
        int dp20 = ScreenUtils.dip2px(getContext(), 20);
        switch (shape) {
            case 0:
                //圆形
                float mRadius = radius == 0 ? Math.min(getWidth() / 2, getHeight() / 2) : radius;
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, mRadius, textPaint);
                break;
            case 1:
                //矩形,left控件最左边到坐标系的坐标值，依次类推，可以看到此控件的大小值为100
                canvas.drawRect(100, 100, 200, 200, textPaint);
                break;
            case 2:
                //圆角矩形
                canvas.drawRoundRect(100, 100, 200, 200, 20, 20, textPaint);
                break;
            case 3:
                //点
                canvas.drawPoint(100, 100, textPaint);
                canvas.drawPoints(new float[]{100, 200, 130, 200, 150, 200, 170, 200, 190, 200}, textPaint);
                canvas.drawPoints(new float[]{100, 300, 130, 300, 150, 300, 170, 300, 190, 300}, 2, 4, textPaint);
                break;
            case 4:
                //线
                canvas.drawLine(100, 100, 200, 100, textPaint);
                canvas.drawLines(new float[]{100, 200, 200, 200, 300, 200, 400, 200}, textPaint);
                canvas.drawLines(new float[]{100, 200, 200, 200, 300, 300, 400, 300}, 4, 4, textPaint);
                break;
            case 5:
                //弧形
                canvas.drawArc(100, 100, 300, 300, 0, 270, true, textPaint);
                break;
            case 6:
                //文字路径
                Path path = new Path();
                path.moveTo(100, 200);
                path.lineTo(200, 100);
                path.lineTo(300, 200);
                canvas.drawPath(path, textPaint);
                canvas.drawTextOnPath("ABCDEFGHIGKLMN", path, 0, 0, textPaint);
                break;
            case 7:
                //画椭圆
                canvas.drawOval(new RectF(100, 100, 400, 200), textPaint);
                break;
            default:
                break;
        }

        //看属性里面是否设置了半径,如果没有设置就是宽和高之中的一半的较小者
//        canvas.drawText(text, getWidth() / 2, getHeight() / 2, textPaint);
    }
}
