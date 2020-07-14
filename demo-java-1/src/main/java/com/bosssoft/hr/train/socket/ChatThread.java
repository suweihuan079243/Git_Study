package com.bosssoft.hr.train.socket;

import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author: Suweihuan
 * @date: 2020/7/12 12:19
 */
public class ChatThread extends Thread {
    private Selector selector;
    private SocketChannel socket;

    public ChatThread(Selector selector, SocketChannel socket) {
        super();
        this.selector = selector;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //等待连接建立
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String s = "hello server";
        try {
            socket.register(selector, SelectionKey.OP_WRITE, ByteBuffer.wrap(s.getBytes()));
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }
        //唤醒之前因为监听OP_READ而阻塞的select()
        selector.wakeup();
   
}
}
