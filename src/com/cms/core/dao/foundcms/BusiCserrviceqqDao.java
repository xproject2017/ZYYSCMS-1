package com.cms.core.dao.foundcms;

import com.cms.core.bean.cms.core.TBusiCserrviceqq;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 浩 on 2017/9/11.
 */
public interface BusiCserrviceqqDao {
    public TBusiCserrviceqq	getBusiCserrviceqq(TBusiCserrviceqq	bean);
    public List<TBusiCserrviceqq> getBusiCserrviceqqList(TBusiCserrviceqq	bean);
    public HashMap getBusiCserrviceqqGroup(TBusiCserrviceqq	bean);
    public int	addBusiCserrviceqq(TBusiCserrviceqq	bean);
    public int	updateBusiCserrviceqq(TBusiCserrviceqq	bean);
    public int	deleteBusiCserrviceqq(TBusiCserrviceqq bean);
}
