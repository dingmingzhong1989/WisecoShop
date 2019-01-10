package com.tamic.statInterface.statsdk.model;

import java.util.List;

/**
 * 上传数据对象bean
 * Created by ZHANGLIANG098 on 2016-03-24.
 */
public class DataBlock {
    private List<AppAction> app_action ;
    private List<Page> page ;
    private List<Event> eventlog ;

    public List<AppAction> getApp_action() {
        return app_action;
    }

    public void setApp_action(List<AppAction> app_action) {
        this.app_action = app_action;
    }

    public List<Page> getPage() {
        return page;
    }

    public void setPage(List<Page> page) {
        this.page = page;
    }

    public List<Event> getEventlog() {
        return eventlog;
    }

    public void setEventlog(List<Event> eventlog) {
        this.eventlog = eventlog;
    }
}
