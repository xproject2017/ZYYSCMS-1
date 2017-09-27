package com.cms.core.controller.admin.vip;

import com.cms.core.bean.cms.busi.NationalBean;
import com.cms.core.bean.cms.busi.PproductBean;
import com.cms.core.bean.cms.busi.VipBean;
import com.cms.core.bean.cms.core.TBusiVip;
import com.cms.core.bean.cms.core.TSysRole;
import com.cms.core.bean.cms.core.TSysStaff;
import com.cms.core.manager.admin.core.foundcms.BusiVipManager;
import com.cms.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 浩 on 2017/9/13.
 */
@Controller
@Scope("prototype")
@RequestMapping("/view/admin/vip")
public class VipControl {

    @Autowired
    private BusiVipManager busiVipManager;

    @RequestMapping("/getVipListPage")
    public ModelAndView getProductListPage(PproductBean bean){
        ModelAndView view = new ModelAndView("/admin/vip/list");
        TBusiVip vip = new TBusiVip();
        vip.setPageFlag(0);
        List<TBusiVip> list = busiVipManager.getBusiVipList(vip);
        view.addObject("data", list);
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/getVipDetailPage")
    public ModelAndView getVipDetailPage(HttpServletRequest request,TBusiVip bean){
        Map<String,Object> map = new HashMap<>();
        VipBean vip = new VipBean();

        TBusiVip retVip = busiVipManager.getBusiVip(bean);
        BeanUtils.copyProperties(retVip,vip);
        vip.setSavetimestr(DateUtil.convertDate2String(vip.getSavetime(), DateUtil.DATE_FORMAT));
        map.put("users",vip);
        return new ModelAndView("/admin/vip/vipdetails",map);
    }

    @RequestMapping("/getVipListData")
    @ResponseBody
    public Map<String,Object> getVipListData(HttpServletRequest request, VipBean bean){
        return getVip(request,bean);
    }


    private HashMap<String, Object> getVip(HttpServletRequest request, VipBean bean) {

        HashMap<String, Object> result = new HashMap<String, Object>();
        bean.setPageFlag(1);
        List<TBusiVip> tBusiVipList = null;

        tBusiVipList = busiVipManager.getBusiVipList(bean);
        List<VipBean> vipBeans = new ArrayList<VipBean>();

        //所有的会员清单
        if (tBusiVipList.get(0).getResult() == 0) {
            for (TBusiVip t : tBusiVipList) {
                VipBean vipBean = new VipBean();
                BeanUtils.copyProperties(t, vipBean);
                vipBeans.add(vipBean);
            }
        } else {
            result.put("code", tBusiVipList.get(0).getResult());
            result.put("failinfo", tBusiVipList.get(0).getFailinfo());
            return result;
        }

        for (VipBean vipBean : vipBeans) {
            if (vipBean.getSavetime() != null) {
                vipBean.setSavetimestr(vipBean.getSavetime().getTime() + "");
            }
        }


        result.put("code", tBusiVipList.get(0).getResult());
        result.put("failinfo", tBusiVipList.get(0).getFailinfo());
        result.put("result", vipBeans);

        return result;
    }
}
