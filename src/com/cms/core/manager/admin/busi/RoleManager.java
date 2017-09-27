package com.cms.core.manager.admin.busi;

import com.cms.core.bean.cms.core.RoleMenus;
import com.cms.core.bean.cms.core.TSysRole;
import com.cms.core.bean.cms.core.TSysStaff;

import java.util.List;

/**
 * Created by clm on 2016/3/23.
 */
public interface RoleManager {
    //角色列表查询
    public List<RoleMenus> getRoleList(RoleMenus bean);
    //角色详情查询
    public RoleMenus getRoleDetail(TSysStaff bean);
    //新增角色-权限
    public TSysRole addRole(RoleMenus bean);
    //修改橘色-权限
    public TSysRole updateRole(RoleMenus bean);
}
