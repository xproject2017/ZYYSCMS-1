package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.cms.core.TBusiVip;

import java.util.HashMap;
import java.util.List;

/**
 ****标准DAO
 *此Dao接口由TBusiVip类自动生成
 *@author zhouxx
 *@since 2016-05-09 20:57:24
 */

public interface BusiVipManager {
    public TBusiVip	getBusiVip(TBusiVip bean);
    public List<TBusiVip>	getBusiVipList(TBusiVip bean);
    public HashMap	getBusiVipGroup(TBusiVip bean);
    public TBusiVip	addBusiVip(TBusiVip bean);
    public TBusiVip	updateBusiVip(TBusiVip bean);
    public TBusiVip	deleteBusiVip(TBusiVip bean);

}
