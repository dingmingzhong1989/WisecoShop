package com.wiseco.wisecoshop.bean;

import java.util.List;

/**
 * Created by wiseco on 2018/11/23.
 */

public class CardListItemBean {
    private List<ProductlistBean> productlist;

    public List<ProductlistBean> getProductlist() {
        return productlist;
    }

    public void setProductlist(List<ProductlistBean> productlist) {
        this.productlist = productlist;
    }

    public static class ProductlistBean {
        /**
         * advantage : 白金卡，免年费，超值好礼|享诸多白金权益服务|五星酒店超值服务
         * approvaltime : 30分钟
         * bankicon : /images/bankicon/spdb.png
         * code : SPDB-CC0003
         * keyword : 5|买一赠一,3|住3付2,1|优惠机票,2|24小时热线
         * maxlimit : 50000
         * name : 浦发VISA简约白金卡
         * prdclass : CC
         * smallicon : /images/creditcard/SPDB-CC0003.png
         */

        private String advantage;
        private String approvaltime;
        private String bankicon;
        private String code;
        private String keyword;
        private int maxlimit;
        private String name;
        private String prdclass;
        private String smallicon;

        public String getAdvantage() {
            return advantage;
        }

        public void setAdvantage(String advantage) {
            this.advantage = advantage;
        }

        public String getApprovaltime() {
            return approvaltime;
        }

        public void setApprovaltime(String approvaltime) {
            this.approvaltime = approvaltime;
        }

        public String getBankicon() {
            return bankicon;
        }

        public void setBankicon(String bankicon) {
            this.bankicon = bankicon;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public int getMaxlimit() {
            return maxlimit;
        }

        public void setMaxlimit(int maxlimit) {
            this.maxlimit = maxlimit;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrdclass() {
            return prdclass;
        }

        public void setPrdclass(String prdclass) {
            this.prdclass = prdclass;
        }

        public String getSmallicon() {
            return smallicon;
        }

        public void setSmallicon(String smallicon) {
            this.smallicon = smallicon;
        }
    }
   /* private List<ProductlistBean> productlist;

    public List<ProductlistBean> getProductlist() {
        return productlist;
    }

    public void setProductlist(List<ProductlistBean> productlist) {
        this.productlist = productlist;
    }

    public static class ProductlistBean {
        *//**
         * prdclass : CC
         * maxlimit : 0
         * code : ICBC-CC0001
         * advantage : 年轻客群、星座粉丝
         * name : 宇宙星座信用卡
         * approvaltime : 30分钟
         * smallicon : http://58.87.76.219:8080/FinancialHub/common/image/bankprd/ICBCCC0001.png
         * bankicon : http://m.icbc.com.cn/Portal_Resources/Touch/images/startico.GIF
         * keyword : 银联,VISA,免年费,生日福利
         *//*

        private String prdclass;
        private int maxlimit;
        private String code;
        private String advantage;
        private String name;
        private String approvaltime;
        private String smallicon;
        private String bankicon;
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
    }*/

}
