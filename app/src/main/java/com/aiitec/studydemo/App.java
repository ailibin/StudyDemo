package com.aiitec.studydemo;

import android.app.Application;
import com.squareup.leakcanary.LeakCanary;

/**
 * @Author: ailibin
 * @Time: 2019/02/20
 * @Description: 内存泄露分析器
 * @Email: ailibin@qq.com
 */
public class App extends Application {

    private String s;
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
