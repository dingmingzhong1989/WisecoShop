package com.wiseco.wisecoshop.bean;

import java.util.List;

/**
 * Created by wiseco on 2018/10/11.
 */

public class MoneyListBean {


    /**
     * code : S
     * productmsg : {"prdnum":3,"pagesize":16,"pagenum":1,"categorylist":[{"name":"银联白金卡","count":2,"id":"1"},{"name":"其它","count":1,"id":"other"},{"name":"VISIA","count":1,"id":"4"}],"productlist":[{"prdclass":"CL","maxlimit":50000,"code":"BONJ-CL0001","minrate":"日0.05%","advantage":"\u201c随鑫花\u201d是小额互联网贷款产品，秒批最高5万额度","name":"随鑫花","approvaltime":"最快3分钟","smallicon":"http://58.87.76.219:8080/FinancialHub/common/image/bankprd/BONJCL0001.png","term":"6/12/24月","bankicon":"https://xmx.njcb.com.cn/portal/images/cfcPortal_64X64.dd9e3c3c.ico","keyword":"纯信用,无抵押,无担保,最高可达5万,纯线上审核,3分钟放款,随借随还,按日计息,日利万分之五"},{"prdclass":"CL","maxlimit":50000,"code":"SMY-CL0001","minrate":"月息0.49","advantage":"纯信用,无抵押,无担保,月息低至0.49%","name":"省呗","approvaltime":"最快1分钟","term":"3/6/12月","bankicon":"https://www.smyfinancial.com/favicon.ico","keyword":"纯信用,无抵押,无担保,月息低至0.49%"},{"prdclass":"CL","maxlimit":299000,"code":"SPDB-CC00017","minrate":"期3.12%","advantage":"已持卡客户部分最高额度为29.9万，针对新客户最高授信额度为5万","name":"小浦红贷","approvaltime":"最快1分钟","term":"3-36月","bankicon":"http://www.spdb.com.cn/favicon.ico","keyword":"纯信用,无抵押,无担保,快速审批,秒速放款"}]}
     */

    private String code;
    private ProductmsgBean productmsg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ProductmsgBean getProductmsg() {
        return productmsg;
    }

    public void setProductmsg(ProductmsgBean productmsg) {
        this.productmsg = productmsg;
    }

    public static class ProductmsgBean {
        /**
         * prdnum : 3
         * pagesize : 16
         * pagenum : 1
         * categorylist : [{"name":"银联白金卡","count":2,"id":"1"},{"name":"其它","count":1,"id":"other"},{"name":"VISIA","count":1,"id":"4"}]
         * productlist : [{"prdclass":"CL","maxlimit":50000,"code":"BONJ-CL0001","minrate":"日0.05%","advantage":"\u201c随鑫花\u201d是小额互联网贷款产品，秒批最高5万额度","name":"随鑫花","approvaltime":"最快3分钟","smallicon":"http://58.87.76.219:8080/FinancialHub/common/image/bankprd/BONJCL0001.png","term":"6/12/24月","bankicon":"https://xmx.njcb.com.cn/portal/images/cfcPortal_64X64.dd9e3c3c.ico","keyword":"纯信用,无抵押,无担保,最高可达5万,纯线上审核,3分钟放款,随借随还,按日计息,日利万分之五"},{"prdclass":"CL","maxlimit":50000,"code":"SMY-CL0001","minrate":"月息0.49","advantage":"纯信用,无抵押,无担保,月息低至0.49%","name":"省呗","approvaltime":"最快1分钟","term":"3/6/12月","bankicon":"https://www.smyfinancial.com/favicon.ico","keyword":"纯信用,无抵押,无担保,月息低至0.49%"},{"prdclass":"CL","maxlimit":299000,"code":"SPDB-CC00017","minrate":"期3.12%","advantage":"已持卡客户部分最高额度为29.9万，针对新客户最高授信额度为5万","name":"小浦红贷","approvaltime":"最快1分钟","term":"3-36月","bankicon":"http://www.spdb.com.cn/favicon.ico","keyword":"纯信用,无抵押,无担保,快速审批,秒速放款"}]
         */

        private int prdnum;
        private int pagesize;
        private int pagenum;
        private List<CategorylistBean> categorylist;
        private List<ProductlistBean> productlist;

        public int getPrdnum() {
            return prdnum;
        }

        public void setPrdnum(int prdnum) {
            this.prdnum = prdnum;
        }

        public int getPagesize() {
            return pagesize;
        }

        public void setPagesize(int pagesize) {
            this.pagesize = pagesize;
        }

        public int getPagenum() {
            return pagenum;
        }

        public void setPagenum(int pagenum) {
            this.pagenum = pagenum;
        }

        public List<CategorylistBean> getCategorylist() {
            return categorylist;
        }

        public void setCategorylist(List<CategorylistBean> categorylist) {
            this.categorylist = categorylist;
        }

        public List<ProductlistBean> getProductlist() {
            return productlist;
        }

        public void setProductlist(List<ProductlistBean> productlist) {
            this.productlist = productlist;
        }

        public static class CategorylistBean {
            /**
             * name : 银联白金卡
             * count : 2
             * id : 1
             */

            private String name;
            private int count;
            private String id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

        public static class ProductlistBean {
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
}
