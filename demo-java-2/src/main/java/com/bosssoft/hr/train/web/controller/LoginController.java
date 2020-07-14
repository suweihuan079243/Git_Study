package com.bosssoft.hr.train.web.controller;

import com.bosssoft.hr.train.web.pojo.User;
import com.bosssoft.hr.train.web.service.UserService;
import com.bosssoft.hr.train.web.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String loginAttribute = "loginUsers";
        boolean flag = authentication(username, password);
        if (flag) {
            //账号密码正确
            //{"":"","":""}这是json格式的字符串，在每个双引号之前要加转义字符"\"
            resp.getWriter().write("{\"message\":\"1\"}");

            //没有就新建 有就添加
            String loginUsers = (String) req.getSession().getServletContext().getAttribute(loginAttribute);
            if (loginUsers == null || loginUsers.trim().length() == 0) {
                req.getSession().getServletContext().setAttribute(loginAttribute, username);
            } else {
                req.getSession().getServletContext().setAttribute(loginAttribute, loginUsers + "," + username);
            }
        } else {
            resp.getWriter().write("{\"message\":\"0\"}");
        }
    }

    /**
     * public 后面改为 private
     *
     * @param code
     * @param password
     * @return
     */
    private boolean authentication(String code, String password) {
        UserService userService = new UserServiceImpl();
        User user = new User(code, password);
        return userService.authentication(user);
    }
}
