package com.lianfu.controller;

import com.lianfu.pojo.Ruls;
import com.lianfu.service.impl.RulsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("ruls")
public class RulsController {

    @Autowired
    public RulsServiceImpl rulsService;


    /**
     * 通过rulsid 更改停车优惠规则的 金额-时间
     * @param modelAndView,rulsMoney,rulsTime
     * @param ruls
     * @return
     */
    @RequestMapping("editBytime")
    public ModelAndView editBytime(ModelAndView modelAndView, Ruls ruls){
        rulsService.editRulesByTime(ruls);
        modelAndView.setViewName("admini");
        return modelAndView;
    }


    /**
     * 通过rulsid 更改停车优惠规则的 金额-次数
     * @param modelAndView,rulsMoney,rulsNums
     * @param ruls
     * @return
     */
    @RequestMapping("editByNums")
    public ModelAndView editByNums(ModelAndView modelAndView, Ruls ruls){
        ruls.setRulsId(2);
        rulsService.editRulesByNums(ruls);
        modelAndView.setViewName("admini");
        return modelAndView;
    }

    /**
     * 通过rulsid 更改停车优惠规则的 金额-次数
     * @param modelAndView,rulsMoney,rulsNums
     * @param
     * @return
     */
    @RequestMapping("edithight")
    public ModelAndView edithight(ModelAndView modelAndView, int  money){
        rulsService.edithight(money);
        modelAndView.setViewName("admini");
        return modelAndView;
    }
    /***
     * 增加优惠规则
     * @param modelAndView
     * @return
     */
    @RequestMapping("add")
    public ModelAndView addRuls(ModelAndView modelAndView,Ruls ruls){
/*      Ruls ruls=new Ruls();
        ruls.setRulsId(1);
        ruls.setRulsMoney("1000");
        ruls.setRulsTime("10");*/
        modelAndView.setViewName("admini");
        rulsService.addRules(ruls);
        return modelAndView;
    }

    /***
     * 查询优惠规则 暂不启用
     * @param modelAndView
     * @return
     */
    @RequestMapping("query")
    public ModelAndView queryRuls(ModelAndView modelAndView){
        List<Ruls> ruls = rulsService.queryRuls();
/*        for (Ruls r:ruls) {
            System.out.println(r.getRulsMoney());
        }*/
        modelAndView.setViewName("admini");

        return modelAndView;
    }

    /***
     * 删除优惠规则 暂不启用
     * @param modelAndView
     * @return
     */
    @RequestMapping("delete")
    public ModelAndView deleteRuls(ModelAndView modelAndView,Ruls ruls){
        rulsService.deleteRules(ruls);
        modelAndView.setViewName("admini");
        return modelAndView;
    }

}
