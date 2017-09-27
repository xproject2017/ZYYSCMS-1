package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.cms.core.TBusiPnotice;

import java.util.HashMap;
import java.util.List;

/**
 ****标准DAO
 *此Dao接口由TBusiPnotice类自动生成
 *@author zhouxx
 *@since 2016-05-09 20:45:14
 */

public interface BusiPnoticeManager {
    public TBusiPnotice	getBusiPnotice(TBusiPnotice bean);
    public List<TBusiPnotice>	getBusiPnoticeList(TBusiPnotice bean);
    public HashMap	getBusiPnoticeGroup(TBusiPnotice bean);
    public TBusiPnotice	addBusiPnotice(TBusiPnotice bean);
    public TBusiPnotice	updateBusiPnotice(TBusiPnotice bean);
    public TBusiPnotice	deleteBusiPnotice(TBusiPnotice bean);

    //热门消息
    public List<TBusiPnotice>	getBusiPnoticeList4hot(TBusiPnotice	bean);
}