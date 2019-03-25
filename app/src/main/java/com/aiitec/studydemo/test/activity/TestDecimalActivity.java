package com.aiitec.studydemo.test.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.aiitec.studydemo.R;

import java.text.DecimalFormat;

/**
 * @Author: aiibin
 * @Time: 2019/02/28
 * @Description: 测试算法demo
 * @Email: ailibin@qq.com
 */
public class TestDecimalActivity extends AppCompatActivity {


    public static final String TAG = "ailibin";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_decimal);

        findViewById(R.id.tv_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                initData();

            }
        });
    }

    private void initData() {

        double number = 12;
        //12.0
        System.out.println(number);
        Log.e(TAG, "number:" + number);
        //12.0
        System.out.println(Double.toString(number));
        Log.e(TAG, "Double.toString:" + Double.toString(number));
        DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
        //12
        System.out.println(decimalFormat.format(number));
        Log.e(TAG, "decimalFormat.format:" + decimalFormat.format(number));

    }

}
