package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.cms.core.TBusiPbespoke;

import java.util.HashMap;
import java.util.List;

/**
 ****标准DAO
 *此Dao接口由TBusiPbespoke类自动生成
 *@author zhouxx
 *@since 2016-05-09 20:29:57
 */

public interface BusiPbespokeManager {
    public TBusiPbespoke	getBusiPbespoke(TBusiPbespoke bean);
    public List<TBusiPbespoke>	getBusiPbespokeList(TBusiPbespoke bean);
    public HashMap	getBusiPbespokeGroup(TBusiPbespoke bean);
    public TBusiPbespoke	addBusiPbespoke(TBusiPbespoke bean);
    public TBusiPbespoke	updateBusiPbespoke(TBusiPbespoke bean);
    public TBusiPbespoke	deleteBusiPbespoke(TBusiPbespoke bean);

}
