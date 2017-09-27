package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.cms.core.TBusiPconsultation;

import java.util.HashMap;
import java.util.List;

/**
 ****标准DAO
 *此Dao接口由TBusiPconsultation类自动生成
 *@author zhouxx
 *@since 2016-05-09 20:33:15
 */

public interface BusiPconsultationManager {
    public TBusiPconsultation	getBusiPconsultation(TBusiPconsultation bean);
    public List<TBusiPconsultation>	getBusiPconsultationList(TBusiPconsultation bean);
    public HashMap	getBusiPconsultationGroup(TBusiPconsultation bean);
    public TBusiPconsultation	addBusiPconsultation(TBusiPconsultation bean);
    public TBusiPconsultation	updateBusiPconsultation(TBusiPconsultation bean);
    public TBusiPconsultation	deleteBusiPconsultation(TBusiPconsultation bean);

}
