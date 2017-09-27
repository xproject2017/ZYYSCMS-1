package com.cms.core.dao.foundcms;

import com.cms.core.bean.cms.core.TBusiPproductcase;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 浩 on 2017/9/10.
 */
public interface BusiPproductcaseDao {

    public TBusiPproductcase	getBusiPproductcase(TBusiPproductcase	bean);
    public List<TBusiPproductcase> getBusiPproductcaseList(TBusiPproductcase	bean);
    public HashMap getBusiPproductcaseGroup(TBusiPproductcase	bean);
    public int	addBusiPproductcase(TBusiPproductcase	bean);
    public int	updateBusiPproductcase(TBusiPproductcase	bean);
    public int	deleteBusiPproductcase(TBusiPproductcase bean);
}
