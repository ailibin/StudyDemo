package com.aiitec.studydemo.fragmentdemo;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ailibin
 * @状态statePagerAdapter 为了适应动态加载的fragment的需要, 主动释放fragment资源
 */
public class SimpleFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragments = new ArrayList<>();
    private List<String> mFragmentTitles = new ArrayList<>();
    private Context context;

    public SimpleFragmentStatePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    public void addFragment(Fragment fragment, String title) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }

    public void updateTitle(int index, String title) {
        mFragmentTitles.set(index, title);
        notifyDataSetChanged();
    }

    public void clear() {
        mFragments.clear();
        mFragmentTitles.clear();
        notifyDataSetChanged();
    }
}