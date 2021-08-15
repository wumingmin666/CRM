package com.example.crm.setting.web.controller;

import com.example.crm.setting.domain.User;
import com.example.crm.setting.service.UserService;
import com.example.crm.setting.service.impl.UserServiceImpl;
import com.example.crm.util.MD5Util;
import com.example.crm.util.PrintJson;
import com.example.crm.util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginAct = request.getParameter("loginAct");
        String loginPwd = MD5Util.getMD5(request.getParameter("loginPwd"));
        String ip = request.getRemoteUser();

        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());

        try{
            User user = us.login(loginAct,loginPwd,ip);
            request.getSession().setAttribute("user",user);
            PrintJson.printJsonFlag(response,true);
        }catch (Exception e){
            e.printStackTrace();
            String msg = e.getMessage();
            Map map = new HashMap();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);
        }



    }
}