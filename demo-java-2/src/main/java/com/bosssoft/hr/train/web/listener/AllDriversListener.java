package com.bosssoft.hr.train.web.listener;

import com.bosssoft.hr.train.web.exception.BusinessException;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

class AllDriversListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Do nothing because don't use
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //这里如果Web应用拥有多个数据库的连接，可以一并关闭
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        Driver driver = null;
        while (drivers.hasMoreElements()) {
            try {
                driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            } catch (SQLException ex) {
                throw new BusinessException("10001", "驱动关闭异常", ex);
            }
        }
        AbandonedConnectionCleanupThread.checkedShutdown();
    }
}
