package com.zxxadmin.manager.core;


import com.cms.core.bean.cms.core.TSysStaff;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"Test-Spring-Usecase.xml"})
public class SysStaffManagerImpTest extends TestCase {


    @Test
    public void selectTest(){
        List<TSysStaff> list = null;
                //sysStaffManager.getSysStaffList(new TSysStaff());
        for(TSysStaff u:list){
            System.out.println(u.getUname());
        }


    }
}