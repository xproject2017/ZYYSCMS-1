package com.cms.core.controller.admin.index;

import com.cms.core.bean.cms.busi.*;
import com.cms.core.bean.cms.core.*;
import com.cms.core.manager.admin.busi.AccountManager;
import com.cms.core.manager.admin.core.foundcms.*;
import com.cms.core.manager.admin.core.system.SysMenuManager;
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
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

/**
 * 首页设置
 */
@Controller
@Scope("prototype")
@RequestMapping("/view/admin/index")
public class IndexController {
    @Autowired
    private SysMenuManager sysMenuManager;

    @Autowired
    private BusiNavigationid1Manager navManager;

    @Autowired
    private BusiPmultiplemsgManager pmultiplemsgManager;

    @Autowired
    private BusiPnesManager busiPnesManager;

    @Autowired
    private BusiPnoticeManager busiPnoticeManager;

    @Autowired
    private BusiPproductManager busiPproductManager;//(9,11)

    @Autowired
    private BusiPquestionManager busiPquestionManager;//(12)

    @Autowired
    private BusiRecruitManager busiRecruitManager;//(15)

    @Autowired
    private AccountManager accountManager;

    @RequestMapping("/forwardMainPage")
    public ModelAndView forwardMenuPage(HttpServletRequest request){
        TSysStaff usersBean =  (TSysStaff)request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        TSysStaff tSysStaff = new TSysStaff();
        tSysStaff.setRoleid(usersBean.getRoleid());
        List<TSysMenu> list = accountManager.getRoleMenuByRoleids(tSysStaff);
        Map<String,String> map = new HashMap<>();
        if(list.get(0).getResult()==0){
            for(TSysMenu menu : list){
                if(menu.getMtype()==0 && menu.getNodelevel() ==1){
                    map.put(menu.getNodecode(),menu.getNodetext());
                }
            }
        }
        ModelAndView view = new ModelAndView("/admin/welcome");

        view.addObject("level",map);
        return  view;
    }

    /**
     * 获取国检账户列表页面
     * @return 页面
     */
    @RequestMapping("/getCooperationListPage")
    public ModelAndView getBespkeListPage(IndexBean bean){
        ModelAndView view = new ModelAndView("/admin/index/cooperationlist");
        List<String> list = new ArrayList<>();
        String imageuploadpath =  ResourceUtil.getSystem("image.upload.path") + bean.getNavigationid1()+ File.separator+bean.getNavigationid2() + File.separator;
        File file = new File(imageuploadpath);
        if(file.isDirectory() && file.exists()){
            File[] fs = file.listFiles();
            for(File f:fs){
                list.add(f.getName().replace(".jpg",""));
            }
        }
        view.addObject("list",list);
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/getMenuConfigPage")
    public ModelAndView getMenuConfigPage(IndexBean bean){
        ModelAndView view = new ModelAndView("/admin/index/menudetail");
        view.addObject("MenuList",getMenuList());
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/getMenuConfigListPage")
    public ModelAndView getMenuConfigListPage(IndexBean bean){
        ModelAndView view = new ModelAndView("/admin/index/menuList");
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/updateMenuConfigPage")
    public ModelAndView updateMenuConfigPage(IndexBean bean){
        ModelAndView view = new ModelAndView("/admin/index/menuList");
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/getMenuListData")
    @ResponseBody
    public Map<String,Object> getMenuListData(IndexBean bean){
        Map<String,Object> result = new HashMap<>();
        TSysMenu param = new TSysMenu();
        param.setPageFlag(0);
        param.setMtype(0);
        List<TSysMenu> allMenus = sysMenuManager.getSysMenuList(param);//全量菜单

        result.put("code",allMenus.get(0).getResult());
        result.put("result", allMenus);
        result.put("failinfo",allMenus.get(0).getFailinfo());
        return result;
    }




    @RequestMapping("/getUpdateMenuConfigPage")
    public ModelAndView getUpdateMenuConfigPage(TSysMenu bean){
        ModelAndView view = new ModelAndView("/admin/index/update");

        TSysMenu param  = sysMenuManager.getSysMenu(bean);//全量菜单
        view.addObject("failinfo",param.getFailinfo());
        view.addObject("code", param.getResult());
        view.addObject("bean", param);
        return view;
    }

    @RequestMapping("/updateData")
    @ResponseBody
    public Map<String,Object> updateData(HttpServletRequest request,TSysMenu bean){
        HashMap<String, Object> result = new HashMap<String, Object>();
        TSysMenu tSysMenu = sysMenuManager.updateSysMenu(bean);
        result.put("code", tSysMenu.getResult());
        result.put("failinfo", tSysMenu.getFailinfo());
        return result;
    }

    @RequestMapping("/updateMenuData")
    @ResponseBody
    public Map<String,Object> updateMenuData(String[] nodetext){
        Map<String,Object> result = new HashMap<>();
        Navigationid bean = new Navigationid();
        List<TBusiNavigationid1> busiNavigationid1 = new ArrayList<>();
        List<TBusiNavigationid2> busiNavigationid2 = new ArrayList<>();
        if(null!=nodetext){
            for(String text : nodetext){
                if(text.split(",")[2].equals("0")){
                    TBusiNavigationid1 b1 = new TBusiNavigationid1();
                    b1.setNavigationid1(Integer.parseInt(text.split(",")[1]));
                    b1.setNavigationname1(text.split(",")[0]);
                    busiNavigationid1.add(b1);
                }else{
                    TBusiNavigationid2 b2 = new TBusiNavigationid2();
                    b2.setNavigationid1(Integer.parseInt(text.split(",")[1]));
                    b2.setNavigationid2(Integer.parseInt(text.split(",")[2]));
                    b2.setNavigationname2(text.split(",")[0]);
                    busiNavigationid2.add(b2);
                }
            }
        }
        bean.setBusiNavigationid1(busiNavigationid1);
        bean.setBusiNavigationid2(busiNavigationid2);
        TBusiNavigationid1 nid = navManager.updateBusiNavigationid(bean);
        result.put("code",nid.getResult());
        result.put("failinfo",nid.getFailinfo());
        return result;
    }

    private List<MenuBean> getMenuList(){
        TSysMenu param = new TSysMenu();
        param.setPageFlag(0);
        param.setMtype(0);
        List<TSysMenu> allMenus = sysMenuManager.getSysMenuList(param);//全量菜单
        Map<String,TSysMenu> parent = new HashMap<>();//所有父级菜单，去重的
        for(TSysMenu menu : allMenus){
            if(!parent.containsKey(menu.getNodecode().substring(0,2)))
                parent.put(menu.getNodecode().substring(0,2), menu);
        }

        List<MenuBean> fmenus = new ArrayList<>();//父级菜单列表
        for (String key:parent.keySet()){
            if(key.equals("01") || key.equals("06")){
                continue;
            }
            MenuBean fmenu = new MenuBean();
            fmenu.setNavigationid1(parent.get(key).getNavigationid1());
            fmenu.setNavigationid2(parent.get(key).getNavigationid2());
            fmenu.setNodeid(parent.get(key).getNodeid());
            fmenu.setNodetext(parent.get(key).getNodetext());
            List<MenuBean> smenus = new ArrayList<>();
            for (TSysMenu son : allMenus) {
                MenuBean smenu = new MenuBean();
                BeanUtils.copyProperties(son, smenu);
                smenu.setResult(0);
                if (son.getNodecode().substring(0,2).equals(parent.get(key).getNodecode().substring(0,2))  && son.getNodelevel() !=1){
                    smenus.add(smenu);
                }
            }
            if (smenus.size() > 0) {
                smenus.sort((MenuBean m1, MenuBean m2) -> {
                    if (m1.getNodeid() > m2.getNodeid()) {
                        return 1;
                    } else {
                        return -1;
                    }
                });
            }else {
                MenuBean smenu = new MenuBean();
                smenu.setResult(2);
                smenus.add(smenu);
            }
            fmenu.setSonMenus(smenus);
            fmenus.add(fmenu);
        }
        return fmenus;
    }


    @RequestMapping("/upload")
    @ResponseBody
    public Map<String,Object> upload(IndexBean bean){
        HashMap<String, Object> result = new HashMap<>();
        String filename = new Date().getTime() + "";
        try {
            String imageuploadpath =  ResourceUtil.getSystem("image.upload.path") + bean.getNavigationid1()+File.separator+bean.getNavigationid2();
            File dir = new File(imageuploadpath);
            if (!dir.exists())
                dir.mkdirs();
            File fos = new File(imageuploadpath + File.separator + filename + ".jpg");
            BufferedImage bIMG = ImageIO.read(bean.getFile().getInputStream());
            Thumbnails.of(bean.getFile().getInputStream()).size(bIMG.getWidth(), bIMG.getHeight()).outputFormat("jpg").toFile(fos);
        } catch (Exception e) {
            result.put("code","1");
            result.put("failinfo","上传失败");
            return result;
        }
        result.put("code","0");
        result.put("filename",filename);
        return result;
    }

    @RequestMapping("/delimage")
    @ResponseBody
    public Map<String,Object> delimage(HttpServletRequest request, HttpServletResponse response, Integer navigationid1, Integer navigationid2, String ids){
        HashMap<String, Object> result = new HashMap<>();
        response.setCharacterEncoding("UTF-8");
        try {
            if(null!=ids && (!"".equals(ids))){
                String imageuploadpath =  ResourceUtil.getSystem("image.upload.path") + navigationid1 +File.separator+navigationid2;
                File dir = new File(imageuploadpath);
                if (dir.exists()){
                    String fids[] = ids.split(",");
                    for(String id : fids){
                        if(null!=id && (!"".equals(ids))){
                            File fos = new File(imageuploadpath + File.separator + id + ".jpg");
                            fos.delete();
                        }
                    }
                }
            }
        } catch (Exception e) {
            result.put("code","1");
            result.put("failinfo","删除失败");
            return result;
        }
        result.put("code","0");
        return result;
    }

    @RequestMapping("/getBannerConfigPage")
    public ModelAndView getBannerConfigPage(HttpServletRequest request, HttpServletResponse response, IndexBean bean){
        response.setCharacterEncoding("UTF-8");
        ModelAndView view = new ModelAndView("/admin/index/bannerconfig");
        TBusiPmultiplemsg msg = new TBusiPmultiplemsg();
        msg.setPageFlag(0);
        List<TBusiPmultiplemsg>	list = pmultiplemsgManager.getBusiPmultiplemsgList4admin(msg);
        if(list.get(0).getResult()==0){
            for(TBusiPmultiplemsg mult : list){
                PmultiplemsgBean msgbean = new PmultiplemsgBean();
                mult.setMbannerstr(mult.getMbanner() == 0 ? "否" : "是");
                TSysMenu param = new TSysMenu();
                param.setPageFlag(0);
                param.setMtype(0);
                param.setNavigationid1(mult.getNavigationid1());
                param.setNavigationid2(mult.getNavigationid2());
                TSysMenu allMenus = sysMenuManager.getSysMenu(param);//全量菜单

                param = new TSysMenu();
                param.setPageFlag(0);
                param.setMtype(0);
                param.setNavigationid1(mult.getNavigationid1());
                param.setNavigationid2(0);
                TSysMenu men = sysMenuManager.getSysMenu(param);//全量菜单
                mult.setNodetext(men.getNodetext()+"("+allMenus.getNodetext()+")");
                BeanUtils.copyProperties(mult,msgbean);
                mult.setSavetimestr(mult.getSavetime().getTime()+"");
            }
        }
        view.addObject("list",list);
        view.addObject("bean", bean);
        return view;
    }

    @RequestMapping("/setbanner")
    @ResponseBody
    public Object setBanner(HttpServletRequest request, HttpServletResponse response, PmultiplemsgBean menu){
        response.setCharacterEncoding("UTF-8");
        HashMap<String, Object> result = new HashMap<>();
        if(menu.getNavigationid2()==1 || menu.getNavigationid2()==2 || menu.getNavigationid2()==3 || menu.getNavigationid2()==4 || menu.getNavigationid2()==14){
            PmultiplemsgBean bean = new PmultiplemsgBean();
            bean.setNavigationid1(menu.getNavigationid1());
            bean.setNavigationid2(menu.getNavigationid2());
            bean.setMesgid(menu.getMesgid());
            bean.setMbanner(menu.getMbanner());
            bean.setSavetime(new Date(Long.valueOf(menu.getSavetimestr())));
            TBusiPmultiplemsg msg = pmultiplemsgManager.updateBusiPmultiplemsg(bean);
            result.put("result", msg);
            result.put("code", msg.getResult());
            result.put("failinfo", msg.getFailinfo());
        }else if(menu.getNavigationid2()==5 || menu.getNavigationid2()==7 || menu.getNavigationid2()==8){
            PnesBean bean = new PnesBean();
            bean.setMesgid(menu.getMesgid());
            bean.setMbanner(menu.getMbanner());
            bean.setNavigationid1(menu.getNavigationid1());
            bean.setNavigationid2(menu.getNavigationid2());
            bean.setSavetime(new Date(Long.valueOf(menu.getSavetimestr())));
            TBusiPnes msg = busiPnesManager.updateBusiPnes(bean);
            result.put("result", msg);
            result.put("code", msg.getResult());
            result.put("failinfo", msg.getFailinfo());
        }else if(menu.getNavigationid2()==6){
            TBusiPnotice bean = new TBusiPnotice();
            bean.setMesgid(menu.getMesgid());
            bean.setMbanner(menu.getMbanner());
            bean.setNavigationid1(menu.getNavigationid1());
            bean.setNavigationid2(menu.getNavigationid2());
            bean.setSavetime(new Date(Long.valueOf(menu.getSavetimestr())));
            TBusiPnotice msg = busiPnoticeManager.updateBusiPnotice(bean);
            result.put("result", msg);
            result.put("code", msg.getResult());
            result.put("failinfo", msg.getFailinfo());
        }else if(menu.getNavigationid2()==9 || menu.getNavigationid2()==11){
            TBusiPproduct bean = new TBusiPproduct();
            bean.setMesgid(menu.getMesgid());
            bean.setMbanner(menu.getMbanner());
            bean.setNavigationid1(menu.getNavigationid1());
            bean.setNavigationid2(menu.getNavigationid2());
            bean.setSavetime(new Date(Long.valueOf(menu.getSavetimestr())));
            TBusiPproduct msg = busiPproductManager.updateBusiPproduct(bean);
            result.put("result", msg);
            result.put("code", msg.getResult());
            result.put("failinfo", msg.getFailinfo());
        }else if(menu.getNavigationid2()==12){
            TBusiPquestion bean = new TBusiPquestion();
            bean.setMesgid(menu.getMesgid());
            bean.setMbanner(menu.getMbanner());
            bean.setNavigationid1(menu.getNavigationid1());
            bean.setNavigationid2(menu.getNavigationid2());
            bean.setSavetime(new Date(Long.valueOf(menu.getSavetimestr())));
            TBusiPquestion msg = busiPquestionManager.updateBusiPquestion(bean);
            result.put("result", msg);
            result.put("code", msg.getResult());
            result.put("failinfo", msg.getFailinfo());
        }else if(menu.getNavigationid2()==15){
            TBusiRecruit bean = new TBusiRecruit();
            bean.setMesgid(menu.getMesgid());
            bean.setMbanner(menu.getMbanner());
            bean.setNavigationid1(menu.getNavigationid1());
            bean.setNavigationid2(menu.getNavigationid2());
            bean.setSavetime(new Date(Long.valueOf(menu.getSavetimestr())));
            TBusiRecruit msg = busiRecruitManager.updateBusiRecruit(bean);
            result.put("result", msg);
            result.put("code", msg.getResult());
            result.put("failinfo", msg.getFailinfo());
        }
        return result;
    }
}
