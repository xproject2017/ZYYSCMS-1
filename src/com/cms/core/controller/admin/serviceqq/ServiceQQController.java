package com.cms.core.controller.admin.serviceqq;

import com.cms.core.bean.cms.busi.CserrviceqqBean;
import com.cms.core.bean.cms.busi.PproductcaseBean;
import com.cms.core.bean.cms.core.TBusiCserrviceqq;
import com.cms.core.bean.cms.core.TBusiPproductcase;
import com.cms.core.bean.cms.core.TSysStaff;
import com.cms.core.manager.admin.core.foundcms.BusiCserrviceqqManager;
import com.cms.util.Constants;
import com.cms.util.DateUtil;
import com.cms.util.ResourceUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 浩 on 2017/9/6.
 */

@Controller
@Scope("prototype")
@RequestMapping("/view/admin/qqservice")
public class ServiceQQController {
    @Autowired
    private BusiCserrviceqqManager  busiCserrviceqqManager;

    @RequestMapping("/getCserrviceqqListPage")
    public ModelAndView getCserrviceqqListPage(CserrviceqqBean bean){
        ModelAndView view = new ModelAndView("/admin/cserviceqq/list");
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/addCserrviceqqListPage")
    public ModelAndView addCserrviceqqListPage(CserrviceqqBean bean){
        ModelAndView view = new ModelAndView("/admin/cserviceqq/add");
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/updateCserrviceqqPage")
    public ModelAndView updateCserrviceqqPage(CserrviceqqBean bean){
        ModelAndView view = new ModelAndView("/admin/cserviceqq/update");
        TBusiCserrviceqq tBusiCserrviceqq = busiCserrviceqqManager.getBusiCserrviceqq(bean);
        BeanUtils.copyProperties(tBusiCserrviceqq,bean);
        bean.setSavetimestr(DateUtil.convertDate2String(tBusiCserrviceqq.getSavetime(),DateUtil.DATE_FORMAT));
        view.addObject("bean",bean );
        return view;
    }

    @RequestMapping("/getCserrviceqqListData")
    @ResponseBody
    public Map<String,Object> getProductListData(CserrviceqqBean bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        bean.setPageFlag(1);
        List<TBusiCserrviceqq> list = busiCserrviceqqManager.getBusiCserrviceqqList(bean);
        result.put("code", list.get(0).getResult());
        result.put("result", list);
        result.put("failinfo", list.get(0).getFailinfo());
        return result;
    }

    @RequestMapping("/addData")
    @ResponseBody
    public  Map<String,Object>  addData(HttpServletRequest request, CserrviceqqBean bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        TSysStaff tSysStaff = (TSysStaff) request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        bean.setUserid(tSysStaff.getUserid());
        TBusiCserrviceqq tBusiCserrviceqq = busiCserrviceqqManager.addBusiCserrviceqq(bean);

        result.put("code", tBusiCserrviceqq.getResult());
        result.put("failinfo", tBusiCserrviceqq.getFailinfo());
        return result;
    }

    @RequestMapping("/updateData")
    @ResponseBody
    public Map<String,Object> updateData(HttpServletRequest request,CserrviceqqBean bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        bean.setSavetime(DateUtil.convertString2Date(bean.getSavetimestr(), DateUtil.DATE_FORMAT));
        TSysStaff tSysStaff = (TSysStaff) request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        bean.setUserid(tSysStaff.getUserid());
        TBusiCserrviceqq tBusiCserrviceqq = busiCserrviceqqManager.updateBusiCserrviceqq(bean);

        result.put("code", tBusiCserrviceqq.getResult());
        result.put("failinfo", tBusiCserrviceqq.getFailinfo());
        return result;
    }


    @RequestMapping("/delData")
    @ResponseBody
    public Map<String,Object> delData(CserrviceqqBean bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        TBusiCserrviceqq tBusiCserrviceqq= busiCserrviceqqManager.deleteBusiCserrviceqq(bean);
        result.put("code", tBusiCserrviceqq.getResult());
        result.put("failinfo", tBusiCserrviceqq.getFailinfo());
        return result;
    }

}
