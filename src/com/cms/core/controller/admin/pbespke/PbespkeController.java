package com.cms.core.controller.admin.pbespke;

import com.cms.core.bean.cms.busi.PbespokeBean;
import com.cms.core.bean.cms.core.TBusiPbespoke;
import com.cms.core.bean.cms.core.TSysStaff;
import com.cms.core.manager.admin.core.foundcms.BusiPbespokeManager;
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
 * 网上预约
 */
@Controller
@Scope("prototype")
@RequestMapping("/view/admin/pbespke")
public class PbespkeController {

    @Autowired
    private BusiPbespokeManager busiPbespokeManager;

    /**
     * 获取国检账户列表页面
     * @return 页面
     */
    @RequestMapping("/getBespkeListPage")
    public ModelAndView getBespkeListPage(PbespokeBean bean){
        ModelAndView view = new ModelAndView("/admin/pbespoke/pbespokelist");
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/updateBespkePage")
    public ModelAndView updateBespkePage(PbespokeBean bean){
        ModelAndView view = new ModelAndView("/admin/pbespoke/updatepbespoke");
        TBusiPbespoke tBusiPbespoke = busiPbespokeManager.getBusiPbespoke(bean);
        BeanUtils.copyProperties(tBusiPbespoke,bean);
        bean.setSavetimestr(DateUtil.convertDate2String(tBusiPbespoke.getSavetime(),DateUtil.DATE_FORMAT));
        bean.setPbespoketimestr(DateUtil.convertDate2String(tBusiPbespoke.getPbespoketime(), DateUtil.DATE_FORMAT));
        bean.setFlagstr(tBusiPbespoke.getFlag()==0?"未处理":"已处理");
        view.addObject("bean",bean );
        return view;
    }

    /**
     * 获取国检账户列表数据
     * @return
     */
    @RequestMapping("/getBespkeListData")
    @ResponseBody
    public Map<String,Object> getBespkeListData(PbespokeBean bean){
        HashMap<String, Object> result = new HashMap<>();
        bean.setPageFlag(1);
        List<TBusiPbespoke>	list = busiPbespokeManager.getBusiPbespokeList(bean);
        int code = list.get(0).getResult();
        if(code == 0){
            List<TBusiPbespoke> busiPbespokeList = new ArrayList<>();
            for(TBusiPbespoke bespoke : list){
                PbespokeBean pbespokeBean = new PbespokeBean();
                BeanUtils.copyProperties(bespoke,pbespokeBean);
                pbespokeBean.setPbespoketimestr(DateUtil.convertDate2String(bespoke.getPbespoketime(), DateUtil.DATE_FORMAT));
                pbespokeBean.setFlagstr(bespoke.getFlag()==0?"未处理":"已处理");
                busiPbespokeList.add(pbespokeBean);
            }
            result.put("result", busiPbespokeList);
        }
        result.put("code", code);
        result.put("failinfo", list.get(0).getFailinfo());
        return result;
    }



    @RequestMapping("/updateData")
    @ResponseBody
    public Map<String,Object> updateData(HttpServletRequest request,PbespokeBean bean){
        HashMap<String, Object> result = new HashMap<>();
        TSysStaff tSysStaff = (TSysStaff) request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        bean.setUserid(tSysStaff.getUserid());
        bean.setFlag(1);//已处理
        bean.setSavetime(DateUtil.convertString2Date(bean.getSavetimestr(), DateUtil.DATE_FORMAT));
        TBusiPbespoke TBusiPbespoke = busiPbespokeManager.updateBusiPbespoke(bean);
        result.put("code", TBusiPbespoke.getResult());
        result.put("failinfo", TBusiPbespoke.getFailinfo());
        return result;
    }
}
