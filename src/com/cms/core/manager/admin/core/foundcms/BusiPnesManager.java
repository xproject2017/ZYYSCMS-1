package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.cms.core.TBusiPnes;

import java.util.HashMap;
import java.util.List;

/**
 ****标准DAO
 *此Dao接口由TBusiPnes类自动生成
 *@author zhouxx
 *@since 2016-05-09 20:41:59
 */

public interface BusiPnesManager {
    public TBusiPnes	getBusiPnes(TBusiPnes bean);
    public List<TBusiPnes>	getBusiPnesList(TBusiPnes bean);
    public HashMap	getBusiPnesGroup(TBusiPnes bean);
    public TBusiPnes	addBusiPnes(TBusiPnes bean);
    public TBusiPnes	updateBusiPnes(TBusiPnes bean);
    public TBusiPnes	deleteBusiPnes(TBusiPnes bean);

 //热门新闻r
 public List<TBusiPnes>	getBusiPnesList4hot(TBusiPnes bean);

}
