package com.cms.util;

/**
 * Created by Administrator on 2016/3/19.
 */
public interface SysFlagUtil {

    public final static String CATOKEN_Attribute = "CA-Token";

    public static final int role_support = 1;//support
    public static final int role_admin = 2;//admin
    public static final int role_executive = 3;//行政管理

    /*
    PLATFORMFLAG 所属平台(1账户管理 2检测网络 3爬虫
            4风险管理 5投诉管理 6智能统计 7取证中心 8门户管理
            9数据采集)
    */
    public static final int PLATFORM_jcwl_0 = 0;//2检测网络
    public static final int PLATFORM_xxcj_1 = 1;//3信息采集
    public static final int PLATFORM_fzgl_2 = 2;//4风险管理
    public static final int PLATFORM_tsjb_3 = 3;//5投诉管理
    public static final int PLATFORM_sjfx_4 = 4;//6智能统计
    public static final int PLATFORM_qzzx_5 = 5;//7取证中心
    public static final int PLATFORM_zszx_6 = 6;//7展示中心
    public static final int PLATFORM_mhgl_7 = 7;//8门户管理
    public static final int PLATFORM_zhgl_8 = 8;//9账户管理


    /*
     局方id:1杭州,2上海,3平潭,4天津,5广州,6重庆,7宁波,8郑州,9深圳,10福州
    */
    public static final int org_hz_1 = 1;
    public static final int org_sh_2 = 2;
    public static final int org_pt_3 = 3;
    public static final int org_tj_4 = 4;
    public static final int org_gz_5 = 5;
    public static final int org_cq_6 = 6;
    public static final int org_nb_7 = 7;
    public static final int org_zz_8 = 8;
    public static final int org_sz_9 = 9;
    public static final int org_fz_10 = 10;

    public static final int nodeid_jcrw = 6;//检测任务的nodeid
    public static final int nodeid_jcjg = 5;//检测结果的nodeid
    public static final int nodeid_fxpd = 36;//风险判断的nodeid
    public static final int nodeid_zjyp = 37;//专家研判的nodeid
    public static final int nodeid_ldjc = 38;//领导决策的nodeid

    public static final int USER_STAUTS_DISABLE = 0;//用户状态 不启用

    public static final String USER_PERMISSION_WRITE = "111";//用户权限 读写权限
    public static final String USER_PERMISSION_READ = "000";//用户权限 读权限

    /*
    * TIPTYPEtinyint(4)消息类型： 0扣分消息 1任务状态变更消息  --系统定义
      TIPFROMtinyint(4)消息来源：1监测网络 0系统消息  --系统定义
      SCOREint(11)分值  --系统定义
    * */
    public static final int Unread   = 0;//消息已读
    public static final int READ  = 1;//消息已读

    public static final Integer TIPS_FROM_SYS = 0;//消息来源 0：系统消息
    public static final Integer TIPS_FROM_JCWL = 1;//消息来源 1：检测网络
    public static final Integer TIPS_FROM_FXJC = 2;//消息来源 2：风险监测

    public static final int TIP_TYPE_SCR  = 0;//分数变动
    public static final int TIP_TYPE_CHG  = 1;//结果状态变更
    public static final int TIP_TYPE_SYS  = 2;//系统消息
    public static final int TIP_TYPE_ROOLBACK = 3;//申请回退
    public static final int TIP_TYPE_SCR_lt60  = 4;//分数变动小于60分的消息x

    //0未接受 1已拒绝 2已关闭 3已接受 4已完成
    public static final int unaccepted = 0;//未接受
    public static final int refused=1; //申请回退  --在“申请回退”状态，机构可以取消回退，状态置为已接受。
    public static final int closed=2; //已关闭 --
    public static final int accepted  = 3;//已接受  --局方拒绝申请，状态变成已接受。（针对这个时刻，该计划下所有机构申请回退的记录，全部置为已接受。）
    public static final int finished = 4;//已完成
    public static final int deleted = -1;//已删除

    //计划类型 1年度计划 2专项计划
    public static final int annual_plan=1; //1年度计划  A-YYYY-01
    public static final int special_plan=2; //2专项计划 B-YYYY-01

    //风险确认状态
    public static final int risk_confirm_unconfirm=0;//未确认
    public static final int risk_confirm_finished=1;//已确认
    public static final int risk_confirm_exp=2;//专家评判
    public static final int risk_confirm_lead=3;//领导决策

    //风险处置状态
    public static final int risk_disp_undisp=0;//未处置
    public static final int risk_disp_disping=1;//处置中
    public static final int risk_disp_finished=2;//已完成

    //风险等级
    public static final int risk_level_low=1;//低
    public static final int risk_level_mid=2;//中
    public static final int risk_level_high=3;//高

    //影响范围
    public static final int risk_rng_low=0;//小
    public static final int risk_rng_mid=1;//中
    public static final int risk_rng_high=2;//大

    //风险来源
    public static final int risk_source_check=1;//监测网络
    public static final int risk_source_comp=2;//投诉举报
    public static final int risk_source_net=3;//网络舆情
    public static final int risk_source_data=4;//大数据分析

    //风险处理人
    public static final int risk_by_comm=1;//一般
    public static final int risk_by_exp=2;//专家
    public static final int risk_by_lead=3;//领导

    //风险处理方式
    public static final int risk_method_confirm=1;//评判
    public static final int risk_method_exp=2;//专家
    public static final int risk_method_lead=3;//领导
    public static final int risk_method_rollback=4;//驳回

    //评判来源
    public static final int risk_consource_comm=1;//评判
    public static final int risk_consource_lead=2;//领导
    public static final int risk_consource_store=3;//直接入库

    //投诉类型
    public static final int risk_comp_net=0;//网络投诉
    public static final int risk_comp_cell=1;//电话投诉

    //展示中心来源定义
    public static final int SHOW_CENTER_SOURCE_JCWL=1;//检测网络

 }
