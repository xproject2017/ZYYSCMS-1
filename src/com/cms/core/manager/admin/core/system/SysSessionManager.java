package com.cms.core.manager.admin.core.system;


import com.cms.core.bean.cms.core.TSysSession;


public interface SysSessionManager {
    public TSysSession getSysSession(TSysSession bean);
    public TSysSession	addSysSession(TSysSession bean);
    public TSysSession	updateSysSession(TSysSession bean);
    public TSysSession	deleteSysSession(TSysSession bean);

    public TSysSession	checkSysSession(TSysSession bean);
}
