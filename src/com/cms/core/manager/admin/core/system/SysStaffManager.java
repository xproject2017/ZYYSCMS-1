package com.cms.core.manager.admin.core.system;


import com.cms.core.bean.cms.core.TSysRole;
import com.cms.core.bean.cms.core.TSysStaff;
import com.cms.core.bean.cms.core.TSysMenu;

import java.util.HashMap;
import java.util.List;


public interface SysStaffManager {
    public TSysStaff getSysStaff(TSysStaff bean);
    public List<TSysStaff> getSysStaffList(TSysStaff bean);
    public HashMap getSysStaffGroup(TSysStaff bean);
    public TSysStaff	addSysStaff(TSysStaff bean);
    public TSysStaff	updateSysStaff(TSysStaff bean);
    public TSysStaff	deleteSysStaff(TSysStaff bean);

    //根据角色名称获取账户列表信息
    public List<TSysStaff> getSysStaffListByRoleName(TSysRole role);

    //根据用户姓名模糊搜索账户列表
    public List<TSysStaff> getSysStaffListByUName(TSysStaff bean);


    public List<TSysStaff> getSysStaffListByMenu4Ctl(TSysMenu menu);

}
