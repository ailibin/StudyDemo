package com.aiitec.studydemo.widget.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import com.aiitec.studydemo.R;

/**
 * @Author: ailibin
 * @Time: 2019/02/21
 * @Description: 绘制圆环
 * @Email: ailibin@qq.com
 */
public class RoundView extends View implements Animation.AnimationListener {


    /**
     * 圆环转过的角度
     */
    private float mSweepAngle = 1;
    /**
     * 之前的角度
     */
    private float mOldAngle = 0;
    /**
     * 新的角度
     */
    private float mNewAngle = 0;
    /**
     * 圆环宽度,默认半径的1／5
     */
    private float mRingWidth = 0;
    /**
     * 圆环颜色,默认 #CBCBCB
     */
    private int mRingColor = 0;

    /**
     * 圆环半径,默认：Math.min(getHeight()/2,getWidth()/2)
     */
    private float mRadius = 0;
    /**
     * 底环画笔
     */
    private Paint mRingPaint;

    /**
     * 进度条圆环宽度
     */
    private float mProgressRingWidth = 0;
    /**
     * 进度条圆环开始颜色，进度条圆环是渐变的,默认
     */
    private int mProgressRingStartColor = 0;
    /**
     * 进度条圆环结束颜色，进度条圆环是渐变的,默认
     */
    private int mProgressRingEndColor = 0;
    /**
     * 进度条圆环Paint
     */
    private Paint mProgressRingPaint;
    /**
     * 进度条圆环渐变shader
     */
    private Shader mProgressRingShader;

    private int[] arcColors;

    /**
     * 进度动画
     */
    private BarAnimation barAnimation;
    /**
     * 抖动（缩放）动画
     */
    private ScaleAnimation scaleAnimation;
    /**
     * 是否正在改变
     */
    private boolean isAnimation = false;

    /**
     * 箭头画笔
     */
    private Paint mArrowPaint;
    /**
     * 箭头大小
     */
    private int arrowSize = 0;


    /**
     * 文字画笔
     */
    private Paint mTextPaint;
    /**
     * 文字颜色
     */
    private int mTextColor;
    /**
     * 文字大小
     */
    private float mTextSize;


    public RoundView(Context context) {
        this(context, null);
    }

    public RoundView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public RoundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundView);
        mRadius = typedArray.getDimension(R.styleable.RoundView_ring_radius, 0);
        mRingColor = typedArray.getColor(R.styleable.RoundView_ring_color, Color.parseColor("#CBCBCB"));
        mRingWidth = typedArray.getDimension(R.styleable.RoundView_ring_width, 0);
        mProgressRingWidth = typedArray.getDimension(R.styleable.RoundView_progress_ring_width, 0);
        mProgressRingStartColor = typedArray.getColor(R.styleable.RoundView_progress_ring_start_color, Color.parseColor("#f90aa9"));
        mProgressRingEndColor = typedArray.getColor(R.styleable.RoundView_progress_ring_end_color, Color.parseColor("#931c80"));
        mTextColor = typedArray.getColor(R.styleable.RoundView_text_color, Color.parseColor("#f90aa9"));
        mTextSize = typedArray.getDimension(R.styleable.RoundView_text_size, dp2px(context, 40));
        arrowSize = dp2px(context, 7);

        init();

        typedArray.recycle();
    }

    private void init() {

        mRingPaint = new Paint();
        //抗锯齿
        mRingPaint.setAntiAlias(true);
        //空心透明
        mRingPaint.setStyle(Paint.Style.STROKE);
        //背景
        mRingPaint.setColor(mRingColor);

        // 圆环紫色渐变色
        arcColors = new int[]{mProgressRingStartColor, mProgressRingEndColor};
        mProgressRingPaint = new Paint();
        // 抗锯齿效果
        mProgressRingPaint.setAntiAlias(true);
        mProgressRingPaint.setStyle(Paint.Style.STROKE);
        // 圆形笔头
        mProgressRingPaint.setStrokeCap(Paint.Cap.ROUND);
        mProgressRingPaint.setStrokeWidth(mProgressRingWidth);

        //箭头画笔
        mArrowPaint = new Paint();
        // 抗锯齿效果
        mArrowPaint.setAntiAlias(true);
        mArrowPaint.setStyle(Paint.Style.FILL);
        mArrowPaint.setColor(Color.parseColor("#000000"));
        mArrowPaint.setStrokeCap(Paint.Cap.ROUND);
        mArrowPaint.setStrokeWidth(5);

        //中间进度文本画笔设置
        mTextPaint = new Paint();
        // 抗锯齿效果
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        //设置圆心的中间
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        // 字体颜色
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);

        //动画
        barAnimation = new BarAnimation();
        barAnimation.setAnimationListener(this);
//        barAnimation.start();
//        startAnimation(barAnimation);

        scaleAnimation = new ScaleAnimation();
        scaleAnimation.setDuration(100);


    }

    /**
     * 设置新的角度
     */
    public void setAngle(final float newAngle, boolean isScale) {
        if (!isAnimation) {
            if (isScale) {
                scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        changeAngle(newAngle);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                startAnimation(scaleAnimation);
            } else {
                changeAngle(newAngle);
            }
        }
    }

    private void changeAngle(float newAngle) {
        mOldAngle = mNewAngle;
        mNewAngle = newAngle;
        int duration = (int) (Math.abs(mNewAngle - mOldAngle) * 15);
        barAnimation.setDuration(duration);
        barAnimation.setInterpolator(new DecelerateInterpolator());
        startAnimation(barAnimation);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float rawX = getWidth() / 2;
        float rawY = getHeight() / 2;

        // //如果未设置半径，则半径的值为view的宽、高一半的较小值
        mRadius = mRadius == 0 ? Math.min(getHeight() / 2, getWidth() / 2) : mRadius;
        //圆环的宽度默认为半径的1/5
        mRingWidth = mRingWidth == 0 ? mRadius / 5 : mRingWidth;
        //由于圆环本身有宽度，所以半径要减去圆环宽度的一半，不然一部分圆会在view外面
        mRadius = mRadius - mRingWidth / 2;
        //设置描边的宽度
        mRingPaint.setStrokeWidth(mRingWidth);
        //画底环
        canvas.drawCircle(rawX, rawY, mRadius, mRingPaint);

        //计算进度圆环宽度,默认和底部圆滑一样大,这里要重新设置一次描边的宽度
        float progressRingWidth = mProgressRingWidth == 0 ? mRingWidth : mProgressRingWidth;
        mProgressRingPaint.setStrokeWidth(progressRingWidth);
        // 设置渐变色,画进度圆环
        if (mProgressRingShader == null) {
            //设置渐变
            mProgressRingShader = new SweepGradient(rawX, rawY, arcColors, null);
            mProgressRingPaint.setShader(mProgressRingShader);
        }
        //一部分超出view边界,进度圆环没有在底部圆环里,我们想要的效果是从顶部开始的(-90度开始)
//        canvas.drawArc(new RectF(0, 0, getWidth(), getHeight()), 0, 180, false, mProgressRingPaint);
        //进度圆环应该在底部圆环的中间;渐变色的效果不对
//        canvas.drawArc(new RectF(progressRingWidth / 2, progressRingWidth / 2, getWidth() - progressRingWidth / 2, getHeight() - progressRingWidth / 2), -90, 180, false, mProgressRingPaint);
        float left = rawX - mRadius;
        float top = rawY - mRadius;
        float right = rawX + mRadius;
        float bottom = rawY + mRadius;
        //旋转画布
        canvas.save();
        //处理渐变的效果,我们使用的是SweepGradient，也是所说的雷达渐变，它也是从水平方向开始顺时针渐变，所以就出现了上面的情况
        // 旋转画布90度+笔头半径转过的角度
        double radian = radianToAngle(mRadius);
        // 90度+
        double degrees = Math.toDegrees(-2 * Math.PI / 360 * (90 + radian));
        //逆时针旋转90度
        canvas.rotate((float) degrees, rawX, rawY);
        canvas.drawArc(new RectF(left, top, right, bottom), (float) radian, 180, false, mProgressRingPaint);
        //角度要还原,不然影响到其它的界面
        canvas.restore();
        //----绘制圆环结束------//

        //将我们要画箭头的位置旋转到顶部，方便我们计算箭头的坐标，绘制完箭头在将画布恢复
        canvas.save();
        double hudu = 2 * Math.PI / 360 * (mOldAngle + mSweepAngle);
        double radians = Math.toDegrees(hudu);
        // 旋转画布
        canvas.rotate((float) radians, rawX, rawY);
        //绘制箭头
        canvas.drawLine(rawX - arrowSize, rawY - mRadius, rawX + arrowSize, rawY - mRadius, mArrowPaint);
        canvas.drawLine(rawX + arrowSize, rawY - mRadius, rawX, rawY - mRadius - arrowSize, mArrowPaint);
        canvas.drawLine(rawX + arrowSize, rawY - mRadius, rawX, rawY - mRadius + arrowSize, mArrowPaint);
        canvas.restore();
        //----绘制箭头结束------//

        //文本绘制
        int percentage = (int) ((mOldAngle + mSweepAngle) / 360 * 100);
        float sizeHeight = getFontHeight();
        canvas.drawText(percentage + "%", rawX, rawY + sizeHeight / 2, mTextPaint);

    }

    /**
     * 设置新的角度
     * newAngle:角度
     */
    public void setAngle(final float newAngle) {
        setAngle(newAngle, true);
    }

    public float getFontHeight() {
        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        return fm.descent - fm.ascent;
    }

    /**
     * 抖动（缩放动画）
     */
    public class ScaleAnimation extends Animation {
        private int centerX;
        private int centerY;

        public ScaleAnimation() {
        }

        @Override
        public void initialize(int width, int height, int parentWidth,
                               int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
            centerX = width / 2;
            centerY = height / 2;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);

            if (interpolatedTime < 0.25f) {
                // 1-1.1
                float ss = interpolatedTime * 0.4f + 1f;
                Matrix matrix = t.getMatrix();
                matrix.setScale(ss, ss, centerX, centerY);
            } else if (interpolatedTime >= 0.25f && interpolatedTime < 0.5f) {
                // 1.1-1
                float ss = (0.5f - interpolatedTime) * 0.4f + 1f;
                Matrix matrix = t.getMatrix();
                matrix.setScale(ss, ss, centerX, centerY);
            } else if (interpolatedTime >= 0.5f && interpolatedTime < 0.75f) {
                // 1-0.9
                float ss = (0.75f - interpolatedTime) * 0.4f + 0.9f;
                Matrix matrix = t.getMatrix();
                matrix.setScale(ss, ss, centerX, centerY);
            } else if (interpolatedTime >= 0.75f && interpolatedTime <= 1f) {
                // 0.9-1
                float ss = interpolatedTime * 0.4f + 0.6f;
                Matrix matrix = t.getMatrix();
                matrix.setScale(ss, ss, centerX, centerY);
            }
        }
    }

    /**
     * 进度条动画
     */
    public class BarAnimation extends Animation {


        public BarAnimation() {

        }

        /**
         * 然后调用postInvalidate()不停的绘制view。
         */
        @Override
        protected void applyTransformation(float interpolatedTime,
                                           Transformation t) {

            super.applyTransformation(interpolatedTime, t);
            if (mNewAngle - mOldAngle >= 0) {
                // 正向
                mSweepAngle = interpolatedTime * (mNewAngle - mOldAngle);
            } else {
                // 逆向
                mSweepAngle = interpolatedTime * (mNewAngle - mOldAngle);
            }
            postInvalidate();
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {
        isAnimation = true;
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        isAnimation = false;

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }


    /**
     * 已知圆半径和切线长求弧长公式
     */
    private double radianToAngle(float radios) {
        double aa = mProgressRingWidth / 2 / radios;
        double asin = Math.asin(aa);
        double radian = Math.toDegrees(asin);
        return radian;
    }

    private int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }


}
