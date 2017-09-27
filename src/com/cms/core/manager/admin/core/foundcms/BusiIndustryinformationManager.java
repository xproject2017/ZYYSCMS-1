package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.cms.core.TBusiIndustryinformation;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 浩 on 2017/9/11.
 */
public interface BusiIndustryinformationManager {
    public TBusiIndustryinformation getBusiIndustryinformation(TBusiIndustryinformation bean);
    public HashMap getBusiIndustryinformationGroup(TBusiIndustryinformation bean);
    public List<TBusiIndustryinformation> getBusiIndustryinformationList(TBusiIndustryinformation bean);
    public TBusiIndustryinformation addBusiIndustryinformation(TBusiIndustryinformation bean);
    public TBusiIndustryinformation updateBusiIndustryinformation(TBusiIndustryinformation bean);
    public TBusiIndustryinformation deleteBusiIndustryinformation(TBusiIndustryinformation bean);
}
