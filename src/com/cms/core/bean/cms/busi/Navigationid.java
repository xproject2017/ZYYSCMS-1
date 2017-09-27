package com.cms.core.bean.cms.busi;

import com.cms.core.bean.cms.core.TBusiNavigationid1;
import com.cms.core.bean.cms.core.TBusiNavigationid2;

import java.util.List;

/**
 * Created by Administrator on 2016/5/17.
 */
public class Navigationid   {

    private List<TBusiNavigationid1> busiNavigationid1;
    private List<TBusiNavigationid2> busiNavigationid2;

    public List<TBusiNavigationid1> getBusiNavigationid1() {
        return busiNavigationid1;
    }

    public void setBusiNavigationid1(List<TBusiNavigationid1> busiNavigationid1) {
        this.busiNavigationid1 = busiNavigationid1;
    }

    public List<TBusiNavigationid2> getBusiNavigationid2() {
        return busiNavigationid2;
    }

    public void setBusiNavigationid2(List<TBusiNavigationid2> busiNavigationid2) {
        this.busiNavigationid2 = busiNavigationid2;
    }
}
