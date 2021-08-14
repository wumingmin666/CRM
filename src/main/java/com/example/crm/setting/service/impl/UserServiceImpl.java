package com.example.crm.setting.service.impl;

import com.example.crm.setting.dao.UserDao;
import com.example.crm.setting.service.UserService;
import com.example.crm.util.SqlSessionUtil;

public class UserServiceImpl implements UserService {
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);
}
