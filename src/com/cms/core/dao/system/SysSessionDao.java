package com.cms.core.dao.system;

import com.cms.core.bean.cms.core.TSysSession;

import java.util.HashMap;
import java.util.List;
/**
 ****标准DAO
 *此Dao接口由TSysSession类自动生成
 *@author zhouxx
 *@since 2016-03-19 18:50:56
 */

public interface SysSessionDao{
    public TSysSession getSysSession(TSysSession	bean);
    public List<TSysSession>	getSysSessionList(TSysSession	bean);
    public HashMap	getSysSessionGroup(TSysSession	bean);
    public int	addSysSession(TSysSession	bean);
    public int	updateSysSession(TSysSession	bean);
    public int	deleteSysSession(TSysSession	bean);

}