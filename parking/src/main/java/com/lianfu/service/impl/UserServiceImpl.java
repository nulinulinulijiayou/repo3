package com.lianfu.service.impl;

import com.lianfu.mapper.UserMapper;
import com.lianfu.pojo.User;
import com.lianfu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User findUserById(int userid){
        return userMapper.findUserById(userid);
    }

    public User findUserByAccount(String account){
        return userMapper.findUserByAccount(account);

    }
    public User findUserByAccAndPwd(User user) {
        return userMapper.findUserByAccAndPwd(user);
    }
}
