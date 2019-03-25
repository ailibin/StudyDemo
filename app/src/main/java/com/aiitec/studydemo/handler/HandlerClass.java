package com.aiitec.studydemo.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.aiitec.studydemo.R;

/**
 * @Author: ailibin
 * @Time: 2019/2/18
 * @Description: handler处理, 在子线程new一个handler的做法
 * @Email: ailibin@qq.com
 */
public class HandlerClass extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_handler);
        initHandler();

    }

    private void initHandler() {

        final HandlerThread handlerThread = new HandlerThread("子线程");
        handlerThread.start();
        final Handler handler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.d("ailibin", "线程:" + Thread.currentThread().getName());
            }
        };

        //子线程发送消息
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessage(100);
            }
        });

        //子线程停止发送消息
        findViewById(R.id.bt_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerThread.quit();
            }
        });

    }
}
