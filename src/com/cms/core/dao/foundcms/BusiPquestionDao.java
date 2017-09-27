package com.cms.core.dao.foundcms;

import	com.cms.core.bean.cms.core.TBusiPquestion;
import java.util.HashMap;
import java.util.List;
/**
 ****标准DAO
 *此Dao接口由TBusiPquestion类自动生成
 *@author zhouxx
 *@since 2016-05-09 20:51:22
 */

public interface BusiPquestionDao{
    public TBusiPquestion	getBusiPquestion(TBusiPquestion	bean);
    public List<TBusiPquestion>	getBusiPquestionList(TBusiPquestion	bean);
    public HashMap	getBusiPquestionGroup(TBusiPquestion	bean);
    public int	addBusiPquestion(TBusiPquestion	bean);
    public int	updateBusiPquestion(TBusiPquestion	bean);
    public int	deleteBusiPquestion(TBusiPquestion	bean);

    public List<TBusiPquestion>	getBusiPquestionList4Next(TBusiPquestion	bean);
}
