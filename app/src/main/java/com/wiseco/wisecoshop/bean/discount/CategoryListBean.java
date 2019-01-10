package com.wiseco.wisecoshop.bean.discount;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by wiseco on 2018/12/28.
 */

public  class CategoryListBean implements Serializable {
    /**
     * id : 38
     * name : 美食
     * mongoId : 579828420926a85e90f2bbe1
     * icon : /images/common/category/meishi.png
     */

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("mongoId")
    private String mongoId;
    @SerializedName("icon")
    private String icon;

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

    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
