package com.bosssoft.hr.train.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Suweihuan
 * @date: 2020/7/12 11:46
 */
public class ThreadPool {
    private static ThreadPool instance = new ThreadPool();
    private BlockingQueue<Runnable> jobQueue;
    private BlockingQueue<Worker> workers;
    private int workerNum;
    private ReentrantLock lock = new ReentrantLock();

    final class Worker implements Runnable{
        Runnable job = null;
        public Worker() {
        }


        @Override
        public void run() {
            try {
                while ((job = jobQueue.take()) != null) {
                    job.run();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static ThreadPool getInstance() {
        return instance;
    }

    private ThreadPool() {
        workers = new LinkedBlockingQueue<>();
    }

    public void init(int workerNum, BlockingQueue<Runnable> _jobQueue) {
        this.workerNum = workerNum;
        this.jobQueue = _jobQueue;
    }

    public void execute(Runnable job) {
        lock.lock();
        try {
            if (workers.size() < workerNum) {
                Worker worker = new Worker();
                Thread t = new Thread(worker);
                t.start();
                workers.add(worker);
            }


            jobQueue.add(job);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
}

