package com.cms.core.manager.admin.core.foundcms;

import com.cms.core.bean.cms.core.TBusiExercises;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 浩 on 2017/9/11.
 */
public interface BusiExercisesManager {
    public TBusiExercises getBusiExercises(TBusiExercises bean);
    public HashMap getBusiExercisesGroup(TBusiExercises bean);
    public List<TBusiExercises> getBusiExercisesList(TBusiExercises bean);
    public TBusiExercises addBusiExercises(TBusiExercises bean);
    public TBusiExercises updateBusiExercises(TBusiExercises bean);
    public TBusiExercises deleteBusiExercises(TBusiExercises bean);
}
