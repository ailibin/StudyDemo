package com.aiitec.studydemo.fragmentdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.aiitec.studydemo.R;

/**
 * @Author: ailibin
 * @Time: 2018/12/28
 * @Description: 模板注释
 * @Email: ailibin@qq.com
 */
public class DemoFragment extends Fragment {


    private int type;

    public static DemoFragment newInstance(int type) {
        DemoFragment fragment = new DemoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("ailibin", type + ":" + "DemoFragment--onAttach");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        type = getArguments().getInt("type");
        View view = inflater.inflate(R.layout.fragment_demo, container, false);
        TextView textView = view.findViewById(R.id.tv_content);
        textView.setText("内容" + type);
        Log.e("ailibin", type + ":" + "DemoFragment--onCreateView");
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e("ailibin", type + ":" + "DemoFragment--onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        Log.e("ailibin", type + ":" + "DemoFragment--onResume");
        super.onResume();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        Log.e("ailibin", type + ":" + "DemoFragment--onViewStateRestored");
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.e("ailibin", type + ":" + "DemoFragment--setUserVisibleHint: " + isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("ailibin", type + ":" + "DemoFragment--onDetach");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("ailibin", type + ":" + "DemoFragment--onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("ailibin", type + ":" + "DemoFragment--onDestroy");
    }
}
