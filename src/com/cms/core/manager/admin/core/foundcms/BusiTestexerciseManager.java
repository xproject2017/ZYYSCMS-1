package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.cms.core.TBusiTestexercise;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 浩 on 2017/9/11.
 */
public interface BusiTestexerciseManager {

    public  TBusiTestexercise getBusiTestexercise(TBusiTestexercise bean);
    public HashMap getBusiTestexerciseGroup(TBusiTestexercise bean);
    public List<TBusiTestexercise> getBusiTestexerciseList(TBusiTestexercise bean);
    public  TBusiTestexercise addBusiTestexercise(TBusiTestexercise bean);
    public  TBusiTestexercise updateBusiTestexercise(TBusiTestexercise bean);
    public  TBusiTestexercise deleteBusiTestexercise(TBusiTestexercise bean);
}
