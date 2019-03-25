package com.aiitec.studydemo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.aiitec.studydemo.EventDispatch.EventDispatchActivity
import com.aiitec.studydemo.MD.MeterialDesignActivity
import com.aiitec.studydemo.fragmentdemo.FragmentDemoActivity
import com.aiitec.studydemo.handler.HandlerClass
import com.aiitec.studydemo.handler.HandlerThreadActivity
import com.aiitec.studydemo.intent_service.IntentServiceActivity
import com.aiitec.studydemo.leak.LeakTestActivity
import com.aiitec.studydemo.market.MarketActivity
import com.aiitec.studydemo.servicedemo.ServiceTestActivity
import com.aiitec.studydemo.test.TestArithmeticActivity
import com.aiitec.studydemo.test.activity.TestDecimalActivity
import com.aiitec.studydemo.widget.WidgetActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setViewClickListener()


    }

    private fun setViewClickListener() {

        btn_handler.setOnClickListener(this)
        btn_handler_thread.setOnClickListener(this)
        btn_intent_service.setOnClickListener(this)
        btn_intent_leak_test.setOnClickListener(this)
        btn_intent_service_demo.setOnClickListener(this)
        btn_intent_fragment.setOnClickListener(this)
        btn_widget_circle.setOnClickListener(this)
        btn_meterial_design.setOnClickListener(this)
        btn_event_dispatch.setOnClickListener(this)
        btn_logic_test.setOnClickListener(this)
        btn_decimal_test.setOnClickListener(this)
        btn_market_download.setOnClickListener(this)

    }

    override fun onClick(view: View?) {

        when (view?.id) {
            R.id.btn_handler -> {
                //handler跳转
                startActivity(Intent(this, HandlerClass::class.java))
            }
            R.id.btn_handler_thread -> {
                //handler定时发送消息
                startActivity(Intent(this, HandlerThreadActivity::class.java))
            }
            R.id.btn_intent_service -> {
                //handler定时发送消息
                startActivity(Intent(this, IntentServiceActivity::class.java))
            }
            R.id.btn_intent_leak_test -> {
                //内存泄露测试
                startActivity(Intent(this, LeakTestActivity::class.java))
            }
            R.id.btn_intent_service_demo -> {
                //服务测试
                startActivity(Intent(this, ServiceTestActivity::class.java))
            }
            R.id.btn_intent_fragment -> {
                //服务测试
                startActivity(Intent(this, FragmentDemoActivity::class.java))
            }
            R.id.btn_widget_circle -> {
                //服务测试
                startActivity(Intent(this, WidgetActivity::class.java))
            }
            R.id.btn_meterial_design -> {
                //android5.0新特性
                startActivity(Intent(this, MeterialDesignActivity::class.java))
            }
            R.id.btn_event_dispatch -> {
                //事件分发
                startActivity(Intent(this, EventDispatchActivity::class.java))
            }
            R.id.btn_logic_test -> {
                startActivity(Intent(this, TestArithmeticActivity::class.java))
            }
            R.id.btn_decimal_test -> {
                //数字和字符串转换测试
                startActivity(Intent(this, TestDecimalActivity::class.java))
            }
            R.id.btn_market_download -> {
                //数字和字符串转换测试
                startActivity(Intent(this, MarketActivity::class.java))
            }

        }

    }

}
