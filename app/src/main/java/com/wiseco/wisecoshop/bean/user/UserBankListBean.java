package com.wiseco.wisecoshop.bean.user;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wiseco on 2018/12/27.
 */

public  class UserBankListBean {
    /**
     * id : 12
     * name : 民生银行
     * shortName : 民生银行
     */

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("shortName")
    private String shortName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}