package com.bosssoft.hr.train.thread;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPoolTest {
    
    @Test
    public void test(){
        ThreadPool.getInstance().init(10, new LinkedBlockingQueue<Runnable>());

        for (int i=0; i<1000; ++i) {
            TestJob job = new TestJob(i+1);
            ThreadPool.getInstance().execute(job);
        }
    }
    

}