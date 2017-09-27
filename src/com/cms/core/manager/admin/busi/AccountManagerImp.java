package com.cms.core.manager.admin.busi;

import com.cms.core.bean.cms.busi.NationalBean;
import com.cms.core.bean.cms.core.*;
import com.cms.core.manager.admin.core.system.SysMenuManager;
import com.cms.core.manager.admin.core.system.SysRoleManager;
import com.cms.core.manager.admin.core.system.SysSessionManager;
import com.cms.core.manager.admin.core.system.SysStaffManager;
import com.cms.util.StringUtil;
import com.cms.util.SysFlagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by whq on 2016/3/21.
 */
@Service
public class AccountManagerImp implements AccountManager {

    @Autowired
    private SysMenuManager sysMenuManager;

    @Autowired
    private SysRoleManager sysRoleManager;

    @Autowired
    private SysStaffManager sysStaffManager;

    @Autowired
    private SysSessionManager sysSessionManager;

    @Override
    public List<TSysStaff> getAccountList(TSysStaff bean) {
        return sysStaffManager.getSysStaffList(bean);
    }

    @Override
    public TSysStaff addAccount(TSysStaff bean) {
        System.out.println("bean.getUsername()==manager==2="+bean.getUname());
        return sysStaffManager.addSysStaff(bean);
    }

    @Override
    public TSysStaff updateAccount(TSysStaff bean) {
        return sysStaffManager.updateSysStaff(bean);
    }

    @Override
    public TSysStaff getAccountDetail(TSysStaff bean) {
        return sysStaffManager.getSysStaff(bean);
    }

    @Override
    public List<TSysRole> getRoleList(TSysRole bean) {
        return sysRoleManager.getSysRoleList(bean);
    }

    @Override
    public List<TSysMenu> getAllMenus(TSysMenu bean) {
        return sysMenuManager.getSysMenuList(bean);
    }

    @Override
    public List<TSysRole> getRoleListByRIDs(NationalBean bean) {
        return null;
    }

    @Override
    public List<TSysMenu> getRoleMenuByUserid(TSysStaff bean) {
        return sysMenuManager.getSysMenuListByUserid(bean);   //有问题，需要判断是全部的菜单，还是勾选的菜单
    }

    @Override
    public List<TSysMenu> getRoleMenuByRoleids(TSysStaff bean) {
        return sysMenuManager.getSysMenuListByRoleids(bean);   //有问题，需要判断是全部的菜单，还是勾选的菜单
    }

    @Override
    public List<TSysStaff> getAccountListByRoleName(TSysRole bean) {
        return sysStaffManager.getSysStaffListByRoleName(bean);
    }

    @Override
    public List<TSysStaff> getAccountListByUName(TSysStaff bean) {
        return sysStaffManager.getSysStaffListByUName(bean);
    }

    @Override
    public TSysSession writeToken(TSysStaff bean) {
        TSysSession dbsession=new TSysSession();

        StringBuilder sb = new StringBuilder();
        sb.append("!");
        sb.append(StringUtil.generateSessionId((long)bean.getUserid())); //生产token串
        sb.append("!");
        //sb.append(bean.getOrgid()); //局方id
        sb.append("!");
        sb.append(SysFlagUtil.PLATFORM_zhgl_8);//平台id
        sb.append("!");
        sb.append(bean.getUserid());//userid

        dbsession.setUserid(bean.getUserid());
        dbsession.setSessiontoken(sb.toString());
        dbsession.setStatus(1);//暂时没有用到
        sysSessionManager.addSysSession(dbsession);
        dbsession.setSessiontoken(dbsession.getSessionid()+dbsession.getSessiontoken()); //拼接上token流水号
        return dbsession;
    }

    @Override
    public List<TSysRole> getRoleListPriority(TSysRole bean) {
        return sysRoleManager.getRoleListPriority(bean);
    }

    @Override
    public List<TSysRolemenus> getExpertRoleList() {
        TSysRolemenus tSysRolemenus = new TSysRolemenus();
        tSysRolemenus.setNodeid(SysFlagUtil.nodeid_zjyp);
        return sysRoleManager.getSysRolemenusList(tSysRolemenus);
    }

    @Override
    public List<TSysStaff> getExpertList(TSysMenu menu) {

        menu.setNodeid(SysFlagUtil.nodeid_zjyp);
        return sysStaffManager.getSysStaffListByMenu4Ctl(menu);
    }

    @Override
    public List<TSysStaff> getLeaderList(TSysMenu menu) {
        menu.setNodeid(SysFlagUtil.nodeid_ldjc);
        return sysStaffManager.getSysStaffListByMenu4Ctl(menu);
    }
}
