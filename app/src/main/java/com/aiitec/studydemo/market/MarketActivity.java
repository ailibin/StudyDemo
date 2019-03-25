package com.aiitec.studydemo.market;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.aiitec.studydemo.R;
import com.aiitec.studydemo.util.AppUtils;

import java.util.ArrayList;

/**
 * @Author: aiibin
 * @Time: 2019/02/28
 * @Description: 测试算法demo
 * @Email: ailibin@qq.com
 */
public class MarketActivity extends AppCompatActivity {

    private ListView lv;
    private ArrayList<String> list = new ArrayList<String>();
    private ArrayList<String> urls = new ArrayList<String>();
    private String[] urlArrayls = new String[]{
            "com.android.vending",
            "com.tencent.android.qqdownloader",
            "com.qihoo.appstore",
            "com.baidu.appsearch",
            "com.xiaomi.market",
            "com.wandoujia.phoenix2",
            "com.huawei.appmarket",
            "com.taobao.appcenter",
            "com.hiapk.marketpho",
            "cn.goapk.market"
    };

    public static final String TAG = "ailibin";

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
        initData();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String packageName = AppUtils.getPackageName(MarketActivity.this);
                launchAppDetail(packageName, urls.get(position));
                Log.e(TAG, "packageName: " + packageName);

            }
        });
    }

    private void initData() {
        for (String url : urlArrayls) {
            urls.add(url);
        }
    }

    private void switchToActivity(String url) {
        Intent intent = new Intent(this, MarketDetailActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    /**
     * 启动到应用商店app详情界面
     *
     * @param appPkg    目标App的包名
     * @param marketPkg 应用商店包名 ,如果为""则由系统弹出应用商店列表供用户选择,否则调转到目标市场的应用详情界面，某些应用商店可能会失败
     */
    public void launchAppDetail(String appPkg, String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg)) {
                return;
            }

            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg)) {
                intent.setPackage(marketPkg);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> getData() {
        list.add("跳转到Google Play");
        list.add("跳转到应用宝");
        list.add("跳转到360手机助手");
        list.add("跳转到百度手机助");
        list.add("跳转到豌豆荚");
        list.add("跳转到华为应用市场");
        list.add("跳转到淘宝手机助手");
        list.add("跳转到安卓市场");
        list.add("跳转到安智市场");
        return list;
    }
}
