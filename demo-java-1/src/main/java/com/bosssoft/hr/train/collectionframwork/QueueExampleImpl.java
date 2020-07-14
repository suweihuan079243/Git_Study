package com.bosssoft.hr.train.collectionframwork;

import java.util.LinkedList;
import java.util.Queue;


public class QueueExampleImpl<User> implements QueueExample<User> {
    /**
     * 使用linkedlist代替一下queue吧
     */
    private Queue<User> queue = new LinkedList<>();


    @Override
    public boolean add(User user) {
        return queue.add(user);
    }

    @Override
    public boolean offer(User user) {
        return queue.offer(user);
    }

    @Override
    public User remove() {
        return queue.remove();
    }

    @Override
    public User poll() {
        return queue.poll();
    }

    @Override
    public User element() {
        return queue.element();
    }

    @Override
    public User peek() {
        return queue.peek();
    }

    public Queue<User> getQueue() {
        return queue;
    }

    public void setQueue(Queue<User> queue) {
        this.queue = queue;
    }
}
