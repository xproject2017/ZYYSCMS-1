package com.cms.core.dao.foundcms;

import	com.cms.core.bean.cms.core.TBusiPconsultation;
import java.util.HashMap;
import java.util.List;
/**
 ****标准DAO
 *此Dao接口由TBusiPconsultation类自动生成
 *@author zhouxx
 *@since 2016-05-09 20:33:15
 */

public interface BusiPconsultationDao{
    public TBusiPconsultation	getBusiPconsultation(TBusiPconsultation	bean);
    public List<TBusiPconsultation>	getBusiPconsultationList(TBusiPconsultation	bean);
    public HashMap	getBusiPconsultationGroup(TBusiPconsultation	bean);
    public int	addBusiPconsultation(TBusiPconsultation	bean);
    public int	updateBusiPconsultation(TBusiPconsultation	bean);
    public int	deleteBusiPconsultation(TBusiPconsultation	bean);

    public List<TBusiPconsultation>	getBusiPconsultationList4Next(TBusiPconsultation	bean);
}
