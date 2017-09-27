package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.cms.core.TBusiPquestion;

import java.util.HashMap;
import java.util.List;

/**
 ****标准DAO
 *此Dao接口由TBusiPquestion类自动生成
 *@author zhouxx
 *@since 2016-05-09 20:51:22
 */

public interface BusiPquestionManager {
    public TBusiPquestion	getBusiPquestion(TBusiPquestion bean);
    public List<TBusiPquestion>	getBusiPquestionList(TBusiPquestion bean);
    public HashMap	getBusiPquestionGroup(TBusiPquestion bean);
    public TBusiPquestion	addBusiPquestion(TBusiPquestion bean);
    public TBusiPquestion	updateBusiPquestion(TBusiPquestion bean);
    public TBusiPquestion	deleteBusiPquestion(TBusiPquestion bean);

}
