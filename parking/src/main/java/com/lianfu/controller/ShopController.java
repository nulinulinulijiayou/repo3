package com.lianfu.controller;

import com.lianfu.pojo.Ruls;
import com.lianfu.pojo.Shop;
import com.lianfu.pojo.ShowMessage;
import com.lianfu.service.RulsService;
import com.lianfu.service.impl.RulsServiceImpl;
import com.lianfu.service.impl.ShopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("shop")
public class ShopController {

    @Autowired
    HttpServletRequest request;
    @Autowired
    public ShopServiceImpl shopService;

    @Autowired
    public RulsServiceImpl rulsService;
    @RequestMapping("/login")
    public String login()  {
        return "load";
    }
    @RequestMapping("index")
    public ModelAndView index(ModelAndView modelAndView,Shop shop){
        request.getSession().invalidate();
        Shop shop1 = shopService.findShopByAccAndPwd(shop);
        if (shop1==null){
            modelAndView.setViewName("load");
            modelAndView.addObject("msg","您输入的用户名或密码错误！");
            return modelAndView;
        }else if ("user".equals(shop1.getRole())){
            request.getSession().setAttribute("shop",shop1);
      /*      System.out.println("shop已经放入session=========================================");*/
            modelAndView.setViewName("free");
            return modelAndView;
        }else {
            request.getSession().setAttribute("shop",shop1);
            List<Shop> shops = shopService.queryShop();
            List<Ruls> ruls= rulsService.queryRuls();
            int hightmoney=0;
            if(ruls.size()>0){
                for (Ruls r: ruls) {
                    if (hightmoney==0){
                        hightmoney=r.getHightmoney() ;
                    }else {
                        break;
                    }
                }
            }
            modelAndView.addObject("hightmoney",hightmoney);
            modelAndView.addObject("ruls",ruls);
            modelAndView.setViewName("admini");
            return modelAndView;
        }

    }

    /**
     * 查询页面 通过消费金额提示剩余时长
     * @param money
     * @return
     */
    @RequestMapping("showTime")
    @ResponseBody
    public ShowMessage showTime(String money)   {
        //   System.out.println("进入获取时间方法");
        ShowMessage message= shopService.showMoney(money);
     /*   System.out.println(message.getMessge()+"这是controller");*/
        return message;
    }


    /**
     * 每月初始化优惠次数
     * @param modelAndView
     * @return id查询当前每月优惠总额，rulsid查询商户对应规则
     */
    @RequestMapping("initShop")
    public ModelAndView initShop(ModelAndView modelAndView){
        Shop shop = new Shop();
        shopService.initShopByTime(shop);
        modelAndView.setViewName("free");
        return modelAndView;
    }

    /***
     * 管理员设置商户的优惠时长/次数 只选其一
     * @param //setStopAllTime setStopAllNums
     * @return
     */
    @RequestMapping("setShopTimeAndNums")
    public ModelAndView setShopTimeAndNums(ModelAndView modelAndView,Shop shop){
       /* System.out.println(shop.toString());*/
/*        shop.setStopAllTime("200");
        shop.setStopAllNums("150");*/
        shopService.setShopAllTimeAndNums(shop);
        modelAndView.setViewName("free");
        return modelAndView;
    }


    /**
     * 增加商户
     * @param modelAndView
     * @param shop
     * @return
     */
    @RequestMapping("add")
    public ModelAndView addShop(ModelAndView modelAndView,Shop shop){
  /*      System.out.println(shop.toString());*/
        shop.setRole("user");
        shopService.addShop(shop);
        modelAndView.setViewName("free");
        return modelAndView;
    }

    /**
     * 查询所有商户
     * @param modelAndView
     * @return
     */
    @RequestMapping("query")
    public ModelAndView queryShop(ModelAndView modelAndView){

      //  List<Shop> shops = shopService.queryShop();
       /* for (Shop shop1:shops){
            System.out.println(shop1.getShopName());
        }
        modelAndView.setViewName("admin");
        modelAndView.addObject("shops",shops);*/
        return modelAndView;

    }

    @RequestMapping("edit")
    public ModelAndView editShop(ModelAndView modelAndView){
        Shop shop=new Shop();
        shop.setShopId(22);
        shop.setShopPassword("6666");
        shop.setShopName("sange");
        shop.setRole("role");
        shopService.editShop(shop);
        modelAndView.setViewName("free");
        return modelAndView;
    }

    @RequestMapping("delete")
    public ModelAndView deleteShop(ModelAndView modelAndView){
            Shop shop=new Shop();
            shop.setShopId(22);
            shop.setShopName("sange");
            shop.setRole("role");
            shopService.deleteShop(shop);
            modelAndView.setViewName("free");
        return modelAndView;
    }

    @RequestMapping("setShopAllTimeAndNums")
    public ModelAndView setShopAllTimeAndNums(ModelAndView modelAndView){
        Shop shop=new Shop();
        shop.setStopAllNums("0");
        shop.setStopAllTime("50");
        shopService.setShopAllTimeAndNums(shop);
        modelAndView.setViewName("free");
        return modelAndView;
    }

    @RequestMapping("queryShopById")
    @ResponseBody
    public Shop queryShopById(String  id){
     /*   System.out.println(id);*/
       /* System.out.println(shopService.queryShopById(Integer.parseInt(id)));*/
        return  shopService.queryShopById(Integer.parseInt(id));
    }
    @RequestMapping("editShopTiAndNums")
    @ResponseBody
    public int editShopTiAndNums(Shop shop){
    /*    System.out.println(shop.toString());
        System.out.println( shopService.editShopTiAndNums(shop));*/
        return  shopService.editShopTiAndNums(shop);
    }
}
