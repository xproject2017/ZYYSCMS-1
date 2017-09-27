package com.cms.core.manager.admin.core.system;


import com.cms.core.bean.cms.core.TSysRole;
import com.cms.core.bean.cms.core.TSysRolemenus;
import com.cms.core.bean.cms.core.RoleMenus;

import java.util.HashMap;
import java.util.List;


public interface SysRoleManager {
    public TSysRole getSysRole(TSysRole bean);
    public List<TSysRole> getSysRoleList(TSysRole bean);
    public HashMap getSysRoleGroup(TSysRole bean);
    public TSysRole addSysRole(TSysRole bean);
    public TSysRole updateSysRole(TSysRole bean);
    public TSysRole deleteSysRole(TSysRole bean);

    public TSysRolemenus getSysRolemenus(TSysRolemenus bean);
    public List<TSysRolemenus> getSysRolemenusList(TSysRolemenus bean);
    public HashMap getSysRolemenusGroup(TSysRolemenus bean);
    public TSysRolemenus addSysRolemenus(TSysRolemenus bean);
    public TSysRolemenus updateSysRolemenus(TSysRolemenus bean);
    public TSysRolemenus deleteSysRolemenus(TSysRolemenus bean);

    //新增角色及权限
    public TSysRole addRoleAndRoleMenus(RoleMenus bean);

    //修改角色及权限
    public TSysRole updateRoleAndRoleMenus(RoleMenus bean);
    //获取优先级低于当前角色的所有角色列表
    public List<TSysRole> getRoleListPriority(TSysRole bean);

}
