package com.aiitec.studydemo.market;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import com.aiitec.studydemo.R;

/**
 * @Author: ailibin
 * @Time: 2019/02/28
 * @Description: 时常链接详情
 * @Email: ailibin@qq.com
 */
public class MarketDetailActivity extends AppCompatActivity {

    private EditText et_param;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        url = getIntent().getStringExtra("url");
        et_param = findViewById(R.id.et_param);
        Button btn_test = findViewById(R.id.btn_test);

    }

}
