package com.cms.core.dao.foundcms;

import	com.cms.core.bean.cms.core.TBusiNavigationid2;
import java.util.HashMap;
import java.util.List;
/**
 ****标准DAO
 *此Dao接口由TBusiNavigationid2类自动生成
 *@author zhouxx
 *@since 2016-05-09 20:26:54
 */

public interface BusiNavigationid2Dao{
    public TBusiNavigationid2	getBusiNavigationid2(TBusiNavigationid2	bean);
    public List<TBusiNavigationid2>	getBusiNavigationid2List(TBusiNavigationid2	bean);
    public HashMap	getBusiNavigationid2Group(TBusiNavigationid2	bean);
    public int	addBusiNavigationid2(TBusiNavigationid2	bean);
    public int	updateBusiNavigationid2(TBusiNavigationid2	bean);
    public int	deleteBusiNavigationid2(TBusiNavigationid2	bean);

}
