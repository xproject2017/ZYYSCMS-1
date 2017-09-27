package com.cms.core.dao.foundcms;

import	com.cms.core.bean.cms.core.TBusiPnes;
import java.util.HashMap;
import java.util.List;
/**
 ****标准DAO
 *此Dao接口由TBusiPnes类自动生成
 *@author zhouxx
 *@since 2016-05-09 20:41:59
 */

public interface BusiPnesDao{
    public TBusiPnes	getBusiPnes(TBusiPnes	bean);
    public List<TBusiPnes>	getBusiPnesList(TBusiPnes	bean);
    public HashMap	getBusiPnesGroup(TBusiPnes	bean);
    public int	addBusiPnes(TBusiPnes	bean);
    public int	updateBusiPnes(TBusiPnes	bean);
    public int	deleteBusiPnes(TBusiPnes	bean);

    //热门新闻
    public List<TBusiPnes>	getBusiPnesList4hot(TBusiPnes	bean);

    //上一条下一条
    public List<TBusiPnes>	getBusiPnesList4Next(TBusiPnes	bean);
}
