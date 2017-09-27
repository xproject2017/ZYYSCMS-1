package com.cms.core.dao.foundcms;

import	com.cms.core.bean.cms.core.TBusiPnotice;
import java.util.HashMap;
import java.util.List;
/**
 ****标准DAO
 *此Dao接口由TBusiPnotice类自动生成
 *@author zhouxx
 *@since 2016-05-09 20:45:14
 */

public interface BusiPnoticeDao{
    public TBusiPnotice	getBusiPnotice(TBusiPnotice	bean);
    public List<TBusiPnotice>	getBusiPnoticeList(TBusiPnotice	bean);
    public HashMap	getBusiPnoticeGroup(TBusiPnotice	bean);
    public int	addBusiPnotice(TBusiPnotice	bean);
    public int	updateBusiPnotice(TBusiPnotice	bean);
    public int	deleteBusiPnotice(TBusiPnotice	bean);

    //热门消息
    public List<TBusiPnotice>	getBusiPnoticeList4hot(TBusiPnotice	bean);

    public List<TBusiPnotice>	getBusiPnoticeList4Next(TBusiPnotice	bean);
}