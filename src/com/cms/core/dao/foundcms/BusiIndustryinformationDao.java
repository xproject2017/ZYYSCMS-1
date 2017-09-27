package com.cms.core.dao.foundcms;

import com.cms.core.bean.cms.core.TBusiIndustryinformation;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 浩 on 2017/9/11.
 */
public interface BusiIndustryinformationDao {

    public TBusiIndustryinformation	getBusiIndustryinformation(TBusiIndustryinformation	bean);
    public List<TBusiIndustryinformation> getBusiIndustryinformationList(TBusiIndustryinformation	bean);
    public HashMap getBusiIndustryinformationGroup(TBusiIndustryinformation	bean);
    public int	addBusiIndustryinformation(TBusiIndustryinformation	bean);
    public int	updateBusiIndustryinformation(TBusiIndustryinformation	bean);
    public int	deleteBusiIndustryinformation(TBusiIndustryinformation bean);
}
