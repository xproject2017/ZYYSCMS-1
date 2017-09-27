package com.cms.core.manager.admin.busi;

import com.cms.core.bean.cms.busi.NationalBean;
import com.cms.core.bean.cms.core.*;
import com.cms.core.bean.cms.core.TSysSession;

import java.util.List;

/**
 * Created by whq on 2016/3/21.
 */
public interface AccountManager {
    //获取账户列表
    public List<TSysStaff> getAccountList(TSysStaff bean);
    //新增账户
    public TSysStaff addAccount(TSysStaff bean);
    //修改账户
    public TSysStaff updateAccount(TSysStaff bean);
    //获取账户详情
    public TSysStaff getAccountDetail(TSysStaff bean);
    //获取所有角色列表
    public List<TSysRole> getRoleList(TSysRole bean);
    //获取所有的权限列表
    public List<TSysMenu> getAllMenus(TSysMenu bean);
    //根据成员角色id获取成员角色
    public List<TSysRole> getRoleListByRIDs(NationalBean bean);
    //获取用户id获取相关的menu
    public List<TSysMenu> getRoleMenuByUserid(TSysStaff bean);
    //获取角色ids获取相关的menu
    public List<TSysMenu> getRoleMenuByRoleids(TSysStaff bean);
    //根据角色名称搜索账户列表
    public List<TSysStaff> getAccountListByRoleName(TSysRole bean);
    //根据用户姓名搜索账户列表
    public List<TSysStaff> getAccountListByUName(TSysStaff bean);

    //写token
    public TSysSession writeToken(TSysStaff bean);
    //获取优先级低于当前角色的所有角色列表
    public List<TSysRole> getRoleListPriority(TSysRole bean);
    //获取专家研判对应的角色列表
    public List<TSysRolemenus> getExpertRoleList();
    //获取专家列表（姓名模糊搜索，根据领域精确搜索）
    public List<TSysStaff> getExpertList(TSysMenu menu);

    //获取领导列表（姓名模糊搜索）
    public List<TSysStaff> getLeaderList(TSysMenu menu);
}
