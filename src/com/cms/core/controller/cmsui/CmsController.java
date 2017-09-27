package com.cms.core.controller.cmsui;

import com.cms.core.bean.cms.busi.MenuBean;
import com.cms.core.bean.cms.busi.PconsultationBean;
import com.cms.core.bean.cms.busi.PmultiplemsgBean;
import com.cms.core.bean.cms.core.*;
import com.cms.core.manager.admin.busi.AccountManager;
import com.cms.core.manager.admin.core.foundcms.*;
import com.cms.core.manager.admin.core.system.SysMenuManager;
import com.cms.util.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录
 */
@Controller
@Scope("prototype")
@RequestMapping("/cms")
public class CmsController {

    @Autowired
    private SysMenuManager sysMenuManager;

    @Autowired
    private BusiVipManager busiVipManager;

    @Autowired
    private BusiPmultiplemsgManager pmultiplemsgManager;

    @Autowired
    private BusiPnesManager busiPnesManager;

    @Autowired
    private BusiPnoticeManager busiPnoticeManager;

    @Autowired
    private BusiPproductManager busiPproductManager;

    @Autowired
    private BusiIndustryinformationManager busiIndustryinformationManager;
    @Autowired
    private BusiCserrviceqqManager busiCserrviceqqManager;

    @RequestMapping("/fwindex")
    @ResponseBody
    public ModelAndView forwardMenuPage(HttpServletRequest request){
        TSysMenu ts = new TSysMenu();
        ts.setMtype(1);
        List<TSysMenu> list = sysMenuManager.getSysMenuList(ts);
        List<MenuBean> fmenus = new ArrayList<>();
        List<MenuBean> smenus = new ArrayList<>();
        Map<Integer,List<MenuBean>> map = new HashMap<>();
        for (TSysMenu menu:list){
            if (menu.getNodelevel() == 1){
                MenuBean newmenu = new MenuBean();
                BeanUtils.copyProperties(menu,newmenu);
                fmenus.add(newmenu);
            }else if (menu.getNodelevel() == 2){
                MenuBean newmenu = new MenuBean();
                BeanUtils.copyProperties(menu,newmenu);
                newmenu.setResult(0);
                smenus.add(newmenu);
            }
        }

        for (MenuBean fmenu:fmenus){
            List<MenuBean> sonMenuList = new ArrayList<>();
            String ftwoCode = fmenu.getNodecode().substring(0,2);
            for (MenuBean smenu:smenus){
                String stwocode = smenu.getNodecode().substring(0,2);
                if (ftwoCode.equals(stwocode)){
                    sonMenuList.add(smenu);
                }
            }
            if (sonMenuList.size()>0) {
                sonMenuList.sort((MenuBean m1, MenuBean m2) -> {
                    if (m1.getNodeid() > m2.getNodeid()) {
                        return 1;
                    } else {
                        return -1;
                    }
                });
            }
            fmenu.setSonMenus(sonMenuList);
            List<MenuBean> news = new ArrayList<>();
            news.add(fmenu);
            news.addAll(sonMenuList);
            map.put(fmenu.getNavigationid1(),news);
        }
        request.getSession().setAttribute("navMenuList",map);
        ModelAndView view = new ModelAndView("/newglobal");

        List<String> listpic = new ArrayList<>();
        String imageuploadpath =  ResourceUtil.getSystem("image.upload.path") + "1" + File.separator+ "0" + File.separator;
        File file = new File(imageuploadpath);
        if(file.isDirectory() && file.exists()){
            File[] fs = file.listFiles();
            for(File f:fs){
                listpic.add(f.getName().replace(".jpg",""));
            }
        }

        TBusiPmultiplemsg bean = new TBusiPmultiplemsg();
        List<TBusiPmultiplemsg>	banner = pmultiplemsgManager.getBusiPmultiplemsgList4cms(bean);
        request.getSession().setAttribute("banner",banner);
        view.addObject("list",listpic);
        view.addObject("code",list.get(0).getResult());
        request.getSession().setAttribute("result",fmenus);

        List<TBusiPnes>	hotPnesList = busiPnesManager.getBusiPnesList4hot(new TBusiPnes());
        List<TBusiPnotice>	hotPnoticeList = busiPnoticeManager.getBusiPnoticeList4hot(new TBusiPnotice());
        List<TBusiPproduct>	hotPproductList = busiPproductManager.getBusiPproductList4hot(new TBusiPproduct());
        view.addObject("hotPnesList",hotPnesList);
        view.addObject("hotPnoticeList",hotPnoticeList);
        view.addObject("hotPproductList",hotPproductList);
        return  view;
    }

    @RequestMapping(value = "/getImage")
    public void getImage(String mesgid,HttpServletResponse response) {
        FileInputStream fis = null;
        response.setContentType("image/gif");
        try {
            OutputStream out = response.getOutputStream();
            String imageuploadpath = ResourceUtil.getSystem("image.upload.path") + "1" + File.separator+ "0" + File.separator + mesgid + ".jpg";
            File file = new File(imageuploadpath);
            if(!file.exists()){
                file = new File(getClass().getResource("/").toString().replace("WEB-INF/classes/","image").replace("file:/","")+File.separator+"no_image.gif");
            }
            fis = new FileInputStream(file);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            out.write(b);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @RequestMapping(value = "/login")
    @ResponseBody
    public Object login(@ModelAttribute TBusiVip users,HttpServletRequest request){
        TBusiVip tSysStaff = busiVipManager.getBusiVip(users);
        Map<String,Object> result = new HashMap<>();
        if(tSysStaff.getResult()==0){
            if(tSysStaff.getUserpassword().equals(users.getUserpassword())){
                request.getSession().setAttribute(Constants.CMS_SESSION_INFO, tSysStaff);
                result.put("code", ResultInfo.Success);
            }else {
                result.put("code",ResultInfo.NullOutput);
                result.put("failinfo", ResultInfo.USER_PWD_ERROR);
            }
        }else {
            result.put("code",ResultInfo.NullOutput);
            result.put("failinfo", ResultInfo.USER_PWD_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/register")
    @ResponseBody
    public Object register(@ModelAttribute TBusiVip users,HttpServletRequest request){
        TBusiVip tSysStaff = busiVipManager.addBusiVip(users);
        Map<String,Object> result = new HashMap<>();
        if(tSysStaff.getResult()==0){
            request.getSession().setAttribute(Constants.CMS_SESSION_INFO, tSysStaff);
            result.put("code", ResultInfo.Success);
        }else {
            result.put("code",ResultInfo.NullOutput);
            result.put("failinfo", ResultInfo.USER_PWD_ERROR);
        }
        result.put("data",users);
        return result;
    }

    @RequestMapping("/search")
    @ResponseBody
    public ModelAndView search(HttpServletRequest request){
        ModelAndView view = new ModelAndView("/searchresult");
        view.addObject("mtags",request.getParameter("mtags"));
        return view;
    }

    @RequestMapping("/getSearchResultData")
    @ResponseBody
    public Map<String,Object> getSearchResultData(TBusiPmultiplemsg bean){
        HashMap<String, Object> result = new HashMap<>();
        bean.setPageFlag(1);
        List<TBusiPmultiplemsg>	list = pmultiplemsgManager.getBusiPmultiplemsgList4search(bean);
        result.put("result", list);
        result.put("code", list.get(0).getResult());
        result.put("failinfo", list.get(0).getFailinfo());
        return result;
    }


    @RequestMapping("/linkus")
    @ResponseBody
    public Map<String,Object> linkus(HttpServletRequest request){
        HashMap<String, Object> result = new HashMap<>();
        TBusiPmultiplemsg bean = new TBusiPmultiplemsg();
        bean.setMtype(2);
        List<TBusiPmultiplemsg>	list = pmultiplemsgManager.getBusiPmultiplemsgList(bean);
        result.put("data", list);
        result.put("code", list.get(0).getResult());
        result.put("failinfo", list.get(0).getFailinfo());
        return result;
    }

    @RequestMapping("/getLinkusDetail")
    @ResponseBody
    public Map<String,Object> getLinkusDetail(TBusiPmultiplemsg bean){
        HashMap<String, Object> result = new HashMap<>();
        bean.setPageFlag(1);
        TBusiPmultiplemsg	tBusiPmultiplemsg = pmultiplemsgManager.getBusiPmultiplemsg(bean);
        result.put("data", tBusiPmultiplemsg);
        result.put("code", tBusiPmultiplemsg.getResult());
        result.put("failinfo", tBusiPmultiplemsg.getFailinfo());
        return result;
    }

    @RequestMapping("/managerTeamList")
    @ResponseBody
    public Map<String,Object> managerTeamList(HttpServletRequest request){
        HashMap<String, Object> result = new HashMap<>();
        TBusiPmultiplemsg bean = new TBusiPmultiplemsg();
        bean.setPageFlag(1);
        bean.setMtype(1);
        List<TBusiPmultiplemsg>	list = pmultiplemsgManager.getBusiPmultiplemsgList(bean);
        result.put("data", list);
        result.put("code", list.get(0).getResult());
        result.put("failinfo", list.get(0).getFailinfo());
        return result;
    }

    @RequestMapping("/managerTeamDetail")
    @ResponseBody
    public Map<String,Object> managerTeamDetail(TBusiPmultiplemsg bean){
        HashMap<String, Object> result = new HashMap<>();
        bean.setPageFlag(1);
        TBusiPmultiplemsg	tBusiPmultiplemsg = pmultiplemsgManager.getBusiPmultiplemsg(bean);
        result.put("data", tBusiPmultiplemsg);
        result.put("code", tBusiPmultiplemsg.getResult());
        result.put("failinfo", tBusiPmultiplemsg.getFailinfo());
        return result;
    }

    @RequestMapping("/companyNewsList")
    @ResponseBody
    public Map<String,Object> companyNewsList(HttpServletRequest request){
        HashMap<String, Object> result = new HashMap<>();
        TBusiPnes bean = new TBusiPnes();
        bean.setPageFlag(1);
        List<TBusiPnes>	list = busiPnesManager.getBusiPnesList(bean);
        result.put("data", list);
        result.put("code", list.get(0).getResult());
        result.put("failinfo", list.get(0).getFailinfo());
        return result;
    }

    @RequestMapping("/companyNewsDetail")
    @ResponseBody
    public Map<String,Object> companyNewsDetail(TBusiPmultiplemsg bean){
        HashMap<String, Object> result = new HashMap<>();
        bean.setPageFlag(1);
        TBusiPmultiplemsg	tBusiPmultiplemsg = pmultiplemsgManager.getBusiPmultiplemsg(bean);
        result.put("data", tBusiPmultiplemsg);
        result.put("code", tBusiPmultiplemsg.getResult());
        result.put("failinfo", tBusiPmultiplemsg.getFailinfo());
        return result;
    }

    @RequestMapping("/industrynewsList")
    @ResponseBody
    public Map<String,Object> industrynewsList(HttpServletRequest request){
        HashMap<String, Object> result = new HashMap<>();
        TBusiIndustryinformation bean = new TBusiIndustryinformation();
        bean.setPageFlag(1);
        bean.setMtype(1);
        List<TBusiIndustryinformation>	list = busiIndustryinformationManager.getBusiIndustryinformationList(bean);
        result.put("data", list);
        result.put("code", list.get(0).getResult());
        result.put("failinfo", list.get(0).getFailinfo());
        return result;
    }

    @RequestMapping("/industrynewsDetail")
    @ResponseBody
    public Map<String,Object> industrynewsDetail(TBusiIndustryinformation bean){
        HashMap<String, Object> result = new HashMap<>();
        bean.setPageFlag(1);
        TBusiIndustryinformation	tBusiIndustryinformation = busiIndustryinformationManager.getBusiIndustryinformation(bean);
        result.put("data", tBusiIndustryinformation);
        result.put("code", tBusiIndustryinformation.getResult());
        result.put("failinfo", tBusiIndustryinformation.getFailinfo());
        return result;
    }

    @RequestMapping("/industrytendencyList")
    @ResponseBody
    public Map<String,Object> industrytendencyList(HttpServletRequest request){
        HashMap<String, Object> result = new HashMap<>();
        TBusiIndustryinformation bean = new TBusiIndustryinformation();
        bean.setPageFlag(1);
        bean.setMtype(2);
        List<TBusiIndustryinformation>	list = busiIndustryinformationManager.getBusiIndustryinformationList(bean);
        result.put("data", list);
        result.put("code", list.get(0).getResult());
        result.put("failinfo", list.get(0).getFailinfo());
        return result;
    }

    @RequestMapping("/industrytendencyDetail")
    @ResponseBody
    public Map<String,Object> industrytendencyDetail(TBusiIndustryinformation bean){

        HashMap<String, Object> result = new HashMap<>();
        bean.setPageFlag(1);
        TBusiIndustryinformation tBusiIndustryinformation = busiIndustryinformationManager.getBusiIndustryinformation(bean);
        result.put("data", tBusiIndustryinformation);
        result.put("code", tBusiIndustryinformation.getResult());
        result.put("failinfo", tBusiIndustryinformation.getFailinfo());
        return result;
    }

    @RequestMapping("/serviceqq")
    @ResponseBody
    public Map<String,Object> serviceqq(HttpServletRequest request){
        HashMap<String, Object> result = new HashMap<>();
        TBusiCserrviceqq  bean = new TBusiCserrviceqq();
        bean.setPageFlag(1);
        List<TBusiCserrviceqq>	list = busiCserrviceqqManager.getBusiCserrviceqqList(bean);
        result.put("data", list);
        result.put("code", list.get(0).getResult());
        result.put("failinfo", list.get(0).getFailinfo());
        return result;
    }

}
