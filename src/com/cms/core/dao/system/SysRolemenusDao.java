package com.cms.core.dao.system;
import com.cms.core.bean.cms.core.TSysRolemenus;

import java.util.HashMap;
import java.util.List;
/**
 ****标准DAO
 *此Dao接口由TSysRolemenus类自动生成
 *@author zhouxx
 *@since 2016-03-19 18:45:57
 */

public interface SysRolemenusDao{
    public TSysRolemenus getSysRolemenus(TSysRolemenus	bean);
    public List<TSysRolemenus>	getSysRolemenusList(TSysRolemenus	bean);
    public HashMap	getSysRolemenusGroup(TSysRolemenus	bean);
    public int	addSysRolemenus(TSysRolemenus	bean);
    public int	updateSysRolemenus(TSysRolemenus	bean);
    public int	deleteSysRolemenus(TSysRolemenus	bean);

    public List<TSysRolemenus>  getSysRolemenusListBYnodeids(TSysRolemenus	bean);

}
