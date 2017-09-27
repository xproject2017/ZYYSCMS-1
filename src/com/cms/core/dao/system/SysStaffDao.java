package com.cms.core.dao.system;

import com.cms.core.bean.cms.core.TSysStaff;
import com.cms.core.bean.cms.core.TSysMenu;

import java.util.HashMap;
import java.util.List;
/**
 ****标准DAO
 *此Dao接口由TSysStaff类自动生成
 *@author zhouxx
 *@since 2016-03-19 18:52:45
 */

public interface SysStaffDao{
    public TSysStaff getSysStaff(TSysStaff	bean);
    public List<TSysStaff>	getSysStaffList(TSysStaff	bean);
    public HashMap	getSysStaffGroup(TSysStaff	bean);
    public int	addSysStaff(TSysStaff	bean);
    public int	updateSysStaff(TSysStaff	bean);
    public int	deleteSysStaff(TSysStaff	bean);

    public HashMap	getSysStaffGroupByUName(TSysStaff	bean);
    public List<TSysStaff>	getSysStaffListByUName(TSysStaff	bean);

    public List<TSysStaff>	getSysStaffListByMenu4Ctl(TSysMenu menu);
    public HashMap	getSysStaffListByMenu4Ctlgroup(TSysMenu menu);

}

