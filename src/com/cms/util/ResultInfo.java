package com.cms.util;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2014/8/6
 * Time: 17:43
 */
public interface ResultInfo {
    public static final int Fail = 1;//数据库操作失败
    public static final int Success = 0;//数据库操作成功，如果是查询操作，查询结果不为空
    public static final int NullOutput = 2;                   //查询操作成功，但查询结果为空
    public static final int RecordExist = 3;                   //记录已存在
    public static final int RecordNotExist = 4;                //记录不存在
    public static final int ErrorPWD = 5;                   //密码错误
    public static final int ErrorDeleteJob = 6;                   //删除任务失败
    public static final int ErrorToken = 7;                   //无效的Token
    public static final int ErrorTokenTimeout = 8;                   //无效的Token

    public static final String ErrorRecordExist                    ="记录已存在";
    public static final String ErrorNoDataFound                    ="数据不存在";//查询操作成功，但查询结果为空
    public static final String ErrorQuery                                  = "查询失败";//"数据库查询失败";
    public static final String ErrorDBOperation = "操作失败";//"数据库操作失败";
    public static final String ErrorOperation = "查询失败，请稍后再试！";//"数据库操作失败";
    public static final String ErrorPassword = "密码错误";//"数据库操作失败";
    public static final String ErrorDeleteJobInfo = "存在已经被接受的任务，不允许删除！";                   //删除任务失败
    public static final String PLAN_NAME_EXIST_ERROR = "该计划名称已存在";
    public static final String PLAN_YEAR_EXIST_ERROR = "该年份的计划已存在";

    public static final String ErrorTokenStr ="无效的Token";
    public static final String ErrorTokenTimeoutStr ="Token超时";
    public static final String ErrorParameter ="输入参数有误";
    public static final String ROLE_NAME_EXIST ="该角色名称已存在";


    public static final String USER_PWD_ERROR ="用户名或密码错误";
    public static final String USER_DISABLE_ERROR ="该账户已被禁用";
    public static final String UPDATE_FILE_FAIL ="更新文件失败";
    public static final String RESULT_MORE_THAN_TOTAL ="任务已完成，无法提交！";
    public static final String UPDATE_ERROR_DEAL_TIME_MORE_THAN_7DAYS = "该投诉处理已经超过7天，不能修改";
    public static final String DELETE_ERROR_DEAL_TIME_MORE_THAN_7DAYS = "该投诉处理已经超过7天，不能删除";
    public static final String DOWNLOAD_FILE_ERROR = "没有可下载的文件";
    public static final String NOTHING_UPDATE_PLAN_ERROR = "该计划没有任何修改";
    public static final String SCREEN_NETWORK_NAME_EXIST_ERROR = "该名称已存在";
    public static final String SCREEN_NETWORK_FILE_NOT_EXIST = "文件不存在";
    public static final String SCREEN_NETWORK_WRONG_FORMAT = "格式不正确";

}
