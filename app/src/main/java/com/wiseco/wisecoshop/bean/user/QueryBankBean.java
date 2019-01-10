package com.wiseco.wisecoshop.bean.user;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wiseco on 2018/12/27.
 */

public class QueryBankBean {
    /**
     * userBankList : [{"id":12,"name":"民生银行","shortName":"民生银行"}]
     * code : S
     */

    @SerializedName("code")
    private String code;
    @SerializedName("userBankList")
    private List<UserBankListBean> userBankList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<UserBankListBean> getUserBankList() {
        return userBankList;
    }

    public void setUserBankList(List<UserBankListBean> userBankList) {
        this.userBankList = userBankList;
    }


}
