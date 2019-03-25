package com.aiitec.studydemo.fragmentdemo;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragments = new ArrayList<>();
    private List<String> mFragmentTitles = new ArrayList<>();
    private Context context;

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    public void addFragment(Fragment fragment, String title) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
    }

    public void addFragment(List<Fragment> fragments, List<String> fragmentTitles) {
        mFragments.clear();
        mFragments.addAll(fragments);
//        if (mFragments.size() > 1) {
////            添加最后一页到第一页
//            mFragments.add(0, mFragments.get(mFragments.size() - 1));
////            添加第一页(经过上行的添加已经是第二页了)到最后一页
//            mFragments.add(mFragments.get(1));
//        }

        mFragmentTitles.clear();
        mFragmentTitles.addAll(fragmentTitles);
//        if (mFragmentTitles.size() > 1) {
////            添加最后一页到第一页
//            mFragmentTitles.add(0, mFragmentTitles.get(mFragmentTitles.size() - 1));
////            添加第一页(经过上行的添加已经是第二页了)到最后一页
//            mFragmentTitles.add(mFragmentTitles.get(1));
//        }
    }

//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//
//        View view = View.inflate(context, R.layout.fragment_demo, null);
//        TextView tv_content = view.findViewById(R.id.tv_content);
//        tv_content.setText(mFragmentTitles.get(position % mFragmentTitles.size()));
//        container.addView(view);
//        return view;
////        return super.instantiateItem(container, position);
//    }
//
//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return view == object;
////        return super.isViewFromObject(view, object);
//    }
//
//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        container.removeView((View) object);
////        super.destroyItem(container, position, object);
//    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
//        return mFragments.get(position % mFragments.size());
    }

    @Override
    public int getCount() {
        return mFragments.size();
//        return Integer.MAX_VALUE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
//        return mFragmentTitles.get(position % mFragmentTitles.size());
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