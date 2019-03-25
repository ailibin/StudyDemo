package com.aiitec.studydemo.fragmentdemo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.aiitec.studydemo.R
import com.aiitec.studydemo.fragmentdemo.xtablayout.XTabLayout
import kotlinx.android.synthetic.main.activity_fragment_demo.*
import java.util.*

class FragmentDemoActivity : AppCompatActivity() {

    private var mPagerAdapter: SimpleFragmentPagerAdapter? = null
    private var mFragments = ArrayList<Fragment>()
    private var mFragmentTitles = ArrayList<String>()
    //    private var mPagerAdapter: SimpleFragmentStatePagerAdapter? = null
    private val FIRST_PAGE = 1
    private var currentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fragment_demo)

        mPagerAdapter = SimpleFragmentPagerAdapter(supportFragmentManager, this)
//        mPagerAdapter = SimpleFragmentStatePagerAdapter(supportFragmentManager, this)

//        mPagerAdapter?.addFragment(DemoFragment.newInstance(1), "Fragment1")
//        mPagerAdapter?.addFragment(DemoFragment.newInstance(2), "Fragment2")
//        mPagerAdapter?.addFragment(DemoFragment.newInstance(3), "Fragment3")
//        mPagerAdapter?.addFragment(DemoFragment.newInstance(4), "Fragment4")

        mFragments.add(DemoFragment.newInstance(1))
        mFragments.add(DemoFragment.newInstance(2))
        mFragments.add(DemoFragment.newInstance(3))
        mFragments.add(DemoFragment.newInstance(4))

        mFragmentTitles.add("Fragment1")
        mFragmentTitles.add("Fragment2")
        mFragmentTitles.add("Fragment3")
        mFragmentTitles.add("Fragment4")

        mPagerAdapter?.addFragment(mFragments, mFragmentTitles)


//        viewpager.currentItem = 100 * mFragments.size
//        viewpager.setCurrentItem(1, false)
        viewpager.adapter = mPagerAdapter
        viewpager.offscreenPageLimit = 1
        tablayout.setupWithViewPager(viewpager)
        tablayout.tabMode = XTabLayout.MODE_FIXED
        tablayout.addOnTabSelectedListener(object : XTabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: XTabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: XTabLayout.Tab?) {
            }

            override fun onTabSelected(tab: XTabLayout.Tab?) {
            }
        })

        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

//                //        若viewpager滑动未停止，直接返回
//                if (state != ViewPager.SCROLL_STATE_IDLE) return
////        若当前为第一张，设置页面为倒数第二张
//                if (currentPosition == 0) {
//                    viewpager.setCurrentItem(mFragments.size - 2, false)
//                } else if (currentPosition == mFragments.size - 1) {
////        若当前为倒数第一张，设置页面为第二张
//                    viewpager.setCurrentItem(1, false)
//                }
//                Log.e("ailibin", "onPageScrollStateChanged")
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                currentPosition = position
//                if (position == mFragments.size - 1) {
//                    // 设置当前值为1
//                    currentPosition = FIRST_PAGE
//                } else if (position == 0) {
//                    // 如果索引值为0了,就设置索引值为倒数第二个
//                    currentPosition = mFragments.size - 2
//                } else {
//                    currentPosition = position
//                }
//                viewpager.currentItem = currentPosition
                Log.e("ailibin", "onPageSelected")
            }

        })

    }
}
