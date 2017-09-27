package com.cms.core.dao.foundcms;

import	com.cms.core.bean.cms.core.TBusiPbespoke;
import java.util.HashMap;
import java.util.List;
/**
 ****标准DAO
 *此Dao接口由TBusiPbespoke类自动生成
 *@author zhouxx
 *@since 2016-05-09 20:29:57
 */

public interface BusiPbespokeDao{
    public TBusiPbespoke	getBusiPbespoke(TBusiPbespoke	bean);
    public List<TBusiPbespoke>	getBusiPbespokeList(TBusiPbespoke	bean);
    public HashMap	getBusiPbespokeGroup(TBusiPbespoke	bean);
    public int	addBusiPbespoke(TBusiPbespoke	bean);
    public int	updateBusiPbespoke(TBusiPbespoke	bean);
    public int	deleteBusiPbespoke(TBusiPbespoke	bean);

    //上一页下一页
    public List<TBusiPbespoke>	getBusiPbespokeList4Next(TBusiPbespoke	bean);
}
