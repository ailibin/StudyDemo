package com.aiitec.studydemo.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: ailibin
 * @Time: 2019/2/18
 * @Description: 本地线程管理
 * @Email: ailibin@qq.com
 */
public class ThreadLocalThread extends Thread {

    private static AtomicInteger ai = new AtomicInteger();

    public ThreadLocalThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 3; i++) {
                Tools.t1.set(ai.addAndGet(1) + "");
                System.out.println(this.getName() + " get value--->" + Tools.t1.get());
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
