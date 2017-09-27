package com.cms.core.manager.admin.core.system;


import com.cms.core.bean.cms.core.TSysMenu;
import com.cms.core.bean.cms.core.TSysStaff;

import java.util.HashMap;
import java.util.List;


public interface SysMenuManager {
    public TSysMenu getSysMenu(TSysMenu bean);
    public List<TSysMenu> getSysMenuList(TSysMenu bean);
    public HashMap getSysMenuGroup(TSysMenu bean);
    public TSysMenu addSysMenu(TSysMenu bean);
    public TSysMenu updateSysMenu(TSysMenu bean);
    public TSysMenu deleteSysMenu(TSysMenu bean);

    //-- 条件 T_SYS_STAFF.ROLEIDS查询用户角色的菜单 角色的全量菜单
    public List<TSysMenu> getSysMenuListByRoleids(TSysStaff bean);
    //-- 条件 T_SYS_STAFF.USERID查询用户已选的菜单  当前用户已选的菜单
    public List<TSysMenu> getSysMenuListByUserid(TSysStaff bean);


}
