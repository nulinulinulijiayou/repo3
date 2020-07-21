package com.lianfu.mapper;

import com.lianfu.pojo.Ruls;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RulsMapper {
    public int addRules(Ruls ruls);
    public int deleteRules(Ruls ruls);
    public int editRulesByTime(Ruls ruls);
    public int editRulesByNums(Ruls shopid);
    public List<Ruls> queryRuls();
    public Ruls queryRulsByShopId(int shopid);
    public Ruls quertRulsnew(int money);
    public int edithight(int money);

}
