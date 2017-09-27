package com.cms.util;


import com.cms.core.bean.util.TagTypeBean;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2014/5/20
 * Time: 11:13
 */
public class DictUtils {

    private static Map<String,List<TagTypeBean>> dictsCache;

    public void setDictsCache(Map<String, List<TagTypeBean>> dictsCache) {
        DictUtils.dictsCache = dictsCache;
    }

    public static String getTagNameByID(String tagid,int tagvalue){
        try {
            List<TagTypeBean> tags = dictsCache.get(tagid);
            String tagVal = String.valueOf(tagvalue);
            for (TagTypeBean tagTypeBean : tags) {
                if (tagTypeBean.getValue().equals(tagVal))
                    return tagTypeBean.getTagname();
            }
        }catch (Exception e) {
            return "";
        }
        return null;
    }
    public static String getTagNameByID(String tagid,String tagvalue){
        try {
            List<TagTypeBean> tags = dictsCache.get(tagid);
            for (TagTypeBean tagTypeBean : tags) {
                if (tagTypeBean.getValue().equals(tagvalue))
                    return tagTypeBean.getTagname();
            }
        }catch (Exception e) {
            return "";
        }
        return null;
    }

    public static List<TagTypeBean> getAllTag(String tagid){
        return dictsCache.get(tagid);
    }
}
