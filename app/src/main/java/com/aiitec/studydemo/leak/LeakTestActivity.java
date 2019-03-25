package com.aiitec.studydemo.leak;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.aiitec.studydemo.R;

/**
 * @Author: ailibin
 * @Time: 2019/02/20
 * @Description: 内存泄露和内存溢出的测试
 * @Email: ailibin@qq.com
 */
public class LeakTestActivity extends AppCompatActivity {

    /**
     * 静态变量引用引起的内存泄露
     */
    private static Drawable sBackground;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_leak_test);
        TextView label = new TextView(this);
        if (sBackground == null) {
            sBackground = getDrawable(R.drawable.ic_launcher_background);
        }

        label.setBackgroundDrawable(sBackground);
        setContentView(label);

//        new MyThread().start();
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MyThread().start();
            }
        });

    }


    /**
     * 使用静态内部线程类避免对外部类的引用，导致内存泄露
     */
    private static class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            //do somthing while(true)
            Log.e("ailibin", "线程...");
        }
    }
}
