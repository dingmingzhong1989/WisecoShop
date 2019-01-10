package com.wiseco.wisecoshop.bean;

import java.util.List;

/**
 * Created by wiseco on 2018/10/23.
 */

public class CardFindBean {
    /**
     * code : S
     * productList : [{"prdclass":"CC","maxlimit":0,"code":"ICBC-CC0001","advantage":"年轻客群、星座粉丝","name":"宇宙星座信用卡","approvaltime":"30分钟","smallicon":"http://58.87.76.219:8080/FinancialHub/common/image/bankprd/ICBCCC0001.png","bankicon":"http://m.icbc.com.cn/Portal_Resources/Touch/images/startico.GIF","keyword":"银联,VISA,免年费,生日福利"},{"prdclass":"CC","maxlimit":0,"code":"ICBC-CC0002","advantage":"体育爱好人群 公务员及事业单位员工","name":"World奋斗·郎平信用卡","approvaltime":"30分钟","smallicon":"http://58.87.76.219:8080/FinancialHub/common/image/bankprd/ICBCCC0002.png","bankicon":"http://m.icbc.com.cn/Portal_Resources/Touch/images/startico.GIF","keyword":"万事达,免年费,郎平签名"},{"prdclass":"CC","maxlimit":0,"code":"ICBC-CC0003","advantage":"爱购全球最高21%返现 差旅需求人群","name":"工银环球旅行信用卡","approvaltime":"30分钟","smallicon":"http://58.87.76.219:8080/FinancialHub/common/image/bankprd/ICBCCC0003.png","bankicon":"http://m.icbc.com.cn/Portal_Resources/Touch/images/startico.GIF","keyword":"银联,VISA,万事达,商旅特惠"}]
     */

    private String code;
    private List<ProductListBean> productList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ProductListBean> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductListBean> productList) {
        this.productList = productList;
    }

    public static class ProductListBean {
        /**
         * prdclass : CC
         * maxlimit : 0
         * code : ICBC-CC0001
         * advantage : 年轻客群、星座粉丝
         * name : 宇宙星座信用卡
         * approvaltime : 30分钟
         * smallicon : http://58.87.76.219:8080/FinancialHub/common/image/bankprd/ICBCCC0001.png
         * bankicon : http://m.icbc.com.cn/Portal_Resources/Touch/images/startico.GIF
         * keyword : 银联,VISA,免年费,生日福利
         */

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
    }
}
