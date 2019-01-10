package com.wiseco.wisecoshop.bean.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by wiseco on 2018/12/24.
 */

public class BankListBean implements Serializable {
    /**
     * id : 11
     * code : CCB
     * hasCC : true
     * name : 建设银行
     * shortname : 建设银行
     * icon : /images/bankicon/ccb.png
     */

    @SerializedName("id")
    private int id;
    @SerializedName("code")
    private String code;
    @SerializedName("hasCC")
    private boolean hasCC;
    @SerializedName("name")
    private String name;
    @SerializedName("shortname")
    private String shortname;
    @SerializedName("icon")
    private String icon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isHasCC() {
        return hasCC;
    }

    public void setHasCC(boolean hasCC) {
        this.hasCC = hasCC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}