package com.aiitec.studydemo.intent_service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import java.text.DateFormat;
import java.util.Date;

/**
 * @Author: ailibin
 * @Time: 2019/02/19
 * @Description: intentService
 * @Email: ailibin@qq.com
 */
public class IntentServiceDemo extends IntentService {

    public IntentServiceDemo(String name) {
        super("IntentServiceDemo");
    }

    public IntentServiceDemo() {
        super("IntentServiceDemo");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        DateFormat format = DateFormat.getTimeInstance();
        Log.e("ailibin", "onHandleIntent开始:" + format.format(new Date()));

        // 这里Thread.sleep(5000)模拟执行一个耗时5秒的任务
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }

        Log.e("ailibin", "onHandleIntent完成:" + format.format(new Date()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("ailibin", "onDestroy");
    }
}
