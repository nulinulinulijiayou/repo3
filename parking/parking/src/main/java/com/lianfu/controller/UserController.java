package com.lianfu.controller;

import com.lianfu.pojo.User;
import com.lianfu.service.UserService;
import com.lianfu.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;

   @GetMapping("findUserById")
    @ResponseBody
    public User findUserById() {
        return userService.findUserById(1);
    }

    @GetMapping("findUser")
    @ResponseBody
    public String findUser() {
        return "hello";
    }


    @RequestMapping("/index")
    public String index()  {
        System.out.println("good boy");
        return "hello";
    }


}
