package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.cms.core.TBusiPproduct;
import com.cms.core.bean.cms.core.TBusiPproductcase;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 浩 on 2017/9/10.
 */
public interface BusiPproductCaseManager {

    public TBusiPproductcase getBusiPproductCase(TBusiPproductcase bean);
    public List<TBusiPproductcase> getBusiPproductCaseList(TBusiPproductcase bean);
    public HashMap getBusiPproductCaseGroup(TBusiPproductcase bean);
    public TBusiPproductcase	addBusiPproductCase(TBusiPproductcase bean);
    public TBusiPproductcase	updateBusiPproductCase(TBusiPproductcase bean);
    public TBusiPproductcase	deleteBusiPproductCase(TBusiPproductcase bean);

    //热门产品
    public List<TBusiPproductcase>	getBusiPproductCaseList4hot(TBusiPproductcase bean);
}
