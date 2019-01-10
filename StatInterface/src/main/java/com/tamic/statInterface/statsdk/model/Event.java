package com.tamic.statInterface.statsdk.model;

import java.util.List;

/**
 * 对应的event事件
 * Created by ZHANGLIANG098 on 2016-03-24.
 */
public class Event {
    private String user_id ;
    private String referer_page_id ;
    private String uid ;
    private String city_id ;
    private String action_id ;
    private String operation_time ;
    private List<KeyValueBean> parameter ;
    private String target_id;
    private String comment;

    public String getTarget_id() {
        return target_id;
    }

    public void setTarget_id(String target_id) {
        this.target_id = target_id;
    }
    public String get_user_id() {
        return user_id;
    }

    public void set_user_id(String user_id) {
        this.user_id = user_id;
    }
    public String get_comment() {
        return comment;
    }

    public void set_comment(String comment) {
        this.comment = comment;
    }
    public String getReferer_page_id() {
        return referer_page_id;
    }

    public void setReferer_page_id(String referer_page_id) {
        this.referer_page_id = referer_page_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getAction_id() {
        return action_id;
    }

    public void setAction_id(String action_id) {
        this.action_id = action_id;
    }

    public String getOperation_time() {
        return operation_time;
    }

    public void setOperation_time(String operation_time) {
        this.operation_time = operation_time;
    }

    public List<KeyValueBean> getParameter() {
        return parameter;
    }

    public void setParameter(List<KeyValueBean> parameter) {
        this.parameter = parameter;
    }
}
