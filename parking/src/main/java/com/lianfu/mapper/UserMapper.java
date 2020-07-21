package com.lianfu.mapper;

import com.lianfu.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    User findUserById(int userid);
    User findUserByAccount(String account);
    User findUserByAccAndPwd(User user);
    List<User> dong(User user);
}
