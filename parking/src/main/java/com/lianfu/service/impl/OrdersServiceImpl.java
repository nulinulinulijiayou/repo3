package com.lianfu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lianfu.mapper.OrderMapper;
import com.lianfu.mapper.RulsMapper;
import com.lianfu.mapper.ShopMapper;
import com.lianfu.pojo.Orders;
import com.lianfu.pojo.Ruls;
import com.lianfu.pojo.SetOrderSucess;
import com.lianfu.pojo.Shop;
import com.lianfu.service.OrdersService;
import com.lianfu.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class OrdersServiceImpl implements OrdersService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
   // public static String PARK_QUEERY = "http://php.eatfah.com/parksystem/public/zd/parkquery";
   // public static String PARK_FEE = "http://php.eatfah.com/parksystem/public/zd/parkfee";
    public static String PARK_QUEERY = "http://core.lianfuteam.com/parksystem/public/zd/parkquery";
    public static String PARK_FEE = "http://core.lianfuteam.com/parksystem/public/zd/parkfee";

    public static final String SELECTURL = "http://120.27.54.98/online_pay/lf_select";
    public static final String PAYSUCCESSURL = "http://120.27.54.98/online_pay/pay_success";
    @Autowired
    HttpServletRequest request;

    @Autowired
    Environment env;
    @Autowired
    public OrderMapper orderMapper;

    @Autowired
    public RulsMapper rulsMapper;

    @Autowired
    public ShopMapper shopMapper;


    /**
     * 用户扫描小票，记录车牌，更改订单信息及订单状态
     * @param uuid
     * @param carNum
     * @return
     */
    @Override
    public String startOrder(String uuid, String carNum,String rulsid,String youhui) throws MalformedURLException, ParseException {
        //通过uuid 查询订单，
        Orders orders= orderMapper.findOrderByUUid(uuid);
        // 查看状态 初次--修改状态，设置订单车牌号，

        System.out.println(youhui);
        //初始状态
        if (orders.getState()==null||"".equals(orders.getState())){
            Orders orders1= new Orders();
            orders1.setCarNum(carNum);
            orders1.setOrderId(uuid);
            orders1.setState("1");
            //接口：查询车辆是否在场--/车牌号/入场时间/免费到期时间/
            String appid= env.getProperty("rain.appid");
            JSONObject parameter = new JSONObject();
            parameter.put("app_id",appid);
            parameter.put("car_number", carNum);
            String parkQuery=  HttpUtils.doGet(SELECTURL+"?data="+ URLEncoder.encode(parameter.toJSONString()));
            JSONObject parkObj=JSONObject.parseObject(parkQuery);
            //付费parkQuery
            String data = parkObj.getString("data");
            JSONObject jsonObject = JSONObject.parseObject(data);
            if ("0".equals(parkObj.getString("ret_code"))) {
                if ("您已经达到无限制停车的条件，不限制".equals(youhui)){ //规则1商家时间优惠方式
                    parameter.put("mianfei",2);
                    String result= HttpUtils.doGet(PAYSUCCESSURL+"?data="+URLEncoder.encode(parameter.toJSONString()));
                //   }else if("2".equals(rulsid)){//规则2直接加一天
             } else {
                    String intime = jsonObject.getString("in_time");
                    parameter.put("mianfei", 1);
                    String youhuitime = addDateMinut(intime, Integer.parseInt(orders.getParkTime()));
                    parameter.put("expr_time", youhuitime);
                    String result = HttpUtils.doGet(PAYSUCCESSURL + "?data=" + URLEncoder.encode(parameter.toJSONString()));
                }
                //车牌输入，订单信息修改完毕
                orderMapper.editOrderState(orders1);
            }else {
                return "车辆不在场内";
            }
        }
        //已被使用状态
        if("1".equals(orders.getState())){
            System.out.println("该二维码已经过期！");
            return "该二维码已被使用!";
        }
        logger.info("已经成功录入车牌信息！订单完成！");
        return "已经成功录入车牌信息！";
    }


    /**
     * 打印小票 生成订单/扣除商家剩余时长/次数
     * @param uuid
     * @param time
     * @return
     */
    @Override
    @ResponseBody
    public SetOrderSucess setOrder(String time, String uuid) {

        //存入订单 uuid 商户id 停车时间/次数
        //修改商户的时间/次数数据
       /* String ordertime="0";
        int orderNums=0;*/
        String rulsid="1";
        Shop shop = (Shop)request.getSession().getAttribute("shop");
        //当前店的剩余状态
        Shop shopnow=  shopMapper.queryShopById(shop.getShopId());
        //查询当前店铺对应的规则
        Ruls ruls=  rulsMapper.queryRulsByShopId(shop.getRulsId());
      /*  if (1==ruls.getRulsId()){
            ordertime=time;
            rulsid="1";
            logger.info("进入扣费规则1，uuid="+uuid+"ordertime="+ordertime);
             int stopSurTimes= Integer.parseInt(shopnow.getStopSurTime())-Integer.parseInt(time);
            // shopnow.setStopSurTime(String.valueOf(stopSurTimes));
        }else if (2==ruls.getRulsId()){
             rulsid="2";
             orderNums=Integer.parseInt(time);
            logger.info("进入扣费规则2,uuid="+uuid+"orderNums="+orderNums);
             int stopNums= Integer.parseInt(shopnow.getStopSurNums())-Integer.parseInt(time);
        //     shopnow.setStopSurTime(String.valueOf(stopNums));
        }*/
        shopMapper.editShopTiAndNums(shopnow);
        //封装order 存入订单
        Orders order = new Orders();
        order.setOrderId(uuid);
        //order.setParkNum(orderNums);
        //order.setParkTime(ordertime);
        order.setParkTime(time);
        order.setShopId(shop.getShopId());
        orderMapper.addOrder(order);
        SetOrderSucess setOrderSucess= new SetOrderSucess();
        setOrderSucess.setRulsid(rulsid);
        setOrderSucess.setSuccess("订单数据已经生成！正在打印小票！");
        return setOrderSucess;
    }


    public static String addDateMinut(String day, int hour){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(day);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (date == null)
            return "";
        System.out.println("front:" + format.format(date)); //显示输入的日期
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hour);// 24小时制
        date = cal.getTime();
        System.out.println("after:" + format.format(date));  //显示更新后的日期
        cal = null;
        return format.format(date);

    }
}
