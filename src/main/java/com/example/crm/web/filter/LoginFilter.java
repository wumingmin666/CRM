package com.example.crm.web.filter;

import com.example.crm.setting.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getServletPath();

        if("/login.jsp".equals(path)||"/setting/user/login.do".equals(path)){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            User user = (User)request.getSession().getAttribute("user");
            if(user!=null){
                filterChain.doFilter(servletRequest, servletResponse);
            }else {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
