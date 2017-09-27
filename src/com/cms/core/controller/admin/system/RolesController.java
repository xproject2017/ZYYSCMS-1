package com.cms.core.controller.admin.system;

import com.cms.core.bean.cms.busi.MenuBean;
import com.cms.core.bean.cms.core.RoleMenus;
import com.cms.core.bean.cms.core.TSysRole;
import com.cms.core.manager.admin.busi.AccountManager;
import com.cms.core.manager.admin.busi.RoleManager;
import com.cms.core.manager.admin.core.system.SysMenuManager;
import com.cms.core.bean.cms.core.TSysMenu;
import com.cms.core.bean.cms.core.TSysStaff;
import com.cms.core.manager.admin.core.system.SysRoleManager;
import com.cms.util.Constants;
import com.cms.util.ResultInfo;
import com.cms.util.SysFlagUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by zyy on 2016/3/21.
 * 国检账户
 */
@Controller
@Scope("prototype")
@RequestMapping("/view/admin/system")
public class RolesController {
    @Autowired
    private RoleManager roleManager;
    @Autowired
    private AccountManager accountManager;
    @Autowired
    private SysMenuManager sysMenuManager;
    @Autowired
    private SysRoleManager sysRoleManager;

    /**
     * 获取国角色列表页面
     * @return 页面
     */
    @RequestMapping("/getRolesListPage")
    public ModelAndView getRolesListPage(TSysRole retRole){
        ModelAndView view = new ModelAndView("/admin/roles/roleslist");
        view.addObject("bean", retRole);
        return view;
    }

    /**
     * 获取角色列表数据
     * @return
     */
    @RequestMapping("/getRolesListData")
    @ResponseBody
    public Map<String,Object> getRolesListData(HttpServletRequest request,RoleMenus bean){
        Map<String,Object> result = new HashMap<>();
        bean.setPageFlag(1);
        List<RoleMenus> retList = roleManager.getRoleList(bean);
        if (retList.get(0).getResult() == 0) {
            for (RoleMenus menus : retList) {
                TSysStaff param = new TSysStaff();
                param.setRoleid(menus.getRoleid());
                List<TSysMenu> retRoleMenus = accountManager.getRoleMenuByRoleids(param);//该账户角色对应的所有菜单
                StringBuffer menuNames = new StringBuffer();
                if (retRoleMenus.get(0).getResult() == 0) {
                    for (TSysMenu rolemenu : retRoleMenus) {
                        menuNames.append(rolemenu.getNodetext()).append("/");
                    }
                }
                menus.setMenuNames(menuNames.toString().replaceAll("/$", ""));
            }
        }
        result.put("code",retList.get(0).getResult());
        result.put("failinfo",retList.get(0).getFailinfo());
        result.put("result",retList);
        return result;
    }

    /**
     * 获取角色详情页面
     * 传入roleids
     * @return 页面
     */
    @RequestMapping("/getRolesDetailPage")
    public ModelAndView getRolesDetailPage(HttpServletRequest request,TSysStaff user){
        Map<String,Object> map = new HashMap<>();
        Integer roleid = user.getRoleid();
        TSysStaff currUser = (TSysStaff)request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        TSysRole params = new TSysRole();
        params.setRoleid(roleid);
        TSysRole retRole = sysRoleManager.getSysRole(params);
        List<TSysMenu> retRoleMenus = accountManager.getRoleMenuByRoleids(user);//该账户角色对应的所有菜单
        TSysMenu param = new TSysMenu();
        param.setPageFlag(0);
        param.setMtype(0);
        List<TSysMenu> allMenus = sysMenuManager.getSysMenuList(param);//全量菜单
        Map<String,TSysMenu> parent = new HashedMap();//所有父级菜单，去重的
        for(TSysMenu menu : allMenus){
            if(!parent.containsKey(menu.getNodecode().substring(0,2)))
                parent.put(menu.getNodecode().substring(0,2), menu);
        }

        List<MenuBean> fmenus = new ArrayList<>();//父级菜单列表
        for (String key:parent.keySet()){
            MenuBean fmenu = new MenuBean();
            fmenu.setNodeid(parent.get(key).getNodeid());
            fmenu.setNodetext(parent.get(key).getNodetext());
            List<MenuBean> smenus = new ArrayList<>();
            for (TSysMenu son:allMenus){
                MenuBean smenu = new MenuBean();
                BeanUtils.copyProperties(son,smenu);
                if (son.getNodecode().substring(0,2).equals(parent.get(key).getNodecode().substring(0,2))  && son.getNodelevel() !=1){
                    for (TSysMenu userMenu : retRoleMenus){
                        if (userMenu.getNodeid() == son.getNodeid()){
                            //如果该用户的菜单id与橘色拥有的菜单id相等，则勾选上
                            smenu.setChecked(true);
                        }
                    }
                    smenus.add(smenu);
                }
            }
            fmenu.setSonMenus(smenus);
            fmenus.add(fmenu);
        }
        boolean canModify = false;
        Integer currRoleid = currUser.getRoleid();
        if (currRoleid == SysFlagUtil.role_support){
            canModify = true;
        }else if (currRoleid == SysFlagUtil.role_admin && roleid != SysFlagUtil.role_admin){
            canModify = true;
        }else if (currRoleid == SysFlagUtil.role_executive && roleid != SysFlagUtil.role_admin && roleid != SysFlagUtil.role_executive){
            canModify = true;
        }
        map.put("MenuList",fmenus);
        map.put("Role",retRole);
        map.put("canModify",canModify);
        return new ModelAndView("/admin/roles/rolesdetails",map);
    }

    /**
     * 获取修改角色页面
     * 传入roleids
     * @return 页面
     */
    @RequestMapping("/getUpdateRolesPage")
    public ModelAndView getUpdateRolesPage(TSysStaff user){
        Map<String,Object> map = new HashMap<>();
        TSysRole params = new TSysRole();
        params.setRoleid(user.getRoleid());
        TSysRole retRole = sysRoleManager.getSysRole(params);
        List<TSysMenu> retRoleMenus = accountManager.getRoleMenuByRoleids(user);//该账户角色对应的所有菜单
        TSysMenu param = new TSysMenu();
        param.setPageFlag(0);
        param.setMtype(0);
        List<TSysMenu> allMenus = sysMenuManager.getSysMenuList(param);//全量菜单
        Map<String,TSysMenu> parent = new HashedMap();//所有父级菜单，去重的
        for(TSysMenu menu : allMenus){
            if(!parent.containsKey(menu.getNodecode().substring(0,2)))
                parent.put(menu.getNodecode().substring(0,2), menu);
        }

        List<MenuBean> fmenus = new ArrayList<>();//父级菜单列表
        for (String key:parent.keySet()){
            MenuBean fmenu = new MenuBean();
            fmenu.setNodeid(parent.get(key).getNodeid());
            fmenu.setNodetext(parent.get(key).getNodetext());
            List<MenuBean> smenus = new ArrayList<>();
            for (TSysMenu son:allMenus){
                MenuBean smenu = new MenuBean();
                BeanUtils.copyProperties(son,smenu);
                if (son.getNodecode().substring(0,2).equals(parent.get(key).getNodecode().substring(0,2))  && son.getNodelevel() !=1){
                    for (TSysMenu userMenu : retRoleMenus){
                        if (userMenu.getNodeid() == son.getNodeid()){
                            //如果该用户的菜单id与橘色拥有的菜单id相等，则勾选上
                            smenu.setChecked(true);
                        }
                    }
                    smenus.add(smenu);
                }
            }
            fmenu.setSonMenus(smenus);
            fmenus.add(fmenu);
        }
        map.put("MenuList",fmenus);
        map.put("Role",retRole);
        return new ModelAndView("/admin/roles/updateroles",map);
    }

    /**
     * 获取新增角色页面
     * @return 页面
     */
    @RequestMapping("/getAddRolesPage")
    public ModelAndView getAddRolesPag(){
        Map<String,Object> map = new HashMap<>();
        TSysMenu param = new TSysMenu();
        param.setPageFlag(0);
        param.setMtype(0);
        List<TSysMenu> allMenus = sysMenuManager.getSysMenuList(param);//全量菜单
        Map<String,TSysMenu> parent = new HashedMap();//所有父级菜单，去重的
        for(TSysMenu menu : allMenus){
            if(!parent.containsKey(menu.getNodecode().substring(0,2)))
                parent.put(menu.getNodecode().substring(0,2), menu);
        }

        List<MenuBean> fmenus = new ArrayList<>();//父级菜单列表
        for (String key:parent.keySet()){
            MenuBean fmenu = new MenuBean();
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
        map.put("MenuList",fmenus);
        return new ModelAndView("/admin/roles/addroles",map);
    }
    /**
     * 新增角色-权限信息
     * @param
     * @return
     */
    @RequestMapping("/addRole")
    @ResponseBody
    public Map<String, Object> addRole(RoleMenus bean){
        Map<String,Object> result = new HashMap<>();
        TSysRole param = new TSysRole();
        param.setRolename(bean.getRolename());
        TSysRole retRole = sysRoleManager.getSysRole(param);
        if (retRole.getResult() == 0){
            result.put("code",ResultInfo.ErrorRecordExist);
            result.put("failinfo", ResultInfo.ROLE_NAME_EXIST);
            return result;
        }
        TSysMenu menuparam = new TSysMenu();
        List<TSysMenu> allMenus = sysMenuManager.getSysMenuList(menuparam);
        List<TSysMenu> sonMenus = new ArrayList<>();
        List<TSysMenu> fatherMenus = new ArrayList<>();
        for (TSysMenu menu :allMenus){
            if (menu.getNodelevel() == 2) {
                sonMenus.add(menu);
            }else if (menu.getNodelevel() == 1){
                fatherMenus.add(menu);
            }
        }
        String[] menuids = bean.getMenuids().replaceAll(",$","").split(",");
        List<Integer> menuidList = new ArrayList<>();
        for (String mid :menuids){
            for (TSysMenu fmenu:fatherMenus){
                if (fmenu.getNodeid() == Integer.parseInt(mid)){
                    //Integer platNo = fmenu.getPlatformflag();
                    String twoPrefixValue = fmenu.getNodecode().substring(0,2);
                    for (TSysMenu smenu:sonMenus){
                        String sonTwoPrefixValue = smenu.getNodecode().substring(0,2);
//                        if (platNo==smenu.getPlatformflag()&&twoPrefixValue.equals(sonTwoPrefixValue)){
//                            menuidList.add(smenu.getNodeid());
//                        }
                    }
                }
            }
            menuidList.add(Integer.parseInt(mid));
        }
        bean.setMenuidList(menuidList);
        TSysRole retBean = roleManager.addRole(bean);
        result.put("code",retBean.getResult());
        result.put("failinfo",retBean.getFailinfo());
        return result;
    }

    /**
     * 修改角色-权限信息
     * @param
     * @return
     */
    @RequestMapping("/updateRole")
    @ResponseBody
    public Map<String, Object> updateRole(RoleMenus bean){
        Map<String,Object> result = new HashMap<>();
        TSysRole param = new TSysRole();
        param.setRolename(bean.getRolename());
        TSysRole retRole = sysRoleManager.getSysRole(param);
        if (retRole.getResult() == 0 && retRole.getRoleid() != bean.getRoleid()){
            result.put("code",3);
            result.put("failinfo", ResultInfo.ROLE_NAME_EXIST);
            return result;
        }
        String[] menuids = bean.getMenuids().replaceAll(",$","").split(",");
        List<Integer> menuidList = new ArrayList<>();
        for (String mid :menuids){
            menuidList.add(Integer.parseInt(mid));
        }
        bean.setMenuidList(menuidList);
        TSysRole retBean = roleManager.updateRole(bean);
        result.put("code",retBean.getResult());
        result.put("failinfo",retBean.getFailinfo());
        return result;
    }
}
