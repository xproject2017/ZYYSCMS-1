package com.cms.core.dao.foundcms;

import com.cms.core.bean.cms.core.TBusiTestpapervip;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 浩 on 2017/9/11.
 */
public interface BusiTestpapervipDao {

    public TBusiTestpapervip	getBusiTestpapervip(TBusiTestpapervip	bean);
    public List<TBusiTestpapervip> getBusiTestpapervipList(TBusiTestpapervip	bean);
    public HashMap getBusiTestpapervipGroup(TBusiTestpapervip	bean);
    public int	addBusiTestpapervip(TBusiTestpapervip	bean);
    public int	updateBusiTestpapervip(TBusiTestpapervip	bean);
    public int	deleteBusiTestpapervip(TBusiTestpapervip bean);
}
