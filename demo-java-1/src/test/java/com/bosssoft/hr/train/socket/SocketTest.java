package com.bosssoft.hr.train.socket;

import org.junit.Before;
import org.junit.Test;

/**
 * @author: Suweihuan
 * @date: 2020/7/12 18:20
 */
public class SocketTest {
    @Test
    public void testSocketServer(){
        SocketServer.start();
    }
    
    @Test
    public void testSocketClient(){
        SocketClient.start();
    }
    
}
