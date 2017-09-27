package com.cms.core.controller.admin.testpaper;

import com.cms.core.bean.cms.busi.BusiExercisesBean;
import com.cms.core.bean.cms.busi.TestPaperBean;
import com.cms.core.bean.cms.core.TBusiExercises;
import com.cms.core.bean.cms.core.TBusiTestexercise;
import com.cms.core.bean.cms.core.TBusiTestpaper;
import com.cms.core.bean.cms.core.TSysStaff;
import com.cms.core.manager.admin.core.foundcms.BusiTestexerciseManager;
import com.cms.core.manager.admin.core.foundcms.BusiTestpaperManager;
import com.cms.util.Constants;
import com.cms.util.DateUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 浩 on 2017/9/17.
 */
@Controller
@Scope("prototype")
@RequestMapping("/view/admin/testpaper")
public class TestPaperControl {
    @Autowired
    private BusiTestpaperManager busiTestpaperManager;
    @Autowired
    private BusiTestexerciseManager busiTestexerciseManager;


    @RequestMapping("/getTestpaperListPage")
    public ModelAndView getTestpaperListPage(TestPaperBean bean){
        ModelAndView view = new ModelAndView("/admin/testpaper/list");
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/getTestpaperListData")
    @ResponseBody
    public Map<String,Object> getTestpaperListData(TestPaperBean bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        bean.setPageFlag(1);
        List<TBusiTestpaper> list = busiTestpaperManager.getBusiTestpaperList(bean);
        result.put("code", list.get(0).getResult());
        result.put("result", list);
        result.put("failinfo", list.get(0).getFailinfo());
        return result;
    }


    @RequestMapping("/updateTestpaperListPage")
    public ModelAndView updateTestpaperListPage(TestPaperBean bean){
        ModelAndView view = new ModelAndView("/admin/testpaper/update");
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/addTestpaperListPage")
    public ModelAndView addTestpaperListPage(TestPaperBean bean){
        ModelAndView view = new ModelAndView("/admin/testpaper/add");
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/addData")
    @ResponseBody
    public  Map<String,Object>  addData(HttpServletRequest request, TestPaperBean bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        TSysStaff tSysStaff = (TSysStaff) request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        bean.setUserid(tSysStaff.getUserid());
        bean.setScoringstandard(changeToJson(bean));
        TBusiTestpaper tBusiTestpaper = busiTestpaperManager.addBusiTestpaper(bean);
        result.put("code", tBusiTestpaper.getResult());
        result.put("failinfo", tBusiTestpaper.getFailinfo());
        return result;
    }

    @RequestMapping("/getTestExecrisesListPage")
    public ModelAndView getTestExecrisesListPage(TestPaperBean bean){
        ModelAndView view = new ModelAndView("/admin/testpaper/execrisesList");
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/addExercisesPage")
    public ModelAndView addExercisesPage(TestPaperBean bean){
        ModelAndView view = new ModelAndView("/admin/testpaper/addexecrises");
        view.addObject("bean", bean);
        return view;
    }




    @RequestMapping("/getExercisesListData")
    @ResponseBody
    public Map<String,Object> getExercisesListData(TestPaperBean bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        bean.setPageFlag(1);
        List<TBusiExercises> list = busiTestpaperManager.getExercises(bean);
        result.put("code", list.get(0).getResult());
        result.put("result", list);
        result.put("failinfo", list.get(0).getFailinfo());
        return result;
    }

    @RequestMapping("/getExercisesList")
    @ResponseBody
    public Map<String,Object> getExercisesList(TestPaperBean bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        bean.setPageFlag(1);
        List<TBusiExercises> list = busiTestpaperManager.getNoexercises(bean);
        result.put("code", list.get(0).getResult());
        result.put("result", list);
        result.put("failinfo", list.get(0).getFailinfo());
        return result;
    }





    //

    private String changeToJson(TestPaperBean bean){
        String strJson = "";
        try {
            JSONObject level1 = new JSONObject();
            level1.put("min",bean.getLeveoneMin());
            level1.put("max",bean.getLeveoneMax());

            JSONObject level2 = new JSONObject();
            level2.put("min",bean.getLevetwoMin());
            level2.put("max",bean.getLevetwoMax());

            JSONObject level3 = new JSONObject();
            level3.put("min",bean.getLevelthreeMin());
            level3.put("max",bean.getLevelthreeMax());

            JSONObject level4 = new JSONObject();
            level4.put("min",bean.getLevelfourMin());
            level4.put("max",bean.getLevelfourMax());

            JSONObject level5 = new JSONObject();
            level5.put("min",bean.getLevelfiveMin());
            level5.put("max",bean.getLevelfiveMax());

            JSONObject json = new JSONObject();
            json.put("level1",level1);
            json.put("level2",level2);
            json.put("level3",level3);
            json.put("level4",level4);
            json.put("level5",level5);

            strJson = json.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return strJson;

    }

    @RequestMapping("/updateData")
    @ResponseBody
    public Map<String,Object> updateData(HttpServletRequest request,TestPaperBean bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        bean.setSavetime(DateUtil.convertString2Date(bean.getSavetimestr(), DateUtil.DATE_FORMAT));
        TSysStaff tSysStaff = (TSysStaff) request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        bean.setUserid(tSysStaff.getUserid());
        TBusiTestpaper tBusiTestpaper = busiTestpaperManager.updateBusiTestpaper(bean);

        result.put("code", tBusiTestpaper.getResult());
        result.put("failinfo", tBusiTestpaper.getFailinfo());
        return result;
    }


    @RequestMapping("/delData")
    @ResponseBody
    public Map<String,Object> delData(TestPaperBean bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        TBusiTestpaper tBusiTestpaper = busiTestpaperManager.deleteBusiTestpaper(bean);
        result.put("code", tBusiTestpaper.getResult());
        result.put("failinfo", tBusiTestpaper.getFailinfo());
        return result;
    }

    @RequestMapping("/delFrompaper")
    @ResponseBody
    public Map<String,Object> delFrompaper(HttpServletRequest request,TBusiTestexercise bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        TBusiTestexercise tBusiTestexercise = busiTestexerciseManager.deleteBusiTestexercise(bean);
        result.put("code", tBusiTestexercise.getResult());
        result.put("failinfo", tBusiTestexercise.getFailinfo());
        return result;
    }

    @RequestMapping("/addTopaper")
    @ResponseBody
    public Map<String,Object> addTopaper(HttpServletRequest request,TBusiTestexercise bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        TSysStaff tSysStaff = (TSysStaff) request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        bean.setUserid(tSysStaff.getUserid());
        TBusiTestexercise tBusiTestexercise = busiTestexerciseManager.addBusiTestexercise(bean);
        result.put("code", tBusiTestexercise.getResult());
        result.put("failinfo", tBusiTestexercise.getFailinfo());
        return result;
    }

}
