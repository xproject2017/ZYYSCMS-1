package com.cms.core.controller.admin.apply;

import com.cms.core.bean.cms.busi.ApplyBean;
import com.cms.core.bean.cms.core.TBusiApply;
import com.cms.core.bean.cms.core.TSysStaff;
import com.cms.core.manager.admin.core.foundcms.BusiApplyManager;
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
 * 应聘信息
 */
@Controller
@Scope("prototype")
@RequestMapping("/view/admin/apply")
public class ApplyController {

    @Autowired
    private BusiApplyManager busiApplyManager;

    /**
     * 获取国检账户列表页面
     * @return 页面
     */
    @RequestMapping("/getApplyListPage")
    public ModelAndView getApplyListPage(ApplyBean bean){
        ModelAndView view = new ModelAndView("/admin/apply/applylist");
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/updateApplyPage")
    public ModelAndView updateApplyPage(ApplyBean bean){
        ModelAndView view = new ModelAndView("/admin/apply/updateapply");
        TBusiApply apply = busiApplyManager.getBusiApply(bean);
        BeanUtils.copyProperties(apply, bean);
        bean.setSavetimestr(DateUtil.convertDate2String(apply.getSavetime(), DateUtil.DATE_FORMAT));
        bean.setFlagstr(apply.getFlag() == 0 ? "未处理" : "已处理");
        bean.setGenderstr(apply.getGender()==0?"男":"女");
        bean.setEducationstr(educationConvert(apply.getEducation()));
        view.addObject("bean",bean );
        return view;
    }

    /**
     * 获取国检账户列表数据
     * @return
     */
    @RequestMapping("/getApplyListData")
    @ResponseBody
    public Map<String,Object> getApplyListData(ApplyBean bean){
        HashMap<String, Object> result = new HashMap<>();
        bean.setPageFlag(1);
        List<TBusiApply>	list = busiApplyManager.getBusiApplyList(bean);
        int code = list.get(0).getResult();
        if(code == 0){
            List<TBusiApply> busiApplyList = new ArrayList<>();
            for(TBusiApply apply : list){
                ApplyBean applyBean = new ApplyBean();
                BeanUtils.copyProperties(apply, applyBean);
                applyBean.setFlagstr(apply.getFlag()==0?"未处理":"已处理");
                applyBean.setGenderstr(apply.getGender()==0?"男":"女");
                applyBean.setEducationstr(educationConvert(apply.getEducation()));
                busiApplyList.add(applyBean);
            }
            result.put("result", busiApplyList);
        }
        result.put("code", code);
        result.put("failinfo", list.get(0).getFailinfo());
        return result;
    }



    @RequestMapping("/updateData")
    @ResponseBody
    public Map<String,Object> updateData(HttpServletRequest request,ApplyBean bean){
        HashMap<String, Object> result = new HashMap<>();
        TSysStaff tSysStaff = (TSysStaff) request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        bean.setUserid(tSysStaff.getUserid());
        bean.setFlag(1);//已处理
        bean.setSavetime(DateUtil.convertString2Date(bean.getSavetimestr(), DateUtil.DATE_FORMAT));
        TBusiApply TBusiApply = busiApplyManager.updateBusiApply(bean);
        result.put("code", TBusiApply.getResult());
        result.put("failinfo", TBusiApply.getFailinfo());
        return result;
    }

    private String educationConvert(int education){
        if(education==0){
            return "初中";
        }else if(education==1){
            return "高中";
        }else if(education==2){
            return "大专";
        }else if(education==3){
            return "本科";
        }else if(education==4){
            return "硕士";
        }else if(education==5){
            return "博士";
        }
        return "";
    }
}
