package com.wiseco.wisecoshop.bean.discount;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wiseco on 2018/12/27.
 */

public  class ActsBean {
    /**
     * id : 5a05139bc95d7a4a36d9748f
     * title : 9积分星巴克
     * bankName : 中信银行
     * category : 餐饮
     * bigImgUrl : 活动图片
     * availableTime : 每天 10:00-23:55
     */

    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("bankName")
    private String bankName;
    @SerializedName("category")
    private String category;
    @SerializedName("bigImgUrl")
    private String bigImgUrl;
    @SerializedName("availableTime")
    private String availableTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBigImgUrl() {
        return bigImgUrl;
    }

    public void setBigImgUrl(String bigImgUrl) {
        this.bigImgUrl = bigImgUrl;
    }

    public String getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(String availableTime) {
        this.availableTime = availableTime;
    }
}
