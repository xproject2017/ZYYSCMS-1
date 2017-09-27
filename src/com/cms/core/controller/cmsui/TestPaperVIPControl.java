package com.cms.core.controller.cmsui;

import com.cms.core.bean.cms.busi.IndexBean;
import com.cms.core.bean.cms.core.TBusiTestpapervip;
import com.cms.core.bean.cms.core.TBusiVip;
import com.cms.core.bean.cms.core.TSysMenu;
import com.cms.core.manager.admin.core.foundcms.BusiTestpapervipManager;
import com.cms.core.manager.admin.core.foundcms.BusiVipManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@Scope("prototype")
@RequestMapping("/testPaper")
public class TestPaperVIPControl {
    @Autowired
    private BusiTestpapervipManager busiTestpapervipManager;
    @Autowired
    private BusiVipManager busiVipManager;

    @RequestMapping("/addData")
    @ResponseBody
    public Map<String,Object> addData(TBusiTestpapervip bean){
        Map<String,Object> result = new HashMap<>();
        TSysMenu param = new TSysMenu();
        param.setPageFlag(0);
        param.setMtype(0);
        TBusiTestpapervip vip = busiTestpapervipManager.addBusiTestpapervip(bean);//全量菜单
        if(vip.getResult()==0){
            TBusiVip bvip = new TBusiVip();
            bvip.setVipid(vip.getVipid());
            TBusiVip newVip = busiVipManager.getBusiVip(bvip);
            newVip.setAflag(1);
            TBusiVip retVip = busiVipManager.updateBusiVip(newVip);
            result.put("code",retVip.getResult());
            result.put("bean", retVip);
            result.put("failinfo",retVip.getFailinfo());
        }else{
            result.put("code",vip.getResult());
            result.put("bean", vip);
            result.put("failinfo",vip.getFailinfo());
        }
        return result;
    }

}
