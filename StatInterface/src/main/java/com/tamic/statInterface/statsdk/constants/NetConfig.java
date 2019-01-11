package com.tamic.statInterface.statsdk.constants;

/**
 * Created by Tc on 2016-03-25.
 */
public class NetConfig {

    /**
     * constructor
     */
    private NetConfig() {

    }


    /**
     * You Url
     */
    public static String BASE_URL ="https://m.wisecofincloud.com";
    /**
     * You Url
     */
    public static String ONLINE_URL ="https://m.wisecofincloud.com/api/eventlog/sendlog";

    /**
     * 数据Url
     */
    public static String EVENTLOG = BASE_URL + "/api/eventlog/sendlog" ;



    /**
     * 临时id
     */
    public static final String ENVBASE =BASE_URL+"/api/eventlog/envbase";

    /**
     * 检查版本
     */
    public static final String GETCONFIGVERSION =BASE_URL+"/api/eventlog/getconfigversion";
    /**
     * 配置信息
     */
    public static final String GETCONFIG =BASE_URL+"/api/eventlog/getconfig";

    /**
     * 请求超时时间
     */
    public static final int TIME_OUT = 1000 * 50 * 1;

    /** 重新请求时间 */
    public static final int RETRY_TIMES = 3;

    /** HEADERS_KEY */
    public static final String HEADERS_KEY = "data_head";

    /** key*/
    public static final String PARAMS_KEY = "data_body";


}
