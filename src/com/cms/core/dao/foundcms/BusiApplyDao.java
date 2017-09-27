package com.cms.core.dao.foundcms;

import	com.cms.core.bean.cms.core.TBusiApply;
import java.util.HashMap;
import java.util.List;
/**
 ****标准DAO
 *此Dao接口由TBusiApply类自动生成
 *@author zhouxx
 *@since 2016-05-09 20:18:31
 */

public interface BusiApplyDao{
    public TBusiApply	getBusiApply(TBusiApply	bean);
    public List<TBusiApply>	getBusiApplyList(TBusiApply	bean);
    public HashMap	getBusiApplyGroup(TBusiApply	bean);
    public int	addBusiApply(TBusiApply	bean);
    public int	updateBusiApply(TBusiApply	bean);
    public int	deleteBusiApply(TBusiApply	bean);

}