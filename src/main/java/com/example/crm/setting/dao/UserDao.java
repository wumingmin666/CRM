package com.example.crm.setting.dao;

import com.example.crm.setting.domain.User;

import java.util.Map;

public interface UserDao {

    User login(Map<String, String> map);
}
