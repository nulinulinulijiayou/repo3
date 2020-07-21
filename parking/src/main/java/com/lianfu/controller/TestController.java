package com.lianfu.controller;

import com.lianfu.utils.Methods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;
    @RequestMapping("print")
    @ResponseBody
    public String print(String uuid,String rulsid,String youhui) throws IOException {
        System.out.println(uuid);
        Methods m = Methods.getInstance();
        if (Methods.token==null|| Methods.token==""){
            m.init("1092203046", "12425665ad72b81a16cd3fcb0af5538e");
            m.getFreedomToken();
            m.addPrinter("4004660914", "250759538952");
           logger.info("初始化打印机。。。。。。。。。。。。。。。。。。。。。。。");
        }
        logger.info("当前打印机的token===========================:"+ Methods.token);
        //m.refreshToken();//刷新token
        //m.deleteIcon("4004660914");

        Integer random6 = (int) ((Math.random() * 9 + 1) * 100000);
        String url = m.print("4004660914", "<QR>http://sodo.lianfuteam.com/parking/orders/sentbind?uuid="+uuid+"&rulsid="+rulsid+"&youhui="+youhui+"</QR>", "Z" + System.currentTimeMillis() + random6.toString());
        System.out.println("<QR>http://sodo.lianfuteam.com/parking/orders/sentbind?uuid="+uuid+"&rulsid="+rulsid+"&youhui="+youhui+"&rulsid="+rulsid+"</QR>");
        response.sendRedirect(url);
        logger.info("小票路径生成"+url);
        //System.out.println("localhost:8087/orders/sentbind?uuid="+uuid);
        //out.flush();
        //out.close();
        return "小票打印成功！";
    }
}
