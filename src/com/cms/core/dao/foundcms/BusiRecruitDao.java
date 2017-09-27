package com.cms.core.dao.foundcms;
import	com.cms.core.bean.cms.core.TBusiRecruit;
import java.util.HashMap;
import java.util.List;
/**
 ****标准DAO
 *此Dao接口由TBusiRecruit类自动生成
 *@author zhouxx
 *@since 2016-05-09 20:54:22
 */

public interface BusiRecruitDao{
    public TBusiRecruit	getBusiRecruit(TBusiRecruit	bean);
    public List<TBusiRecruit>	getBusiRecruitList(TBusiRecruit	bean);
    public HashMap	getBusiRecruitGroup(TBusiRecruit	bean);
    public int	addBusiRecruit(TBusiRecruit	bean);
    public int	updateBusiRecruit(TBusiRecruit	bean);
    public int	deleteBusiRecruit(TBusiRecruit	bean);

    public List<TBusiRecruit>	getBusiRecruitList4Next(TBusiRecruit	bean);
}
