package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.cms.core.TBusiExercises;
import com.cms.core.bean.cms.core.TBusiTestpaper;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 浩 on 2017/9/11.
 */
public interface BusiTestpaperManager {

    public TBusiTestpaper getBusiTestpaper(TBusiTestpaper bean);
    public HashMap  getBusiTestpaperGroup(TBusiTestpaper bean);
    public List<TBusiTestpaper> getBusiTestpaperList(TBusiTestpaper bean);
    public TBusiTestpaper  addBusiTestpaper(TBusiTestpaper bean);
    public TBusiTestpaper updateBusiTestpaper(TBusiTestpaper bean);
    public TBusiTestpaper deleteBusiTestpaper(TBusiTestpaper bean);

    public List<TBusiExercises>getExercises(TBusiTestpaper bean);
    public List<TBusiExercises>getNoexercises(TBusiTestpaper bean);


}
