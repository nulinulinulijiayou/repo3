package com.lianfu.service.impl;

import com.lianfu.mapper.RulsMapper;
import com.lianfu.pojo.Ruls;
import com.lianfu.service.RulsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RulsServiceImpl implements RulsService {

    @Autowired
    public RulsMapper rulsMapper;

    @Override
    public int addRules(Ruls ruls) {
        return rulsMapper.addRules(ruls);
    }

    @Override
    public int deleteRules(Ruls ruls) {
        return rulsMapper.deleteRules(ruls);
    }

    @Override
    public int editRulesByTime(Ruls ruls) {
        return rulsMapper.editRulesByTime(ruls);
    }

    @Override
    public int editRulesByNums(Ruls ruls) {
        return rulsMapper.editRulesByNums(ruls);
    }

    @Override
    public Ruls quertRulsnew(int money) {
        return rulsMapper.quertRulsnew(money);
    }


    @Override
    public int edithight(int money) {
        return rulsMapper.edithight(money);
    }

    @Override
    public List<Ruls> queryRuls() {
        return rulsMapper.queryRuls();
    }



    public String setRuls(int rulstime,String rulsmoney,String rulsnums){
        return null;
    }
}

