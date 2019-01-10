package com.wiseco.wisecoshop.bean;

import java.util.List;

/**
 * Created by wiseco on 2018/10/22.
 */

public class MoneyFindBean {
    /**
     * code : S
     * productList : [{"prdclass":"CL","maxlimit":50000,"code":"BONJ-CL0001","minrate":"日0.05%","advantage":"\u201c随鑫花\u201d是小额互联网贷款产品，秒批最高5万额度","name":"随鑫花","approvaltime":"最快3分钟","smallicon":"http://58.87.76.219:8080/FinancialHub/common/image/bankprd/BONJCL0001.png","term":"6/12/24月","bankicon":"https://xmx.njcb.com.cn/portal/images/cfcPortal_64X64.dd9e3c3c.ico","keyword":"纯信用,无抵押,无担保,最高可达5万,纯线上审核,3分钟放款,随借随还,按日计息,日利万分之五"}]
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
         * prdclass : CL
         * maxlimit : 50000
         * code : BONJ-CL0001
         * minrate : 日0.05%
         * advantage : “随鑫花”是小额互联网贷款产品，秒批最高5万额度
         * name : 随鑫花
         * approvaltime : 最快3分钟
         * smallicon : http://58.87.76.219:8080/FinancialHub/common/image/bankprd/BONJCL0001.png
         * term : 6/12/24月
         * bankicon : https://xmx.njcb.com.cn/portal/images/cfcPortal_64X64.dd9e3c3c.ico
         * keyword : 纯信用,无抵押,无担保,最高可达5万,纯线上审核,3分钟放款,随借随还,按日计息,日利万分之五
         */

        private String prdclass;
        private int maxlimit;
        private String code;
        private String minrate;
        private String advantage;
        private String name;
        private String approvaltime;
        private String smallicon;
        private String term;
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

        public String getSmallicon() {
            return smallicon;
        }

        public void setSmallicon(String smallicon) {
            this.smallicon = smallicon;
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
}
