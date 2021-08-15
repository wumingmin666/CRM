package com.example.crm.setting.service.impl;

import com.example.crm.exception.LoginException;
import com.example.crm.setting.dao.UserDao;
import com.example.crm.setting.domain.User;
import com.example.crm.setting.service.UserService;
import com.example.crm.util.DateTimeUtil;
import com.example.crm.util.SqlSessionUtil;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);
    public User login(String loginAct,String loginPwd,String ip) throws LoginException {
        Map<String,String> map = new HashMap<String,String>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        User user = userDao.login(map);
        if(user==null){
            throw new LoginException("账号密码错误");
        }
        //程序运行到此处说明账号密码正确，继续验证其它信息

        if(Integer.parseInt(user.getLockState())==0){
            throw new LoginException("该账号已被锁定");
        }
        if(user.getExpireTime().compareTo(DateTimeUtil.getSysTime())<0){
            throw new LoginException("已超出访问时间");
        }
        if(ip!=null&&user.getAllowIps().contains(ip)){
            throw new LoginException("此ip不具有访问权限");
        }
        return user;
    }
}
