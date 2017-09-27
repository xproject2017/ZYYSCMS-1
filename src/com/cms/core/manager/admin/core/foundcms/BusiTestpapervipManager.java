package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.cms.core.TBusiTestpapervip;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 浩 on 2017/9/11.
 */
public interface BusiTestpapervipManager {

    public TBusiTestpapervip getBusiTestpapervip(TBusiTestpapervip bean);
    public HashMap getBusiTestpapervipGroup(TBusiTestpapervip bean);
    public List<TBusiTestpapervip> getBusiTestpapervipList(TBusiTestpapervip bean);
    public TBusiTestpapervip addBusiTestpapervip(TBusiTestpapervip bean);
    public TBusiTestpapervip updateBusiTestpapervip(TBusiTestpapervip bean);
    public TBusiTestpapervip deleteBusiTestpapervip(TBusiTestpapervip bean);
}
