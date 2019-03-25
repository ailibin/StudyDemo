package com.aiitec.studydemo.skeleton;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListPopupWindow;
import com.aiitec.studydemo.R;

/**
 * @Author: ailibin
 * @Time: 2019/02/26
 * @Description: 事件分发demo 事件传递 Activity->ViewGroup->View  事件消费从下往上 View->ViewGroup->Activity
 * @Email: ailibin@qq.com
 */
public class SkeletonActivity extends AppCompatActivity {

    private ListPopupWindow listPopupWindow;
    private String[] products = {"super", "false", "true"};
    private static final String TAG = "ailibin";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event_dispatch);

    }

}
