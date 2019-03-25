package com.aiitec.studydemo.widget.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.aiitec.studydemo.R;

/**
 * @Author: ailibin
 * @Time: 2019/02/21
 * @Description: 全局配置
 * @Email: ailibin@qq.com
 */
public class GlobalActivity extends AppCompatActivity {

//    drawPoint(float x, float y, Paint paint)：在（x,y）处绘制点 x,y：x,y坐标
//    drawPoints(float[] pts, Paint paint)：绘制一系列点 pts：多个（x，y）集合
//    drawPoints(float[] pts, int offset, int count, Paint paint)
//    pts：多个（x，y）集合
//    offset：pts从offset处开始绘制
//    count：从offset处开始取count个点绘制

//    drawLine(float startX, float startY, float stopX, float stopY, Paint paint)
//    startX,startY：开始点的（x，y）坐标
//    stopX, stopY：结束点的（x，y）坐标
//
//    drawLines(float[] pts, Paint paint)4个点确定一条线
//    drawLines(float[] pts, int offset, int count, Paint paint)
//    同Points截取一段点作为线点坐标，4个一组

    //文字
//    drawText(String text, float x, float y, Paint paint)
//    在（x，y）坐标处绘制文字，注意 mPaint.setTextAlign(Paint.Align.CENTER); 设置不同的align效果不同。还有其他几个重载方法，是对text的截取。
//
//    drawTextOnPath(String text, Path path, float hOffset, float vOffset, Paint paint)：在路径上绘制文字
//    hOffset：水平偏移
//    vOffset：垂直偏移

    //画弧
//    drawArc(RectF oval, float startAngle, float sweepAngle, boolean useCenter, Paint paint)：在矩形区域内绘制弧
//    oval：矩形区域
//    startAngle：开始角度
//    sweepAngle：旋转角度
//    useCenter：是否和中心连线

    //画椭圆
//    drawOval(RectF oval, Paint paint)：在矩形区域内画椭圆


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global);
        int type = getIntent().getIntExtra("type", 0);
        initView(type);
    }

    private void initView(int type) {
        switch (type) {
            case 0:
                //画圆形
                findViewById(R.id.rv_circle).setVisibility(View.VISIBLE);
                break;
            case 1:
                //画点
                findViewById(R.id.rv_dot).setVisibility(View.VISIBLE);
                break;
            case 2:
                //画线
                findViewById(R.id.rv_line).setVisibility(View.VISIBLE);
                break;
            case 3:
                //画弧
                findViewById(R.id.rv_draw_arc).setVisibility(View.VISIBLE);
                break;
            case 4:
                //画矩形
                findViewById(R.id.rv_rect).setVisibility(View.VISIBLE);
                break;
            case 5:
                //画圆角矩形
                findViewById(R.id.rv_roundRect).setVisibility(View.VISIBLE);
                break;
            case 6:
                //画文字路径
                findViewById(R.id.rv_draw_path).setVisibility(View.VISIBLE);
                break;
            case 7:
                //画椭圆
                findViewById(R.id.rv_draw_oval).setVisibility(View.VISIBLE);
                break;
            case 8:
                //京东快递图
                findViewById(R.id.jd_loading).setVisibility(View.VISIBLE);
                break;
            case 9:
                //绘制圆环
                findViewById(R.id.rv_round_view).setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }
}
