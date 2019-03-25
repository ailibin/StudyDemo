package com.aiitec.studydemo.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.aiitec.studydemo.R;
import com.aiitec.studydemo.widget.activity.GlobalActivity;

import java.util.ArrayList;

/**
 * @Author: ailibin
 * @Time: 2019/02/20
 * @Description: 自定义控件--圆环绘制
 * @Email: ailibin@qq.com
 */
public class WidgetActivity extends AppCompatActivity {

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
        Intent intent = new Intent(this, GlobalActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }

    private ArrayList<String> getData() {
        list.add("画圆");
        list.add("画点");
        list.add("画线");
        list.add("画弧");
        list.add("画矩形");
        list.add("画圆角矩形");
        list.add("画文字路径");
        list.add("画椭圆");
        list.add("京东快递动态图");
        list.add("绘制圆环");
        return list;
    }

}
