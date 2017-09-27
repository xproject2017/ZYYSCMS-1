package com.cms.core.dao.system;

import com.cms.core.bean.cms.core.TSysRole;

import java.util.HashMap;
import java.util.List;
/**
 ****标准DAO
 *此Dao接口由TSysRole类自动生成
 *@author zhouxx
 *@since 2016-03-21 10:07:11
 */

public interface SysRoleDao{
    public TSysRole getSysRole(TSysRole	bean);
    public List<TSysRole>	getSysRoleList(TSysRole	bean);
    public HashMap	getSysRoleGroup(TSysRole	bean);
    public int	addSysRole(TSysRole	bean);
    public int	updateSysRole(TSysRole	bean);
    public int	deleteSysRole(TSysRole	bean);

    public List<TSysRole>	getSysRoleList_gt_rid(TSysRole	bean);
}