package com.cms.core.dao.system;

import com.cms.core.bean.cms.core.TSysMenu;
import com.cms.core.bean.cms.core.TSysStaff;

import java.util.HashMap;
import java.util.List;
/**
 ****标准DAO
 *此Dao接口由TSysMenu类自动生成
 *@author zhouxx
 *@since 2016-03-19 18:35:25
 */

public interface SysMenuDao{
    public TSysMenu getSysMenu(TSysMenu bean);
    public List<TSysMenu>	getSysMenuList(TSysMenu	bean);
    public HashMap	getSysMenuGroup(TSysMenu	bean);
    public int	addSysMenu(TSysMenu	bean);
    public int	updateSysMenu(TSysMenu	bean);
    public int	deleteSysMenu(TSysMenu	bean);

    //-- 条件 T_SYS_STAFF.ROLEIDS查询用户角色的菜单 角色的全量菜单
    public List<TSysMenu> getSysMenuListByRoleids(TSysStaff bean);
    //-- 条件 T_SYS_STAFF.USERID查询用户已选的菜单  当前用户已选的菜单
    public List<TSysMenu> getSysMenuListByUserid(TSysStaff bean);

    //通过一级导航和二级导航更新菜单名
    public int	updateSysMenuByNavigationid(TSysMenu	bean);

}