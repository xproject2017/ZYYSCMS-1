package com.cms.core.controller.admin.exercises;

import com.cms.core.bean.cms.busi.BusiExercisesBean;
import com.cms.core.bean.cms.busi.IndustryinformationBean;
import com.cms.core.bean.cms.busi.PproductcaseBean;
import com.cms.core.bean.cms.core.TBusiExercises;
import com.cms.core.bean.cms.core.TBusiIndustryinformation;
import com.cms.core.bean.cms.core.TBusiPproductcase;
import com.cms.core.bean.cms.core.TSysStaff;
import com.cms.core.manager.admin.core.foundcms.BusiExercisesManager;
import com.cms.core.manager.admin.core.foundcms.BusiExercisesManagerImpl;
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
 * Created by 浩 on 2017/9/16.
 */
@Controller
@Scope("prototype")
@RequestMapping("/view/admin/exercises")
public class ExercisesControl {
    @Autowired
    private BusiExercisesManager busiExercisesManager;

    @RequestMapping("/getExercisesListPage")
    public ModelAndView getExercisesListPage(BusiExercisesBean bean){
        ModelAndView view = new ModelAndView("/admin/exercises/list");
        view.addObject("bean", bean);
        return view;
    }


    @RequestMapping("/getExercisesListData")
    @ResponseBody
    public Map<String,Object> getExercisesListData(BusiExercisesBean bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        bean.setPageFlag(1);
        List<TBusiExercises> list = busiExercisesManager.getBusiExercisesList(bean);
        result.put("code", list.get(0).getResult());
        result.put("result", list);
        result.put("failinfo", list.get(0).getFailinfo());
        return result;
    }

    @RequestMapping("/addExercisesPage")
    public ModelAndView addExercisesPage(BusiExercisesBean bean){
        ModelAndView view = new ModelAndView("/admin/exercises/add");
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/updateExercisesPage")
    public ModelAndView updateExercisesPage(BusiExercisesBean bean){
        ModelAndView view = new ModelAndView("/admin/exercises/update");
        TBusiExercises tBusiExercises = busiExercisesManager.getBusiExercises(bean);
        BeanUtils.copyProperties(tBusiExercises,bean);
        bean.setSavetimestr(DateUtil.convertDate2String(tBusiExercises.getSavetime(),DateUtil.DATE_FORMAT));
        view.addObject("bean",bean );
        return view;
    }

    @RequestMapping("/addData")
    @ResponseBody
    public  Map<String,Object>  addData(HttpServletRequest request, BusiExercisesBean bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        TSysStaff tSysStaff = (TSysStaff) request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        bean.setUserid(tSysStaff.getUserid());
        TBusiExercises tBusiExercises = busiExercisesManager.addBusiExercises(bean);
        result.put("code", tBusiExercises.getResult());
        result.put("failinfo", tBusiExercises.getFailinfo());
        return result;
    }

    @RequestMapping("/updateData")
    @ResponseBody
    public Map<String,Object> updateData(HttpServletRequest request,BusiExercisesBean bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        bean.setSavetime(DateUtil.convertString2Date(bean.getSavetimestr(), DateUtil.DATE_FORMAT));
        TSysStaff tSysStaff = (TSysStaff) request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        bean.setUserid(tSysStaff.getUserid());
        TBusiExercises tBusiExercises= busiExercisesManager.updateBusiExercises(bean);

        result.put("code", tBusiExercises.getResult());
        result.put("failinfo", tBusiExercises.getFailinfo());
        return result;
    }


    @RequestMapping("/delData")
    @ResponseBody
    public Map<String,Object> delData(BusiExercisesBean bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        TBusiExercises tBusiExercises= busiExercisesManager.deleteBusiExercises(bean);
        result.put("code", tBusiExercises.getResult());
        result.put("failinfo", tBusiExercises.getFailinfo());
        return result;
    }





}
