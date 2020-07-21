package com.lianfu.pojo;

public class Orders {
    private String orderId;    //订单ID
    private int shopId;        //商户ID
    private int parkNum;       //剩余停车次数
    private String parkTime;   //剩余停车时间
    private String carNum;     //车牌号
    private String state;        //预留字段
    private String yu2;
    private String yu3;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getParkNum() {
        return parkNum;
    }

    public void setParkNum(int parkNum) {
        this.parkNum = parkNum;
    }

    public String getParkTime() {
        return parkTime;
    }

    public void setParkTime(String parkTime) {
        this.parkTime = parkTime;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getYu2() {
        return yu2;
    }

    public void setYu2(String yu2) {
        this.yu2 = yu2;
    }

    public String getYu3() {
        return yu3;
    }

    public void setYu3(String yu3) {
        this.yu3 = yu3;
    }
}
