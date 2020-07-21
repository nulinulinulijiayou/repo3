package com.lianfu.mapper;

import com.lianfu.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User findUserById(int userid);

}
