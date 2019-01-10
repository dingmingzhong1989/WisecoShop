package com.wiseco.wisecoshop.bean.ticket;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wiseco on 2018/12/25.
 */

public  class WalletListBean {
    /**
     * id : 70
     * userId : 10000000405
     * name : 优酷会员
     * value : 6个月专享
     * isUsed : false
     * picUrl : na
     * obtainReason : 双蛋大礼
     * cardNo : 123
     * cardPwd : 123
     * useStartTime : 1544580671000
     * useEndTime : 1546135871000
     * gmtCreate : 1545703871000
     */

    @SerializedName("id")
    private int id;
    @SerializedName("userId")
    private long userId;
    @SerializedName("name")
    private String name;
    @SerializedName("value")
    private String value;
    @SerializedName("isUsed")
    private boolean isUsed;
    @SerializedName("picUrl")
    private String picUrl;
    @SerializedName("obtainReason")
    private String obtainReason;
    @SerializedName("cardNo")
    private String cardNo;
    @SerializedName("cardPwd")
    private String cardPwd;
    @SerializedName("useStartTime")
    private long useStartTime;
    @SerializedName("useEndTime")
    private long useEndTime;
    @SerializedName("gmtCreate")
    private long gmtCreate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isIsUsed() {
        return isUsed;
    }

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getObtainReason() {
        return obtainReason;
    }

    public void setObtainReason(String obtainReason) {
        this.obtainReason = obtainReason;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardPwd() {
        return cardPwd;
    }

    public void setCardPwd(String cardPwd) {
        this.cardPwd = cardPwd;
    }

    public long getUseStartTime() {
        return useStartTime;
    }

    public void setUseStartTime(long useStartTime) {
        this.useStartTime = useStartTime;
    }

    public long getUseEndTime() {
        return useEndTime;
    }

    public void setUseEndTime(long useEndTime) {
        this.useEndTime = useEndTime;
    }

    public long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
