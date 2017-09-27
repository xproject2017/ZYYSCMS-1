package com.cms.core.bean.cms.core;

import com.cms.core.bean.cms.core.TSysMenu;
import com.cms.core.bean.cms.core.TSysRole;

import java.util.List;

/**
 * Created by clm on 2016/3/23.
 */
public class RoleMenus extends TSysRole {
    private List<Integer> menuidList;//菜单id列表
    private String menuids;//菜单id，以逗号分隔
    private List<TSysMenu> menuList;//菜单列表
    private String menuNames;//父级菜单名称，/分隔

    public List<Integer> getMenuidList() {
        return menuidList;
    }

    public void setMenuidList(List<Integer> menuidList) {
        this.menuidList = menuidList;
    }

    public String getMenuids() {
        return menuids;
    }

    public void setMenuids(String menuids) {
        this.menuids = menuids;
    }

    public List<TSysMenu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<TSysMenu> menuList) {
        this.menuList = menuList;
    }

    public String getMenuNames() {
        return menuNames;
    }

    public void setMenuNames(String menuNames) {
        this.menuNames = menuNames;
    }
}
