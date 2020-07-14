package com.bosssoft.hr.train.web.tag;


import com.bosssoft.hr.train.web.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;


public class UserTag extends TagSupport {

    transient PageContext userPageContext;

    @Override
    public int doStartTag() throws JspException {
        //获取页面Servlet中 request 和out 对象
        HttpServletRequest request = (HttpServletRequest) userPageContext.getRequest();
        JspWriter out = userPageContext.getOut();
        //获取ip地址
        String loginUsers = (String) request.getSession().getServletContext().getAttribute("loginUsers");
        try {
            out.write(loginUsers);
        } catch (IOException e) {
            throw new BusinessException("400", "UserTag出错", e);
        }
        return 0;
    }

    @Override
    public void setPageContext(PageContext pageContext) {
        userPageContext = pageContext;
    }
}
