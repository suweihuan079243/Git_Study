package com.bosssoft.hr.train.thread;

/**
 * @author: Suweihuan
 * @date: 2020/7/12 12:06
 */
public class TestJob implements Runnable  {
    int index = 0;
    public TestJob(int _index) {
        this.index = _index;
    }
    @Override
    public void run() {
        System.out.println("job start " + index + " thread id=" + Thread.currentThread().getId());
    }
}
