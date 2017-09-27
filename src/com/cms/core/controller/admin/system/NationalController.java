package com.cms.core.controller.admin.system;

import com.cms.core.bean.cms.core.*;
import com.cms.core.manager.admin.busi.AccountManager;
import com.cms.util.*;
import com.cms.core.bean.cms.busi.NationalBean;
import com.cms.core.manager.admin.core.system.SysRoleManager;
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
public class NationalController {

    private static final String DEFAULT_PASSWORD = "123456";

    @Autowired
    private AccountManager accountManager;
    @Autowired
    private SysRoleManager sysRoleManager;

    /**
     * 获取国检账户列表页面
     * @return 页面
     */
    @RequestMapping("/getNationalListPage")
    public ModelAndView getNationalListPage(NationalBean bean){
        ModelAndView view = new ModelAndView("/admin/national/nationallist");
        List<TSysRole> tSysRoleList = getRoleList();
        view.addObject("roleList", tSysRoleList);
        view.addObject("bean", bean);
        return view;
    }

    /**
     * 获取国检账户列表数据
     * @return
     */
    @RequestMapping("/getNationalListData")
    @ResponseBody
    public Map<String,Object> getNationalListData(HttpServletRequest request,NationalBean bean){
        return getNational(request,bean);
    }

    /**
     * 获取角色列表
     * @return
     */
    public List<TSysRole> getRoleList() {
        TSysRole role = new TSysRole();
        role.setPageFlag(0);
        return accountManager.getRoleList(role);
    }

    /**
     * 获取账户详情页面
     * 传入userid
     * @return 页面
     */
    @RequestMapping("/getNationalDetailPage")
    public ModelAndView getNationalDetailPage(HttpServletRequest request,TSysStaff user){
        Map<String,Object> map = new HashMap<>();
        NationalBean newUser = new NationalBean();
        TSysStaff retUser = accountManager.getAccountDetail(user);//账户信息
        BeanUtils.copyProperties(retUser,newUser);
        newUser.setSavedateTimeStr(DateUtil.convertDate2String(newUser.getSavetime(), DateUtil.DATE_FORMAT));
        TSysRole role = new TSysRole();
        role.setPageFlag(0);
        role.setRoleid(newUser.getRoleid());
        List<TSysRole> tSysRoleList = accountManager.getRoleList(role);
        if(null==newUser.getUsertelephone()){
            newUser.setUsertelephone("");
        }
        if(tSysRoleList.get(0).getResult()==0){
            newUser.setRolenames(tSysRoleList.get(0).getRolename());
        }else{
            newUser.setRolenames("");
        }
        if(newUser.getUstatus()==0){
            newUser.setUstatusStr("禁用");
        }else{
            newUser.setUstatusStr("启用");
        }
        //用户信息
        map.put("users",newUser);
        return new ModelAndView("/admin/national/nationaldetails",map);
    }

    /**
     * 获取添加账户页面
     * @return 页面
     */
    @RequestMapping("/getAddNationalPage")
    public ModelAndView getAddNationalPage(HttpServletRequest request){
        TSysStaff tSysStaff = (TSysStaff)request.getSession().getAttribute(Constants.USER_SESSION_INFO);

        Map<String,Object> map = new HashMap<>();
        TSysRole param = new TSysRole();
        param.setRoleid(tSysStaff.getRoleid());
        List<TSysRole> roleList = accountManager.getRoleListPriority(param);
        map.put("roleList",roleList);
        return new ModelAndView("/admin/national/addnational",map);
    }



    /**
     * 获取修改账户页面
     * @return 页面
     */
    @RequestMapping("/getUpdateNationalPage")
    public ModelAndView getUpdateNationalPage(TSysStaff user){
        Map<String,Object> map = new HashMap<>();
        //用户信息
        NationalBean newUser = new NationalBean();
        TSysStaff retUser = accountManager.getAccountDetail(user);//账户信息
        BeanUtils.copyProperties(retUser,newUser);
        newUser.setSavedateTimeStr(DateUtil.convertDate2String(newUser.getSavetime(),DateUtil.DATE_FORMAT));
        TSysRole role = new TSysRole();
        role.setPageFlag(0);
        List<TSysRole> tSysRoleList = accountManager.getRoleList(role);
        map.put("users", newUser);
        map.put("roleList",tSysRoleList);
        return new ModelAndView("/admin/national/updatenational",map);
    }

    /**
     * 新增账户_用户信息
     * @param nationalBean
     * @return
     */
    @RequestMapping("/addNationalUser")
    @ResponseBody
    public HashMap<String, Object> addNationalUser(HttpServletRequest request,NationalBean nationalBean) {
        TSysStaff currUser = (TSysStaff)request.getSession().getAttribute(Constants.USER_SESSION_INFO);
        nationalBean.setCuserid(((TSysStaff)request.getSession().getAttribute(Constants.USER_SESSION_INFO)).getUserid());
        HashMap<String, Object> resultHashMap = new HashMap<String, Object>();

        if (isStaffExist(nationalBean)) {
            resultHashMap.put("code", ResultInfo.RecordExist);
            resultHashMap.put("failinfo", "账户" + nationalBean.getUname() + "已存在");
            return resultHashMap;
        }


        TSysStaff tSysStaff = (TSysStaff)nationalBean;


        //将前端传来的roleids转换为数据库需要的roleids
        Integer roleIds_old = nationalBean.getRoleid();
        tSysStaff.setRoleid(roleIds_old);
        tSysStaff.setUserpassword(nationalBean.getUserpassword());//前端加密，防止被截取密码
        if (roleIds_old == SysFlagUtil.role_executive){
            tSysStaff.setPermissionflag(SysFlagUtil.USER_PERMISSION_WRITE);
        }else {
            tSysStaff.setPermissionflag(SysFlagUtil.USER_PERMISSION_READ);
        }
        TSysStaff account = accountManager.addAccount(tSysStaff);

        resultHashMap.put("code", account.getResult());
        resultHashMap.put("failinfo", account.getFailinfo());
        resultHashMap.put("result", account);
        return resultHashMap;
    }

    /**
     * 判断机构账户是否存在
     * @param nationalBean
     * @return
     */
    private boolean isStaffExist(NationalBean nationalBean) {
        TSysStaff tSysStaff = new TSysStaff();
        tSysStaff.setUname(nationalBean.getUname());
        List<TSysStaff> staffList = accountManager.getAccountList(tSysStaff);
        return staffList.get(0).getResult() == ResultInfo.Success;
    }



    /**
     * 修改账户_用户信息
     * @param nationalBean
     * @return
     */
    @RequestMapping("/updateNationalUser")
    @ResponseBody
    public HashMap<String, Object> updateNationalUser(NationalBean nationalBean) {
        HashMap<String, Object> resultHashMap = new HashMap<String, Object>();
        TSysStaff tSysStaff = (TSysStaff)nationalBean;
        tSysStaff.setUserpassword(null);
        try {
            tSysStaff.setSavetime(DateUtil.convertString2Date(nationalBean.getSavedateTimeStr(),DateUtil.DATE_FORMAT));
        } catch (Exception e) {
            e.printStackTrace();
        }


        //将前端传来的roleids转换为数据库需要的roleids
        Integer roleIds_old = nationalBean.getRoleid();
        tSysStaff.setRoleid(roleIds_old);
        TSysStaff account = accountManager.updateAccount(tSysStaff);
        resultHashMap.put("code", account.getResult());
        resultHashMap.put("failinfo", account.getFailinfo());
        resultHashMap.put("result", account);
        return resultHashMap;
    }


    /**
     * 根据userid获取权限列表
     * @param bean
     * @return
     */
    @RequestMapping("/getRoleMenuByUserid")
    @ResponseBody
    public HashMap<String, Object> getRoleMenuByUserid(TSysStaff bean) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        List<TSysMenu> tSysMenuList = accountManager.getRoleMenuByUserid(bean);
        map.put("code", tSysMenuList.get(0).getResult());
        map.put("failinfo", tSysMenuList.get(0).getFailinfo());
        map.put("result", tSysMenuList);

        return map;
    }

    /**
     * 根据roleids获取权限列表
     * @param bean
     * @return
     */
    @RequestMapping("/getRoleMenuByRoleids")
    @ResponseBody
    public HashMap<String, Object> getRoleMenuByRoleids(TSysStaff bean) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        List<TSysMenu> tSysMenuList = accountManager.getRoleMenuByRoleids(bean);
        map.put("code", tSysMenuList.get(0).getResult());
        map.put("failinfo", tSysMenuList.get(0).getFailinfo());
        map.put("result", tSysMenuList);

        return map;
    }



    /**
     * 搜索
     * @param bean
     * @return
     */
    @RequestMapping("/searchAccount")
    @ResponseBody
    public HashMap<String, Object> searchNational(HttpServletRequest request,NationalBean bean) {

        return getNational(request,bean);
    }

    /**
     * 公用方法，“账户列表页面”和“搜索功能”使用
     * @param bean
     * @return
     */
    private HashMap<String, Object> getNational(HttpServletRequest request,NationalBean bean) {

        HashMap<String, Object> result = new HashMap<String, Object>();
        bean.setPageFlag(1);
        List<TSysStaff> tSysStaffList = null;

        tSysStaffList = accountManager.getAccountListByUName(bean);
        List<NationalBean> nationalBeanList = new ArrayList<NationalBean>();

        //所有的角色清单
        if (tSysStaffList.get(0).getResult() == 0) {
            for (TSysStaff t : tSysStaffList) {
                NationalBean nationalBean = new NationalBean();
                BeanUtils.copyProperties(t, nationalBean);
                TSysRole role = new TSysRole();
                role.setPageFlag(0);
                role.setRoleid(nationalBean.getRoleid());
                List<TSysRole> tSysRoleList = accountManager.getRoleList(role);
                if(null==nationalBean.getUsertelephone()){
                    nationalBean.setUsertelephone("");
                }
                if(tSysRoleList.get(0).getResult()==0){
                    nationalBean.setRolenames(tSysRoleList.get(0).getRolename());
                }else{
                    nationalBean.setRolenames("");
                }
                nationalBeanList.add(nationalBean);
                if(nationalBean.getUstatus()==0){
                    nationalBean.setUstatusStr("禁用");
                }else{
                    nationalBean.setUstatusStr("启用");
                }
            }
        } else {
            result.put("code", tSysStaffList.get(0).getResult());
            result.put("failinfo", tSysStaffList.get(0).getFailinfo());

            return result;
        }

        for (NationalBean nationalBean : nationalBeanList) {
            if (nationalBean.getSavetime() != null) {
                nationalBean.setSavedateTimeStr(nationalBean.getSavetime().getTime() + "");
            }
        }


        result.put("code", tSysStaffList.get(0).getResult());
        result.put("failinfo", tSysStaffList.get(0).getFailinfo());
        result.put("result", nationalBeanList);

        return result;
    }
    /**
     * 重置密码 传userid，savedateTimeStr
     * @param bean
     * @return
     */
    @RequestMapping("/resetPwd")
    @ResponseBody
    public Map<String,Object> resetPwd(NationalBean bean){

        TSysStaff tSysStaff = new TSysStaff();
        tSysStaff.setUserid(bean.getUserid());

        try {
            if (bean.getSavedateTimeStr().trim().contains(" ")) {
                tSysStaff.setSavetime(DateUtil.convertString2Date(bean.getSavedateTimeStr(), DateUtil.DATE_FORMAT));
            } else {
                tSysStaff.setSavetime(DateUtil.converDate2SqlDate(new Date(Long.parseLong(bean.getSavedateTimeStr()))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        tSysStaff.setUserpassword(MD5Util.textToMD5L32(DEFAULT_PASSWORD));
        TSysStaff result = accountManager.updateAccount(tSysStaff);

        Map<String,Object> map = new HashMap<>();

        map.put("code", result.getResult());
        map.put("failinfo",result.getFailinfo());
        return map;
    }

    /**
     * 修改密码
     * @param bean 前端传入userid、savedateTimeStr、userpassword
     * @return
     */
    @RequestMapping("/updatePwd")
    @ResponseBody
    public Map<String, Object> updatePwd(NationalBean bean) {
        TSysStaff tSysStaff = new TSysStaff();
        tSysStaff.setUserid(bean.getUserid());
        try {
            if (bean.getSavedateTimeStr().trim().contains(" ")) {
                tSysStaff.setSavetime(DateUtil.convertString2Date(bean.getSavedateTimeStr(), DateUtil.DATE_FORMAT));
            } else {
                tSysStaff.setSavetime(DateUtil.converDate2SqlDate(new Date(Long.parseLong(bean.getSavedateTimeStr()))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        tSysStaff.setUserpassword(bean.getUserpassword());
        TSysStaff result = accountManager.updateAccount(tSysStaff);

        Map<String,Object> map = new HashMap<>();

        map.put("code", result.getResult());
        map.put("failinfo",result.getFailinfo());
        return map;
    }



    /**
     * 个人信息 传userid
     * @param user 传userid
     * @return
     */
    @RequestMapping("/personalDetail")
    @ResponseBody
    public Map<String,Object> personalDetail(TSysStaff user){
        Map<String,Object> map = new HashMap<>();
        TSysStaff currUser = accountManager.getAccountDetail(user);
        NationalBean newUser = new NationalBean();
        BeanUtils.copyProperties(currUser,newUser);
        newUser.setSavedateTimeStr(DateUtil.convertDate2String(newUser.getSavetime(), DateUtil.DATE_FORMAT));
        TSysRole role = new TSysRole();
        role.setRoleid(newUser.getRoleid());
        TSysRole retRole = sysRoleManager.getSysRole(role);
        newUser.setRolenames(retRole.getRolename());
        map.put("code", currUser.getResult());
        map.put("failinfo",currUser.getFailinfo());
        map.put("users",newUser);
        return map;
    }
}
