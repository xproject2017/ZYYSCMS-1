package com.cms.core.dao.foundcms;

import	com.cms.core.bean.cms.core.TBusiVip;
import java.util.HashMap;
import java.util.List;
/**
 ****标准DAO
 *此Dao接口由TBusiVip类自动生成
 *@author zhouxx
 *@since 2016-05-09 20:57:24
 */

public interface BusiVipDao{
    public TBusiVip	getBusiVip(TBusiVip	bean);
    public List<TBusiVip>	getBusiVipList(TBusiVip	bean);
    public HashMap	getBusiVipGroup(TBusiVip	bean);
    public int	addBusiVip(TBusiVip	bean);
    public int	updateBusiVip(TBusiVip	bean);
    public int	deleteBusiVip(TBusiVip	bean);

}

