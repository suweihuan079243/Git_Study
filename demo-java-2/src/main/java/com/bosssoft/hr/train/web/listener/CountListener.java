package com.bosssoft.hr.train.web.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class CountListener implements HttpSessionListener {
    /**
     * 并发问题
     * java 一切皆对象
     * java 一切皆线程
     */
    private  int num = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().getServletContext().setAttribute("count", ++num);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        se.getSession().getServletContext().setAttribute("count", --num);
    }
}
