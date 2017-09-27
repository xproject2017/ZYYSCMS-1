package com.cms.core.dao.foundcms;

import	com.cms.core.bean.cms.core.TBusiPproduct;
import java.util.HashMap;
import java.util.List;
/**
 ****标准DAO
 *此Dao接口由TBusiPproduct类自动生成
 *@author zhouxx
 *@since 2016-05-09 20:48:07
 */

public interface BusiPproductDao{
    public TBusiPproduct	getBusiPproduct(TBusiPproduct	bean);
    public List<TBusiPproduct>	getBusiPproductList(TBusiPproduct	bean);
    public HashMap	getBusiPproductGroup(TBusiPproduct	bean);
    public int	addBusiPproduct(TBusiPproduct	bean);
    public int	updateBusiPproduct(TBusiPproduct	bean);
    public int	deleteBusiPproduct(TBusiPproduct	bean);

    //热门产品
    public List<TBusiPproduct>	getBusiPproductList4hot(TBusiPproduct	bean);

    public List<TBusiPproduct>	getBusiPproductList4Next (TBusiPproduct	bean);
}

