package com.wiseco.wisecoshop.bean.ticket;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wiseco on 2018/12/25.
 */

public class TicketNoUsedBean {
    /**
     * code : S
     * pageNo : 1
     * walletList : [{"id":70,"userId":10000000405,"name":"优酷会员","value":"6个月专享","isUsed":false,"picUrl":"na","obtainReason":"双蛋大礼","cardNo":"123","cardPwd":"123","useStartTime":1544580671000,"useEndTime":1546135871000,"gmtCreate":1545703871000},{"id":69,"userId":10000000405,"name":"餐饮专享","value":"100元满减","isUsed":false,"picUrl":"no","obtainReason":"双蛋大礼","cardNo":"123","cardPwd":"123","useStartTime":1544580671000,"useEndTime":1546135871000,"gmtCreate":1545703871000},{"id":66,"userId":10000000405,"name":"优酷会员","value":"6个月专享","isUsed":false,"picUrl":"na","obtainReason":"双蛋大礼","cardNo":"123","cardPwd":"123","useStartTime":1544580671000,"useEndTime":1546135871000,"gmtCreate":1545703871000},{"id":65,"userId":10000000405,"name":"餐饮专享","value":"100元满减","isUsed":false,"picUrl":"no","obtainReason":"双蛋大礼","cardNo":"123","cardPwd":"123","useStartTime":1544580671000,"useEndTime":1546135871000,"gmtCreate":1545703871000},{"id":62,"userId":10000000405,"name":"优酷会员","value":"6个月专享","isUsed":false,"picUrl":"na","obtainReason":"双蛋大礼","cardNo":"123","cardPwd":"123","useStartTime":1544580671000,"useEndTime":1546135871000,"gmtCreate":1545703871000},{"id":61,"userId":10000000405,"name":"餐饮专享","value":"100元满减","isUsed":false,"picUrl":"no","obtainReason":"双蛋大礼","cardNo":"123","cardPwd":"123","useStartTime":1544580671000,"useEndTime":1546135871000,"gmtCreate":1545703871000},{"id":58,"userId":10000000405,"name":"优酷会员","value":"6个月专享","isUsed":false,"picUrl":"na","obtainReason":"双蛋大礼","cardNo":"123","cardPwd":"123","useStartTime":1544580671000,"useEndTime":1546135871000,"gmtCreate":1545703871000},{"id":57,"userId":10000000405,"name":"餐饮专享","value":"100元满减","isUsed":false,"picUrl":"no","obtainReason":"双蛋大礼","cardNo":"123","cardPwd":"123","useStartTime":1544580671000,"useEndTime":1546135871000,"gmtCreate":1545703871000},{"id":54,"userId":10000000405,"name":"优酷会员","value":"6个月专享","isUsed":false,"picUrl":"na","obtainReason":"双蛋大礼","cardNo":"123","cardPwd":"123","useStartTime":1544580671000,"useEndTime":1546135871000,"gmtCreate":1545703871000},{"id":53,"userId":10000000405,"name":"餐饮专享","value":"100元满减","isUsed":false,"picUrl":"no","obtainReason":"双蛋大礼","cardNo":"123","cardPwd":"123","useStartTime":1544580671000,"useEndTime":1546135871000,"gmtCreate":1545703871000},{"id":50,"userId":10000000405,"name":"优酷会员","value":"6个月专享","isUsed":false,"picUrl":"na","obtainReason":"双蛋大礼","cardNo":"123","cardPwd":"123","useStartTime":1544580671000,"useEndTime":1546135871000,"gmtCreate":1545703871000},{"id":49,"userId":10000000405,"name":"餐饮专享","value":"100元满减","isUsed":false,"picUrl":"no","obtainReason":"双蛋大礼","cardNo":"123","cardPwd":"123","useStartTime":1544580671000,"useEndTime":1546135871000,"gmtCreate":1545703871000}]
     * pageSize : 16
     * pageNum : 1
     * walletNum : 12
     */

    @SerializedName("code")
    private String code;
    @SerializedName("pageNo")
    private int pageNo;
    @SerializedName("pageSize")
    private int pageSize;
    @SerializedName("pageNum")
    private int pageNum;
    @SerializedName("walletNum")
    private int walletNum;
    @SerializedName("walletList")
    private List<WalletListBean> walletList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getWalletNum() {
        return walletNum;
    }

    public void setWalletNum(int walletNum) {
        this.walletNum = walletNum;
    }

    public List<WalletListBean> getWalletList() {
        return walletList;
    }

    public void setWalletList(List<WalletListBean> walletList) {
        this.walletList = walletList;
    }


}
