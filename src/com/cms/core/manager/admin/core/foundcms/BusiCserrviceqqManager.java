package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.cms.core.TBusiCserrviceqq;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 浩 on 2017/9/11.
 */
public interface BusiCserrviceqqManager {
    public TBusiCserrviceqq getBusiCserrviceqq(TBusiCserrviceqq bean);
    public HashMap getBusiCserrviceqqGroup(TBusiCserrviceqq bean);
    public List<TBusiCserrviceqq> getBusiCserrviceqqList(TBusiCserrviceqq bean);
    public TBusiCserrviceqq addBusiCserrviceqq(TBusiCserrviceqq bean);
    public TBusiCserrviceqq updateBusiCserrviceqq(TBusiCserrviceqq bean);
    public TBusiCserrviceqq deleteBusiCserrviceqq(TBusiCserrviceqq bean);
}
