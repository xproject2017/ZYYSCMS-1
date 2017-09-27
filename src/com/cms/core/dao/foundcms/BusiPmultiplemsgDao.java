package com.cms.core.dao.foundcms;

import	com.cms.core.bean.cms.core.TBusiPmultiplemsg;
import java.util.HashMap;
import java.util.List;
/**
 ****标准DAO
 *此Dao接口由TBusiPmultiplemsg类自动生成
 *@author zhouxx
 *@since 2016-05-09 20:37:57
 */

public interface BusiPmultiplemsgDao{
    public TBusiPmultiplemsg	getBusiPmultiplemsg(TBusiPmultiplemsg	bean);
    public List<TBusiPmultiplemsg>	getBusiPmultiplemsgList(TBusiPmultiplemsg	bean);
    public HashMap	getBusiPmultiplemsgGroup(TBusiPmultiplemsg	bean);
    public int	addBusiPmultiplemsg(TBusiPmultiplemsg	bean);
    public int	updateBusiPmultiplemsg(TBusiPmultiplemsg	bean);
    public int	deleteBusiPmultiplemsg(TBusiPmultiplemsg	bean);

    //后台管理 综合处理是否上大banner
    public List<TBusiPmultiplemsg>	getBusiPmultiplemsgList4admin(TBusiPmultiplemsg	bean);
    //banner上只展示10条
    public List<TBusiPmultiplemsg>	getBusiPmultiplemsgList4cms(TBusiPmultiplemsg	bean);

    //搜索
    public List<TBusiPmultiplemsg>	getBusiPmultiplemsgList4search(TBusiPmultiplemsg	bean);
    public HashMap getBusiPmultiplemsgList4searchGroup(TBusiPmultiplemsg	bean);

    public List<TBusiPmultiplemsg>	getBusiPmultiplemsgList4Next (TBusiPmultiplemsg	bean);
}
