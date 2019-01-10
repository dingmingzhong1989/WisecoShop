package com.wiseco.wisecoshop.bean.goods;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wiseco on 2018/12/28.
 */
public  class ClRLBean {
    /**
     * prdclass : CL
     * maxlimit : 200000
     * code : HRBB-CL0001
     * minrate : 日息0.03%
     * advantage : 纯信用,无抵押,无担保,月息低至0.49%
     * name : 哈银有卡贷
     * approvaltime : 最快1分钟
     * term : 6/12/18/24月
     * bankicon : /images/bankicon/hrbb.png
     * keyword : 1|纯信用,2|无抵押,3|无担保,4|快速审批,5|秒速放款
     */

    @SerializedName("prdclass")
    private String prdclass;
    @SerializedName("maxlimit")
    private int maxlimit;
    @SerializedName("code")
    private String code;
    @SerializedName("minrate")
    private String minrate;
    @SerializedName("advantage")
    private String advantage;
    @SerializedName("name")
    private String name;
    @SerializedName("approvaltime")
    private String approvaltime;
    @SerializedName("term")
    private String term;
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

    public String getMinrate() {
        return minrate;
    }

    public void setMinrate(String minrate) {
        this.minrate = minrate;
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

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
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
