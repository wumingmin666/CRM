package com.example.crm.setting.service;

import com.example.crm.exception.LoginException;
import com.example.crm.setting.domain.User;

public interface UserService {
    User login(String loginAct, String loginPwd, String ip) throws LoginException;
}
