package com.wiseco.wisecoshop.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wiseco on 2018/12/28.
 */


public  class BannerBean {
    /**
     * id : 36
     * name : newAppBanner
     * imageUrl : https://m.wisecofincloud.com/images/common/banner/bannernewapp.png
     * platform : 1
     * position : bn
     */

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("platform")
    private String platform;
    @SerializedName("position")
    private String position;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}