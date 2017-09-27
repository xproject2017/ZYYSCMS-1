package com.cms.core.dao.foundcms;

import com.cms.core.bean.cms.core.TBusiTestexercise;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 浩 on 2017/9/11.
 */
public interface BusiTestexerciseDao {
    public TBusiTestexercise	getBusiTestexercise(TBusiTestexercise	bean);
    public List<TBusiTestexercise> getBusiTestexerciseList(TBusiTestexercise	bean);
    public HashMap getBusiTestexerciseGroup(TBusiTestexercise	bean);
    public int	addBusiTestexercise(TBusiTestexercise	bean);
    public int	updateBusiTestexercise(TBusiTestexercise	bean);
    public int	deleteBusiTestexercise(TBusiTestexercise bean);
}
