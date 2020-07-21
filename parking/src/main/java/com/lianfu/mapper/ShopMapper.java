package com.lianfu.mapper;

import com.lianfu.pojo.Shop;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopMapper {
    public int addShop(Shop shop);
    public int deleteShop(Shop shop);
    public List<Shop> queryShop();
    public int editShop(Shop shop);
    public int setShopAllNums(Shop shop);
    public int setShopAllTime(Shop shop);
    public Shop findShopByAccAndPwd(Shop shop);
    public Shop queryShopById(int shopid);
    public int editShopTiAndNums(Shop shop);
    public int initShopByTime(Shop shop);
    public int initShopByNums(Shop shop);
}
