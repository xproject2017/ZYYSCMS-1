package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.cms.core.TBusiRecruit;

import java.util.HashMap;
import java.util.List;

/**
 ****标准DAO
 *此Dao接口由TBusiRecruit类自动生成
 *@author zhouxx
 *@since 2016-05-09 20:54:22
 */

public interface BusiRecruitManager {
    public TBusiRecruit	getBusiRecruit(TBusiRecruit bean);
    public List<TBusiRecruit>	getBusiRecruitList(TBusiRecruit bean);
    public HashMap	getBusiRecruitGroup(TBusiRecruit bean);
    public TBusiRecruit	addBusiRecruit(TBusiRecruit bean);
    public TBusiRecruit	updateBusiRecruit(TBusiRecruit bean);
    public TBusiRecruit	deleteBusiRecruit(TBusiRecruit bean);

}
