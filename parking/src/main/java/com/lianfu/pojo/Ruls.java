package com.lianfu.pojo;

public class Ruls {
    private int rulsId;         //规则ID
    private int rulsTime;       //停车优惠时长
    private int rulsMoney;   //消费金额
    private int rulsNums;    //停车优惠次数
    private int hightmoney;

    public int getRulsId() {
        return rulsId;
    }

    public void setRulsId(int rulsId) {
        this.rulsId = rulsId;
    }

    public int getRulsTime() {
        return rulsTime;
    }

    public void setRulsTime(int rulsTime) {
        this.rulsTime = rulsTime;
    }

    public int getRulsMoney() {
        return rulsMoney;
    }

    public void setRulsMoney(int rulsMoney) {
        this.rulsMoney = rulsMoney;
    }

    public int getRulsNums() {
        return rulsNums;
    }

    public void setRulsNums(int rulsNums) {
        this.rulsNums = rulsNums;
    }

    public int getHightmoney() {
        return hightmoney;
    }

    public void setHightmoney(int hightmoney) {
        this.hightmoney = hightmoney;
    }

    @Override
    public String toString() {
        return "Ruls{" +
                "rulsId=" + rulsId +
                ", rulsTime=" + rulsTime +
                ", rulsMoney='" + rulsMoney + '\'' +
                ", rulsNums='" + rulsNums + '\'' +
                '}';
    }
}
