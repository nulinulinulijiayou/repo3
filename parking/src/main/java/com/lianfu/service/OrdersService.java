package com.lianfu.service;

import com.lianfu.pojo.SetOrderSucess;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.text.ParseException;

@Service
public interface OrdersService {
    public String startOrder(String money,String carNum,String rulsid,String youhui) throws MalformedURLException, ParseException;
    public SetOrderSucess setOrder(String time, String uuid);

}
