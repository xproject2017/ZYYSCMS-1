package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.cms.core.TBusiPmultiplemsg;

import java.util.HashMap;
import java.util.List;

/**
 ****标准DAO
 *此Dao接口由TBusiPmultiplemsg类自动生成
 *@author zhouxx
 *@since 2016-05-09 20:37:57
 */

public interface BusiPmultiplemsgManager {
    public TBusiPmultiplemsg	getBusiPmultiplemsg(TBusiPmultiplemsg bean);
    public List<TBusiPmultiplemsg>	getBusiPmultiplemsgList(TBusiPmultiplemsg bean);
    public HashMap	getBusiPmultiplemsgGroup(TBusiPmultiplemsg bean);
    public TBusiPmultiplemsg	addBusiPmultiplemsg(TBusiPmultiplemsg bean);
    public TBusiPmultiplemsg	updateBusiPmultiplemsg(TBusiPmultiplemsg bean);
    public TBusiPmultiplemsg	deleteBusiPmultiplemsg(TBusiPmultiplemsg bean);

    //后台管理 综合处理是否上大banner
    public List<TBusiPmultiplemsg>	getBusiPmultiplemsgList4admin(TBusiPmultiplemsg	bean);
    //banner上只展示10条
    public List<TBusiPmultiplemsg>	getBusiPmultiplemsgList4cms(TBusiPmultiplemsg	bean);

    //搜索
    public List<TBusiPmultiplemsg>	getBusiPmultiplemsgList4search(TBusiPmultiplemsg bean);
    public HashMap	getBusiPmultiplemsgList4searchGroup(TBusiPmultiplemsg	bean);
}
