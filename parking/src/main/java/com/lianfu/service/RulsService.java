package com.lianfu.service;

import com.lianfu.pojo.Ruls;

import java.util.List;

public interface RulsService {
    public int addRules(Ruls ruls);
    public int deleteRules(Ruls ruls);
    public int editRulesByTime(Ruls ruls);
    public List<Ruls> queryRuls();
    public int editRulesByNums(Ruls ruls);
    public Ruls quertRulsnew(int money);
    public int edithight(int money);
}
