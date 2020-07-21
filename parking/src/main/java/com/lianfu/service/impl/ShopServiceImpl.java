package com.lianfu.service.impl;

import com.lianfu.mapper.RulsMapper;
import com.lianfu.mapper.ShopMapper;
import com.lianfu.pojo.Ruls;
import com.lianfu.pojo.Shop;
import com.lianfu.pojo.ShowMessage;
import com.lianfu.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    HttpServletRequest request;

    @Autowired
    public  ShopMapper shopMapper;

    @Autowired
    RulsMapper rulsMapper;

    @Override
    public int addShop(Shop shop) {
        return shopMapper.addShop(shop);
    }

    @Override
    public int deleteShop(Shop shop) {
        return shopMapper.deleteShop(shop);
    }

    @Override
    public List<Shop> queryShop() {
        return shopMapper.queryShop();
    }

    @Override
    public int editShop(Shop shop) {
        return shopMapper.editShop(shop);
    }

    /***
     * 初始化所有商户的停车时间/停车次数
     * @param
     * @return
     */
    @Override
    public int initShopByTime(Shop shop) {
        return shopMapper.initShopByTime(shop);
    }

    @Override
    public int initShopByNums(Shop shop) {
        return shopMapper.initShopByNums(shop);
    }

    @Override
    public Shop queryShopById(int id) {
        return shopMapper.queryShopById(id);
    }

    @Override
    public int editShopTiAndNums(Shop shop) {
        return shopMapper.editShopTiAndNums(shop);
    }

    @Override
    public int setShopAllTimeAndNums(Shop shop) {
         shopMapper.setShopAllTime(shop);
         shopMapper.setShopAllNums(shop);
         return 1;
    }

    @Override
    public Shop findShopByAccAndPwd(Shop shop) {
        return shopMapper.findShopByAccAndPwd(shop);
    }

    @Override
    public ShowMessage showMoney(String money) {
        ShowMessage message=  new ShowMessage();
    /*   Shop shop= (Shop)request.getSession().getAttribute("shop");
         Ruls ruls=(Ruls)rulsMapper.queryRulsByShopId(shop.getRulsId());
        //Shop shopnow=  shopMapper.queryShopById(shop.getShopId());
        int rulsmoney= ruls.getRulsMoney();//商户活动金额标准
        int rulstime=ruls.getRulsTime();    //商户活动满减时间
        //String rulsNum=ruls.getRulsNums();  //商户活动满减次数
        int baseprice= shop.getBasePrice(); //基础价格
        //满减次数=(付款金额-基础金额)/优惠金额条件
        int rulsnumnow= (Integer.parseInt(money)-baseprice)/rulsmoney;
        //时间条件*/
      List<Ruls> ruls1=   rulsMapper.queryRuls();
        int hight_money=0;
        for (Ruls r:ruls1) {
            if(r.getHightmoney()!=0){
                hight_money=r.getHightmoney();
            }
        }
        if(Integer.parseInt(money)>hight_money){
            message.setMessge("您已经达到无限制停车的条件，不限制");
            message.setState("2");
            return message;
        }

        Ruls  youhui= rulsMapper.quertRulsnew(Integer.parseInt(money));
/*        if(1==ruls.getRulsId()){
            //判断商家剩余时间
            int time=rulsnumnow*rulstime;
            // if (Integer.parseInt(shopnow.getStopSurTime())-time>=0){
            //  return String.valueOf(time);
            return message;
        }*/
        if (youhui==null){
            message.setMessge("您输入的金额不得低于优惠0");
            message.setState("1");
            return message;
        }
        message.setMessge(String.valueOf(youhui.getRulsTime()));
        message.setState("1");
        return message;
    }

}


