package com.wiseco.wisecoshop.bean.discount;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wiseco on 2018/12/27.
 */

public class PromActivityBean {
    /**
     * id : adtdtdtdd
     * title : 9积分星巴克
     * way : 在中国银行「缤纷生活」购劵
     * bigimgurl : https://qnpic.billbear.cn/Fi_DTc8PJ0CCv6y1i7Sl25ReseH0
     * bankname : 中国银行
     * startdate : 1534032488000
     * enddate : 1541981288000
     * category1 : 美食
     * category2 : 咖啡
     * type : 打折
     * payment : 在线支付
     * paybrand : 银联
     * availabletime : 每天 10:00-23:55
     * availablecity : 上海
     * note : 随时退、过期退、免预约
     * subjecttype : 线上
     * content : 活动期间，招商银行信用卡持卡人**每周三上午 10:00** 至招行「掌上生活 APP」50 元购「味多美」100 元代金券
     */

    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("way")
    private String way;
    @SerializedName("bigimgurl")
    private String bigimgurl;
    @SerializedName("bankname")
    private String bankname;
    @SerializedName("startdate")
    private long startdate;
    @SerializedName("enddate")
    private long enddate;
    @SerializedName("category1")
    private String category1;
    @SerializedName("category2")
    private String category2;
    @SerializedName("type")
    private String type;
    @SerializedName("payment")
    private String payment;
    @SerializedName("paybrand")
    private String paybrand;
    @SerializedName("availabletime")
    private String availabletime;
    @SerializedName("availablecity")
    private String availablecity;
    @SerializedName("note")
    private String note;
    @SerializedName("subjecttype")
    private String subjecttype;
    @SerializedName("content")
    private String content;

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

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getBigimgurl() {
        return bigimgurl;
    }

    public void setBigimgurl(String bigimgurl) {
        this.bigimgurl = bigimgurl;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public long getStartdate() {
        return startdate;
    }

    public void setStartdate(long startdate) {
        this.startdate = startdate;
    }

    public long getEnddate() {
        return enddate;
    }

    public void setEnddate(long enddate) {
        this.enddate = enddate;
    }

    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }

    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPaybrand() {
        return paybrand;
    }

    public void setPaybrand(String paybrand) {
        this.paybrand = paybrand;
    }

    public String getAvailabletime() {
        return availabletime;
    }

    public void setAvailabletime(String availabletime) {
        this.availabletime = availabletime;
    }

    public String getAvailablecity() {
        return availablecity;
    }

    public void setAvailablecity(String availablecity) {
        this.availablecity = availablecity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSubjecttype() {
        return subjecttype;
    }

    public void setSubjecttype(String subjecttype) {
        this.subjecttype = subjecttype;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
