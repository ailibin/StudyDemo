package com.aiitec.studydemo.servicedemo

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.widget.ProgressBar
import android.widget.TextView
import com.aiitec.studydemo.R
import kotlinx.android.synthetic.main.activity_service_demo.*

/**
 * @Author: ailibin
 * @Time: 2019/02/20
 * @Description: 服务demo
 * @Email: ailibin@qq.com
 */
class ServiceTestActivity : AppCompatActivity() {

    private var msgService: MsgService? = null
    private var mProgressBar: ProgressBar? = null
    private var tv_progress: TextView? = null
    private var progress1 = 0
    private var CODE = 0x110


    val handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if (msg?.what == CODE) {
                val progress = msg.arg1
                tv_progress?.text = progress.toString()
            }
        }
    }

    val conn = object : ServiceConnection {
        override fun onServiceDisconnected(p0: ComponentName?) {
        }

        override fun onServiceConnected(p0: ComponentName?, sevice: IBinder?) {
            //连接上了服务,返回一个服务对象
            msgService = (sevice as (MsgService.MsgBinder)).service

            //注册回调接口来接收下载进度的变化
            msgService?.setOnProgressListener { progress ->
                mProgressBar?.progress = progress
                val message = handler.obtainMessage()
                message.what = CODE
                message.arg1 = progress
                handler.sendMessage(message)
            }

        }
    }

    private var intent1: Intent? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_demo)

        //绑定service
//        val intent = Intent("com.example.communication.MSG_ACTION")

        intent1 = Intent(this, MsgService::class.java)
//        startService(intent1)
        //android5.0要显性声明,不然会报Service Intent must be explicit: Intent
        bindService(intent1, conn, Context.BIND_AUTO_CREATE)

        //测试开始
        mProgressBar = findViewById(R.id.progressBar)
        tv_progress = findViewById(R.id.tv_progress)
        button1.setOnClickListener {
            //开始下载
            msgService?.startDownLoad()
        }

        button2.setOnClickListener {
            //暂停服务
            stopService(intent1)
            unbindService(conn)
        }

        button3.setOnClickListener {
            //开始服务
            startService(intent1)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(conn)
    }
}