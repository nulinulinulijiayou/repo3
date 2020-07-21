package com.lianfu.pojo;

public class Shop {
    private int shopId;         //商户ID
    private String shopName;    //商户名称
    private String shopAccount; //商户账号
    private String shopPassword;//商户密码
    private String role;        //商户角色--区分商户/管理员
    private int rulsId;         //规则ID
   // private String orderId;
    private int basePrice;      //基础价格
    private String stopAllTime; //停车优惠总时长
    private String stopAllNums; //停车优惠总次数
    private String stopSurTime; //停车优惠剩余时长
    private String stopSurNums; //停车优惠剩余次数

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public String getStopAllTime() {
        return stopAllTime;
    }

    public void setStopAllTime(String stopAllTime) {
        this.stopAllTime = stopAllTime;
    }

    public String getStopAllNums() {
        return stopAllNums;
    }

    public void setStopAllNums(String stopAllNums) {
        this.stopAllNums = stopAllNums;
    }

    public String getStopSurTime() {
        return stopSurTime;
    }

    public void setStopSurTime(String stopSurTime) {
        this.stopSurTime = stopSurTime;
    }

    public String getStopSurNums() {
        return stopSurNums;
    }

    public void setStopSurNums(String stopSurNums) {
        this.stopSurNums = stopSurNums;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAccount() {
        return shopAccount;
    }

    public void setShopAccount(String shopAccount) {
        this.shopAccount = shopAccount;
    }

    public String getShopPassword() {
        return shopPassword;
    }

    public void setShopPassword(String shopPassword) {
        this.shopPassword = shopPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getRulsId() {
        return rulsId;
    }

    public void setRulsId(int rulsId) {
        this.rulsId = rulsId;
    }



    @Override
    public String toString() {
        return "Shop{" +
                "shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", shopAccount='" + shopAccount + '\'' +
                ", shopPassword='" + shopPassword + '\'' +
                ", role='" + role + '\'' +
                ", rulsId=" + rulsId +

                ", stopAllTime='" + stopAllTime + '\'' +
                ", stopAllNums='" + stopAllNums + '\'' +
                ", stopSurTime='" + stopSurTime + '\'' +
                ", stopSurNums='" + stopSurNums + '\'' +
                '}';
    }
}
