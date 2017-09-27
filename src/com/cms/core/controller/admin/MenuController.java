package com.cms.core.controller.admin;

import com.cms.core.bean.cms.busi.MenuBean;
import com.cms.core.bean.cms.core.TSysMenu;
import com.cms.core.bean.cms.core.TSysStaff;
import com.cms.core.manager.admin.busi.AccountManager;
import com.cms.util.Constants;
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
 * 登录
 */
@Controller
@Scope("prototype")
@RequestMapping("/view")
public class MenuController {

    @Autowired
    private AccountManager accountManager;



    @RequestMapping("/getMenuData")
    @ResponseBody
    public Map<String,Object> getMenuData(HttpServletRequest request){
        TSysStaff usersBean =  (TSysStaff)request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        TSysStaff tSysStaff = new TSysStaff();
        tSysStaff.setRoleid(usersBean.getRoleid());
        List<TSysMenu> list = accountManager.getRoleMenuByRoleids(tSysStaff);
        List<MenuBean> fmenus = new ArrayList<>();
        List<MenuBean> smenus = new ArrayList<>();
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
            }else {
                MenuBean noresult = new MenuBean();
                noresult.setResult(2);
                sonMenuList.add(noresult);
            }
            fmenu.setSonMenus(sonMenuList);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("code",list.get(0).getResult());
        map.put("result",fmenus);
        return map;
    }
}
