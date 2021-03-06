package com.lianfu.service;

import com.lianfu.pojo.User;

public interface UserService {
    public User findUserById(int userid);
    public User findUserByAccount(String account);
    public User findUserByAccAndPwd(User user);
}
