package com.wiseco.wisecoshop.bean.goods;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wiseco on 2018/12/28.
 */

public  class CcRLBean {
    /**
     * prdclass : CC
     * maxlimit : 50000
     * code : CMBCHINA-CC0004
     * advantage : 首年免年费|刷卡额度最高5万元|享5折美食，9元兑电影券
     * name : 招行腾讯视频VIP联名卡
     * approvaltime : 30分钟
     * smallicon : /images/creditcard/CMBCHINA-CC0004.png
     * bankicon : /images/bankicon/cmbchina.png
     * keyword : 1|达标好礼,2|吃喝玩乐,4|多重优惠,5|会员赠送
     */

    @SerializedName("prdclass")
    private String prdclass;
    @SerializedName("maxlimit")
    private int maxlimit;
    @SerializedName("code")
    private String code;
    @SerializedName("advantage")
    private String advantage;
    @SerializedName("name")
    private String name;
    @SerializedName("approvaltime")
    private String approvaltime;
    @SerializedName("smallicon")
    private String smallicon;
    @SerializedName("bankicon")
    private String bankicon;
    @SerializedName("keyword")
    private String keyword;

    public String getPrdclass() {
        return prdclass;
    }

    public void setPrdclass(String prdclass) {
        this.prdclass = prdclass;
    }

    public int getMaxlimit() {
        return maxlimit;
    }

    public void setMaxlimit(int maxlimit) {
        this.maxlimit = maxlimit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAdvantage() {
        return advantage;
    }

    public void setAdvantage(String advantage) {
        this.advantage = advantage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApprovaltime() {
        return approvaltime;
    }

    public void setApprovaltime(String approvaltime) {
        this.approvaltime = approvaltime;
    }

    public String getSmallicon() {
        return smallicon;
    }

    public void setSmallicon(String smallicon) {
        this.smallicon = smallicon;
    }

    public String getBankicon() {
        return bankicon;
    }

    public void setBankicon(String bankicon) {
        this.bankicon = bankicon;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}