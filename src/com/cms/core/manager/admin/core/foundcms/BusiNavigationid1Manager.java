package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.cms.busi.Navigationid;
import com.cms.core.bean.cms.core.TBusiNavigationid1;

import java.util.HashMap;
import java.util.List;

/**
 ****标准DAO
 *此Dao接口由TBusiNavigationid1类自动生成
 *@author zhouxx
 *@since 2016-05-09 20:23:31
 */

public interface BusiNavigationid1Manager {
    public TBusiNavigationid1	getBusiNavigationid1(TBusiNavigationid1 bean);
    public List<TBusiNavigationid1>	getBusiNavigationid1List(TBusiNavigationid1 bean);
    public HashMap	getBusiNavigationid1Group(TBusiNavigationid1 bean);
    public TBusiNavigationid1	addBusiNavigationid1(TBusiNavigationid1 bean);
    public TBusiNavigationid1	updateBusiNavigationid1(TBusiNavigationid1 bean);
    public TBusiNavigationid1	deleteBusiNavigationid1(TBusiNavigationid1 bean);

    //同时修改一级导航和二级导航
    public  TBusiNavigationid1  updateBusiNavigationid(Navigationid listbean);
}