package com.aiitec.studydemo.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.aiitec.studydemo.R;
import com.aiitec.studydemo.test.activity.ResultActivity;

import java.util.ArrayList;

/**
 * @Author: aiibin
 * @Time: 2019/02/28
 * @Description: 测试算法demo
 * @Email: ailibin@qq.com
 */
public class TestArithmeticActivity extends AppCompatActivity {

    private ListView lv;
    private ArrayList<String> list = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);
        lv = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_expandable_list_item_1,
                getData());
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switchToActivity(position);
            }
        });
    }

    private void switchToActivity(int type) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }

    private ArrayList<String> getData() {
        list.add("测试字符串的反转");
        return list;
    }
}
