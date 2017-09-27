package com.cms.core.dao.foundcms;

import	com.cms.core.bean.cms.core.TBusiNavigationid1;
import java.util.HashMap;
import java.util.List;
/**
 ****标准DAO
 *此Dao接口由TBusiNavigationid1类自动生成
 *@author zhouxx
 *@since 2016-05-09 20:23:31
 */

public interface BusiNavigationid1Dao{
    public TBusiNavigationid1	getBusiNavigationid1(TBusiNavigationid1	bean);
    public List<TBusiNavigationid1>	getBusiNavigationid1List(TBusiNavigationid1	bean);
    public HashMap	getBusiNavigationid1Group(TBusiNavigationid1	bean);
    public int	addBusiNavigationid1(TBusiNavigationid1	bean);
    public int	updateBusiNavigationid1(TBusiNavigationid1	bean);
    public int	deleteBusiNavigationid1(TBusiNavigationid1	bean);

}