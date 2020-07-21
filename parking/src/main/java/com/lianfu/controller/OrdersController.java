package com.lianfu.controller;


import com.lianfu.pojo.SetOrderSucess;
import com.lianfu.service.impl.OrdersServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.net.MalformedURLException;
import java.text.ParseException;

@Controller
@RequestMapping("orders")
public class OrdersController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OrdersServiceImpl ordersService;


    /**
     * 用户扫描小票，记录车牌，更改订单信息及订单状态
     * @param uuid
     * @param carNum
     * @return
     */
    @GetMapping("startOrder")
    @ResponseBody
    public String startOrder(ModelAndView modelAndView,String uuid,String carNum,String rulsid,String youhui) throws MalformedURLException, ParseException {
        //存入订单 车牌号，消费金额，计算优惠时长，减去商家应有时长
        logger.info("用户已经填写车牌！ uuid："+uuid+"车牌号："+carNum);
        String s= ordersService.startOrder(uuid,carNum,rulsid,youhui);
        modelAndView.setViewName("binding");
     //   modelAndView.addObject("parkMoney",money);
        return s;
    }


    @RequestMapping("add")
    public ModelAndView addOrder(ModelAndView modelAndView){
        return modelAndView;
    }


    /**
     * 打印小票 生成订单/扣除商家剩余时长/次数
     * @param uuid
     * @param time
     * @return
     */
    @GetMapping("setOrder")
    @ResponseBody
    public SetOrderSucess setOrder(ModelAndView modelAndView, String time, String uuid){
        logger.info("订单已经生成-----------uuid为 "+uuid+"       时间/次数为 "+time);
        SetOrderSucess setorder = null;
        try {
            setorder= ordersService.setOrder(time,uuid);
            //modelAndView.setViewName("free");
            return setorder;
        }catch (Exception e){
            setorder.setSuccess("系统出现异常");
            return setorder;
        }
    }

    /**
     * 小票扫描接口， 记录uuid跳转到输入车牌页面
     * @param modelAndView
     * @param uuid
     * @return
     */
    @RequestMapping("sentbind")
    public ModelAndView sentbind(ModelAndView modelAndView,String uuid,String rulsid,String youhui){
        logger.info("用户已经扫码进入 填写车牌号界面!优惠时长"+youhui);
        modelAndView.addObject("parkuuid",uuid);
        modelAndView.addObject("rulsid",rulsid);
        modelAndView.addObject("youhui",youhui);
        modelAndView.setViewName("binding");
        return modelAndView;
    }

    @RequestMapping("edit")
    public ModelAndView editOrder(ModelAndView modelAndView){
        return modelAndView;
    }

    @RequestMapping("delete")
    public ModelAndView deleteOrder(ModelAndView modelAndView){
        return modelAndView;
    }
}
