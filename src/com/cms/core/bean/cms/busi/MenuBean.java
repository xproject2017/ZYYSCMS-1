package com.cms.core.bean.cms.busi;

import com.cms.core.bean.cms.core.TSysMenu;

import java.util.List;

/**
 * Created by clm on 2016/3/22.
 */
public class MenuBean extends TSysMenu{
    private List<MenuBean> sonMenus;//所有子菜单列表
    private boolean checked;//账户含有的菜单

    public List<MenuBean> getSonMenus() {
        return sonMenus;
    }

    public void setSonMenus(List<MenuBean> sonMenus) {
        this.sonMenus = sonMenus;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

}
