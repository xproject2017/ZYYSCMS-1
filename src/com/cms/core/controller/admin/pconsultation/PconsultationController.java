package com.cms.core.controller.admin.pconsultation;

import com.cms.core.bean.cms.busi.PconsultationBean;
import com.cms.core.bean.cms.core.TBusiPconsultation;
import com.cms.core.bean.cms.core.TSysStaff;
import com.cms.core.manager.admin.core.foundcms.BusiPconsultationManager;
import com.cms.util.Constants;
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
 * Created by zyy on 2016/5/12.
 * 产品咨询
 */
@Controller
@Scope("prototype")
@RequestMapping("/view/admin/pconsultation")
public class PconsultationController {

    @Autowired
    private BusiPconsultationManager busiPconsultationManager;

    /**
     * 获取国检账户列表页面
     * @return 页面
     */
    @RequestMapping("/getConsultationListPage")
    public ModelAndView getConsultationListPage(PconsultationBean bean){
        ModelAndView view = new ModelAndView("/admin/pconsultation/pconsultationlist");
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/updateConsultationPage")
    public ModelAndView updateConsultationPage(PconsultationBean bean){
        ModelAndView view = new ModelAndView("/admin/pconsultation/updatepconsultation");
        TBusiPconsultation tBusiPconsultation = busiPconsultationManager.getBusiPconsultation(bean);
        BeanUtils.copyProperties(tBusiPconsultation,bean);
        bean.setSavetimestr(DateUtil.convertDate2String(tBusiPconsultation.getSavetime(),DateUtil.DATE_FORMAT));
        bean.setConstimestr(DateUtil.convertDate2String(tBusiPconsultation.getConstime(), DateUtil.DATE_FORMAT));
        bean.setFlagstr(tBusiPconsultation.getFlag()==0?"未处理":"已处理");
        view.addObject("bean",bean );
        return view;
    }

    /**
     * 获取国检账户列表数据
     * @return
     */
    @RequestMapping("/getConsultationListData")
    @ResponseBody
    public Map<String,Object> getConsultationListData(PconsultationBean bean){
        HashMap<String, Object> result = new HashMap<>();
        bean.setPageFlag(1);
        List<TBusiPconsultation>	list = busiPconsultationManager.getBusiPconsultationList(bean);
        int code = list.get(0).getResult();
        if(code == 0){
            List<TBusiPconsultation> busiPbespokeList = new ArrayList<>();
            for(TBusiPconsultation bespoke : list){
                PconsultationBean PconsultationBean = new PconsultationBean();
                BeanUtils.copyProperties(bespoke,PconsultationBean);
                PconsultationBean.setConstimestr(DateUtil.convertDate2String(bespoke.getConstime(), DateUtil.DATE_FORMAT));
                PconsultationBean.setFlagstr(bespoke.getFlag()==0?"未处理":"已处理");
                busiPbespokeList.add(PconsultationBean);
            }
            result.put("result", busiPbespokeList);
        }
        result.put("code", code);
        result.put("failinfo", list.get(0).getFailinfo());
        return result;
    }



    @RequestMapping("/updateData")
    @ResponseBody
    public Map<String,Object> updateData(HttpServletRequest request,PconsultationBean bean){
        HashMap<String, Object> result = new HashMap<>();
        TSysStaff tSysStaff = (TSysStaff) request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        bean.setUserid(tSysStaff.getUserid());
        bean.setFlag(1);//已处理
        bean.setSavetime(DateUtil.convertString2Date(bean.getSavetimestr(), DateUtil.DATE_FORMAT));
        TBusiPconsultation TBusiPconsultation = busiPconsultationManager.updateBusiPconsultation(bean);
        result.put("code", TBusiPconsultation.getResult());
        result.put("failinfo", TBusiPconsultation.getFailinfo());
        return result;
    }
}
