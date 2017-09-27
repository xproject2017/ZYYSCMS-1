package com.zxxadmin.dao;

import com.cms.core.bean.cms.core.TSysMenu;
import com.cms.core.bean.cms.core.TSysRole;
import com.cms.core.bean.cms.core.TSysRolemenus;
import com.cms.core.dao.system.*;
import com.cms.util.DictUtils;
import com.cms.util.SysFlagUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"Test-Spring-Usecase.xml"})
public class SystemDaoTest {
    @Resource
    private SysRoleDao sysRoleDao;
    @Resource
    private SysMenuDao sysMenuDao;
    @Resource
    private SysStaffDao sysStaffDao;
    @Resource
    private SysSessionDao sysSessionDao;
    @Resource
    private SysRolemenusDao sysRolemenusDao;


    @Test
    public void testconf(){
        System.out.println(DictUtils.getTagNameByID("10001",2));
    }

    @Test
    public void initRole(){
        TSysRole role=new TSysRole();
        role.setRoleid(SysFlagUtil.role_admin);
        role.setRolename("admin");
        sysRoleDao.addSysRole(role);

        role=new TSysRole();
        role.setRoleid(SysFlagUtil.role_executive);
        role.setRolename("行政管理");
        sysRoleDao.addSysRole(role);


    }
    @Test
    public void initMenu(){
        TSysMenu tSysMenu=new TSysMenu();
        tSysMenu.setNodeid(1);
        //tSysMenu.setOrgid(SysFlagUtil.org_hz_1);
        tSysMenu.setNodelevel(1);
        tSysMenu.setNodecode("010000");
        tSysMenu.setNodetext("产品标准库");
        tSysMenu.setPath("/view/checknetwork/productLibListPage");
//        tSysMenu.setFnodeid(0);
//        tSysMenu.setPlatformflag(SysFlagUtil.PLATFORM_jcwl_0);
//        tSysMenu.setComment("");
        sysMenuDao.addSysMenu(tSysMenu);

        tSysMenu=new TSysMenu();
        tSysMenu.setNodeid(2);
       // tSysMenu.setOrgid(SysFlagUtil.org_hz_1);
        tSysMenu.setNodelevel(1);
        tSysMenu.setNodecode("020000");
        tSysMenu.setNodetext("方法标准库");
        tSysMenu.setPath("/view/checknetwork/methodLibListPage");
//        tSysMenu.setFnodeid(0);
//        tSysMenu.setPlatformflag(SysFlagUtil.PLATFORM_jcwl_0);
//        tSysMenu.setComment("");
        sysMenuDao.addSysMenu(tSysMenu);


    }

    @Test
    public void initRoleMenu(){
        TSysRolemenus tSysRolemenus=new TSysRolemenus();
        for(int i=1;i<=10;i++){
            tSysRolemenus=new TSysRolemenus();
            tSysRolemenus.setRoleid(SysFlagUtil.role_admin);
            tSysRolemenus.setNodeid(i);
            sysRolemenusDao.addSysRolemenus(tSysRolemenus);
        }


        tSysRolemenus=new TSysRolemenus();
        tSysRolemenus.setRoleid(SysFlagUtil.role_executive);
        tSysRolemenus.setNodeid(7);
        sysRolemenusDao.addSysRolemenus(tSysRolemenus);

        tSysRolemenus=new TSysRolemenus();
        tSysRolemenus.setRoleid(SysFlagUtil.role_executive);
        tSysRolemenus.setNodeid(8);
        sysRolemenusDao.addSysRolemenus(tSysRolemenus);


    }



}