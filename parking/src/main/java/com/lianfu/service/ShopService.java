package com.lianfu.service;

import com.lianfu.pojo.Shop;
import com.lianfu.pojo.ShowMessage;

import java.util.List;


public interface ShopService {
    public int addShop(Shop shop);
    public int deleteShop(Shop shop);
    public List<Shop> queryShop();
    public int editShop(Shop shop);
    public int setShopAllTimeAndNums(Shop shop);
    public Shop findShopByAccAndPwd(Shop shop);
    public ShowMessage showMoney(String money);
    public int initShopByTime(Shop shop);
    public int initShopByNums(Shop shop);
    public Shop queryShopById(int id);
    public int editShopTiAndNums(Shop shop);
}
