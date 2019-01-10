package com.wiseco.wisecoshop.bean;

import java.util.List;

/**
 * Created by wiseco on 2018/10/22.
 */

public class GridViewBean {
    /**
     * code : UCC
     * bankList : [{"id":1,"code":"SPDB","hasCC":true,"name":"浦发银行","shortname":"浦发银行","icon":"http://www.spdb.com.cn/favicon.ico"},{"id":3,"code":"ICBC","hasCC":true,"name":"工商银行","shortname":"工商银行","icon":"http://m.icbc.com.cn/Portal_Resources/Touch/images/startico.GIF"}]
     */

    private String code;
    private List<BankListBean> bankList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<BankListBean> getBankList() {
        return bankList;
    }

    public void setBankList(List<BankListBean> bankList) {
        this.bankList = bankList;
    }

    public static class BankListBean {
        /**
         * id : 1
         * code : SPDB
         * hasCC : true
         * name : 浦发银行
         * shortname : 浦发银行
         * icon : http://www.spdb.com.cn/favicon.ico
         */

        private int id;
        private String code;
        private boolean hasCC;
        private String name;
        private String shortname;
        private String icon;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public boolean isHasCC() {
            return hasCC;
        }

        public void setHasCC(boolean hasCC) {
            this.hasCC = hasCC;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getShortname() {
            return shortname;
        }

        public void setShortname(String shortname) {
            this.shortname = shortname;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}
