package com.aiitec.studydemo.intent_service;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.aiitec.studydemo.R;

/**
 * @Author: ailibin
 * @Time: 2019/02/19
 * @Description: 模板注释
 * @Email: ailibin@qq.com
 */
public class IntentServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_intent_service);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //启动service
                Toast.makeText(IntentServiceActivity.this, "启动service", Toast.LENGTH_SHORT).show();
                startService(new Intent(IntentServiceActivity.this, IntentServiceDemo.class));
            }
        });
    }
}
