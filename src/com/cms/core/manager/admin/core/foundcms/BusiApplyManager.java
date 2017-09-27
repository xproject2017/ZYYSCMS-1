package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.cms.core.TBusiApply;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/5/11.
 */
public interface BusiApplyManager {
    public TBusiApply getBusiApply(TBusiApply	bean);
    public List<TBusiApply> getBusiApplyList(TBusiApply	bean);
    public HashMap getBusiApplyGroup(TBusiApply	bean);
    public TBusiApply	addBusiApply(TBusiApply	bean);
    public TBusiApply	updateBusiApply(TBusiApply	bean);
    public TBusiApply	deleteBusiApply(TBusiApply	bean);
}
