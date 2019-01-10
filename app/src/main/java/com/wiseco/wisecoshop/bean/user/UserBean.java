package com.wiseco.wisecoshop.bean.user;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wiseco on 2018/12/25.
 */

public class UserBean {
    /**
     * gendar : true
     * userWalletNum : 0
     * idcardStatus : true
     * code : S
     * constellation : 白羊座
     * encPhone : 188****3240
     * userCardNum : 1
     */

    @SerializedName("gendar")
    private boolean gendar;
    @SerializedName("userWalletNum")
    private int userWalletNum;
    @SerializedName("idcardStatus")
    private boolean idcardStatus;
    @SerializedName("code")
    private String code;
    @SerializedName("constellation")
    private String constellation;
    @SerializedName("encPhone")
    private String encPhone;
    @SerializedName("userCardNum")
    private int userCardNum;

    public boolean isGendar() {
        return gendar;
    }

    public void setGendar(boolean gendar) {
        this.gendar = gendar;
    }

    public int getUserWalletNum() {
        return userWalletNum;
    }

    public void setUserWalletNum(int userWalletNum) {
        this.userWalletNum = userWalletNum;
    }

    public boolean isIdcardStatus() {
        return idcardStatus;
    }

    public void setIdcardStatus(boolean idcardStatus) {
        this.idcardStatus = idcardStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getEncPhone() {
        return encPhone;
    }

    public void setEncPhone(String encPhone) {
        this.encPhone = encPhone;
    }

    public int getUserCardNum() {
        return userCardNum;
    }

    public void setUserCardNum(int userCardNum) {
        this.userCardNum = userCardNum;
    }
}
