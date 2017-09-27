package com.zxxadmin.manager.core;

import com.cms.core.bean.cms.core.RoleMenus;
import com.cms.core.bean.cms.core.TSysRole;
import com.cms.core.manager.admin.core.system.SysMenuManager;
import com.cms.core.manager.admin.core.system.SysRoleManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"Test-Spring-Usecase.xml"})
public class SysManagerTest {

    @Resource
    public SysRoleManager sysRoleManager;

    @Resource
    public SysMenuManager sysMenuManager;

    @Test
    public void testRole(){
        TSysRole role=new TSysRole();
        role.setRolename("zxx");

        RoleMenus bean=new RoleMenus();
        bean.setRolename("zxxxxx22222");
        List<Integer> nodeids=new ArrayList<Integer>();
        for(int i=0;i<5;i++){
            nodeids.add(i);
        }
        bean.setMenuidList(nodeids);

        sysRoleManager.addRoleAndRoleMenus(bean);
    }

}
