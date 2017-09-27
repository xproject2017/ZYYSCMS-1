package com.cms.core.dao.foundcms;

import com.cms.core.bean.cms.core.TBusiExercises;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 浩 on 2017/9/11.
 */
public interface BusiExercisesDao {
        public TBusiExercises	getBusiExercises(TBusiExercises	bean);
        public List<TBusiExercises> getBusiExercisesList(TBusiExercises	bean);
        public HashMap getBusiExercisesGroup(TBusiExercises	bean);
        public int	addBusiExercises(TBusiExercises	bean);
        public int	updateBusiExercises(TBusiExercises	bean);

        public  int deleteBusiExercises(TBusiExercises bean);
}
