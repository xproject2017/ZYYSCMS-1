package com.cms.core.dao.foundcms;

import com.cms.core.bean.cms.core.TBusiExercises;
import com.cms.core.bean.cms.core.TBusiTestpaper;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 浩 on 2017/9/11.
 */
public interface BusiTestpaperDao {
    public TBusiTestpaper	getBusiTestpaper(TBusiTestpaper	bean);
    public List<TBusiTestpaper> getBusiTestpaperList(TBusiTestpaper	bean);
    public HashMap getBusiTestpaperGroup(TBusiTestpaper	bean);
    public int	addBusiTestpaper(TBusiTestpaper	bean);
    public int	updateBusiTestpaper(TBusiTestpaper	bean);
    public int	deleteBusiTestpaper(TBusiTestpaper bean);
    public List<TBusiExercises>getExercises(TBusiTestpaper bean);
    public List<TBusiExercises>getNoexercises(TBusiTestpaper bean);
}
