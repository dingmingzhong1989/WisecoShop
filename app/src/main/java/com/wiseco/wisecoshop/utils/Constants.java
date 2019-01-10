package com.wiseco.wisecoshop.utils;

/**
 * Created by wiseco on 2018/11/6.
 */

public class Constants {

    //COMMENT

    public static String COMMENT_EVENT_ADVERTISING = "记录用户点击首页广告行为";
    public static String COMMENT_EVENT_USER = "用户点击“我的”个人信息";
    public static String COMMENT_EVENT_START_VERIFY = "用户开始输入手机身份证";
    public static String COMMENT_EVENT_VERIFY_CODE_SUCCESS = "用户填写验证码并验证成功";
    public static String COMMENT_EVENT_VERIFY_CODE_FAILED = "用户填写验证码并验证失败";
    public static String COMMENT_EVENT_VERIFY_ID_FAILED = "用户是未完成身份证信息填写";
    public static String COMMENT_EVENT_VERIFY_ID_SUCCESS = "用户是完成身份证信息填写";
    public static String COMMENT_EVENT_PRODUCT_SELECT_MONEY = "用户选择贷款";
    public static String COMMENT_EVENT_PRODUCT_SELECT_CARDS = "用户选择了信用卡";
    public static String COMMENT_EVENT_PRODUCT_FILTER = "记录用户选择筛选项组合";
    public static String COMMENT_EVENT_PRODUCT_DETAIL = "用户点击产品页了解详情";
    public static String COMMENT_EVENT_APPAY_CARD = "用户申请了信用卡";
    public static String COMMENT_EVENT_APPAY_MONEY = "用户申请了现金分期";


    //CONTENT
    public static String CONTENT_EVENT_ADVERTISING = "ad_click";
    public static String CONTENT_EVENT_USER = "personal_info";
    public static String CONTENT_EVENT_START_VERIFY = "verify_init";
    public static String CONTENT_EVENT_VERIFY_CODE = "verify_code";
    public static String CONTENT_EVENT_VERIFY_ID= "verify_result";
    public static String CONTENT_EVENT_PRODUCT_SELECT = "product_select";
    public static String CONTENT_EVENT_PRODUCT_FILTER = "product_filter";
    public static String CONTENT_EVENT_PRODUCT_DETAIL = "info_click";

    public static String CONTENT_EVENT_APPAY = "appay_click";

    public static String LATITUDE = "39.924090";


    public static String LONGITUDE = "116.407737";

}
