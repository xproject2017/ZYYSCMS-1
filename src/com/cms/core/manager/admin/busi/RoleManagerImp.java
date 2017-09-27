package com.cms.core.manager.admin.busi;

import com.cms.core.bean.cms.core.RoleMenus;
import com.cms.core.bean.cms.core.TSysRole;
import com.cms.core.bean.cms.core.TSysStaff;
import com.cms.core.manager.admin.core.system.SysRoleManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clm on 2016/3/23.
 */
@Service
public class RoleManagerImp implements RoleManager {
    @Autowired
    private SysRoleManager sysRoleManager;

    @Override
    public List<RoleMenus> getRoleList(RoleMenus bean) {
        List<RoleMenus> newList = new ArrayList<>();
        if ("".equals(bean.getRolename())){
            bean.setRolename(null);
        }
        List<TSysRole> retList = sysRoleManager.getSysRoleList(bean);
        for(TSysRole role:retList){
            RoleMenus rms = new RoleMenus();
            BeanUtils.copyProperties(role,rms);
            newList.add(rms);
        }
        return newList;
    }

    @Override
    public RoleMenus getRoleDetail(TSysStaff bean) {
        return null;
    }

    @Override
    public TSysRole addRole(RoleMenus bean) {
        return sysRoleManager.addRoleAndRoleMenus(bean);
    }

    @Override
    public TSysRole updateRole(RoleMenus bean) {
        return sysRoleManager.updateRoleAndRoleMenus(bean);
    }
}
