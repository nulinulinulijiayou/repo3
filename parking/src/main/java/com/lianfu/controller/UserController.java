package com.lianfu.controller;

import com.lianfu.mapper.UserMapper;
import com.lianfu.pojo.User;
import com.lianfu.service.impl.UserServiceImpl;
import com.lianfu.utils.FileDayinJI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserServiceImpl userService;

    @Autowired
    public FileDayinJI fileDayinJI;
    @RequestMapping("/login")
    public String login()  {
        return "load";
    }

    @Autowired
    UserMapper userMapper;


    @RequestMapping("dong")
    public ModelAndView dong(ModelAndView modelAndView){
        User user = new User();
        user.setUserid(1);
       List<User>  users=   userMapper.dong(user);
        System.out.println(users.size());
        modelAndView.setViewName("free");
        return modelAndView;
    }



    @RequestMapping("/index")
    public ModelAndView index(User user, ModelAndView modelAndView)  {
        User user1 = userService.findUserByAccAndPwd(user);
        if (user1==null){
             modelAndView.setViewName("load");
             modelAndView.addObject("msg","您输入的用户名或密码错误！");
             return modelAndView;
        }else if ("user".equals(user1.getRole())){
          //  System.out.println("进入用户界面！");
            modelAndView.setViewName("free");
            return modelAndView;
        }else {
            int i;
         //   System.out.println("进入管理员页面");
            modelAndView.setViewName("QRcode");
            return modelAndView;
        }
    }

    @RequestMapping("setRules")
    public ModelAndView setRules(ModelAndView modelAndView){
        return modelAndView;
    }

    @RequestMapping("getTime")
    @ResponseBody
    public User getTime(User user)   {
     //   System.out.println("进入获取时间方法");
        user.setAccount("100000");
        return user;
    }

    @RequestMapping("subAndPrint")
    @ResponseBody
    public User enableOrder(User user)   {
        user.setUsername("工作人员");
        System.out.println("进入获取时间方法");
        user.setAccount("100000");
        System.out.println(user);
        fileDayinJI.startPrint(user);
        return user;
    }
    @RequestMapping("redirectUrl")
    public void redirectUrl(ModelAndView modelAndView){

    }

}
