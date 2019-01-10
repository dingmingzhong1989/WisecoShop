package com.wiseco.wisecoshop.bean.user;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wiseco on 2018/12/24.
 */

public class UserCardListBean {
    /**
     * id : 1
     * userId : 10000000413
     * bankId : 2
     * typeId : 4
     * status : true
     * cardEndNum : 1234
     * repayDay : 16
     * gmtCreate : 1545465816000
     * gmtModify : 1545478590000
     */

    @SerializedName("id")
    private int id;
    @SerializedName("userId")
    private long userId;
    @SerializedName("bankId")
    private int bankId;
    @SerializedName("typeId")
    private int typeId;
    @SerializedName("status")
    private boolean status;
    @SerializedName("cardEndNum")
    private String cardEndNum;
    @SerializedName("repayDay")
    private int repayDay;
    @SerializedName("gmtCreate")
    private long gmtCreate;
    @SerializedName("gmtModify")
    private long gmtModify;

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

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCardEndNum() {
        return cardEndNum;
    }

    public void setCardEndNum(String cardEndNum) {
        this.cardEndNum = cardEndNum;
    }

    public int getRepayDay() {
        return repayDay;
    }

    public void setRepayDay(int repayDay) {
        this.repayDay = repayDay;
    }

    public long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public long getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(long gmtModify) {
        this.gmtModify = gmtModify;
    }
}
