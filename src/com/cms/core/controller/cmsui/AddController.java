package com.cms.core.controller.cmsui;

import com.cms.core.bean.cms.busi.PbespokeBean;
import com.cms.core.bean.cms.busi.PconsultationBean;
import com.cms.core.bean.cms.core.TBusiPbespoke;
import com.cms.core.bean.cms.core.TBusiPconsultation;
import com.cms.core.bean.cms.core.TBusiVip;
import com.cms.core.manager.admin.core.foundcms.BusiPbespokeManager;
import com.cms.core.manager.admin.core.foundcms.BusiPconsultationManager;
import com.cms.util.Constants;
import com.cms.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录
 */
@Controller
@Scope("prototype")
@RequestMapping("/cms/admin")
public class AddController {

    @Autowired
    private BusiPbespokeManager busiPbespokeManager;

    @Autowired
    private BusiPconsultationManager busiPconsultationManager;

    @RequestMapping(value = "/addBespoke")
    @ResponseBody
    public Object addBespoke(@ModelAttribute PbespokeBean bean,HttpServletRequest request){
        bean.setFlag(0);
        bean.setNavigationid1(4);
        bean.setNavigationid2(10);
        bean.setResmes("");
        TBusiVip vip = (TBusiVip)request.getSession().getAttribute(Constants.CMS_SESSION_INFO);
        bean.setVipid(vip.getVipid());
        bean.setUserid(vip.getVipid());
        bean.setPbespoketime(DateUtil.convertString2Date(bean.getPbespoketimestr()+" 00:00:00"));
        TBusiPbespoke tSysStaff = busiPbespokeManager.addBusiPbespoke(bean);
        Map<String,Object> result = new HashMap<>();
        result.put("code",tSysStaff.getResult());
        result.put("failinfo", tSysStaff.getFailinfo());
        return result;
    }

    @RequestMapping(value = "/addConsultation")
    @ResponseBody
    public Object addConsultation(@ModelAttribute PconsultationBean bean,HttpServletRequest request){
        bean.setFlag(0);
        bean.setNavigationid1(4);
        bean.setNavigationid2(13);
        bean.setResmes("");
        TBusiVip vip = (TBusiVip)request.getSession().getAttribute(Constants.CMS_SESSION_INFO);
        bean.setVipid(vip.getVipid());
        bean.setUserid(vip.getVipid());
        bean.setConstime(new Date());
        TBusiPconsultation tSysStaff = busiPconsultationManager.addBusiPconsultation(bean);
        Map<String,Object> result = new HashMap<>();
        result.put("code",tSysStaff.getResult());
        result.put("failinfo", tSysStaff.getFailinfo());
        return result;
    }
}
