package com.tamic.statInterface.statsdk.model;

import java.util.List;

/**
 * Created by wiseco on 2019/1/7.
 */

public class TargetBean {

    /**
     * targetList : ["P01_T03"]
     * code : S
     * actionList : ["12"]
     */


    private String code;

    private List<String> targetList;

    private List<String> actionList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getTargetList() {
        return targetList;
    }

    public void setTargetList(List<String> targetList) {
        this.targetList = targetList;
    }

    public List<String> getActionList() {
        return actionList;
    }

    public void setActionList(List<String> actionList) {
        this.actionList = actionList;
    }
}
