package com.wiseco.wisecoshop.bean;

/**
 * Created by wiseco on 2018/10/22.
 */

public class MoneyDetailBean {
    /**
     * productdtl : {"applycondition":"18-50周岁我国公民，无不良征信记录。","code":"BONJ-CL0001","minrate":"日0.05%","advantage":"\u201c随鑫花\u201d是小额互联网贷款产品，秒批最高5万额度","bankicon":"https://xmx.njcb.com.cn/portal/images/cfcPortal_64X64.dd9e3c3c.ico","characteristic":"\u201c随鑫花\u201d是小额互联网贷款产品，秒批最高5万额度|APP在线申请、在线放款，无需至网点面签！|申请流程：|注册登录APP|扫描上传身份证|验证银行卡|人脸识别|设置交易密码|填写税后年收入|征信授权","maxlimit":50000,"bigicon":"http://58.87.76.219:8080/FinancialHub/common/image/bankprd/BONJCL0001.png","name":"随鑫花","approvaltime":"最快3分钟","term":"6/12/24月","bankname":"南京银行","keyword":"纯信用,无抵押,无担保,最高可达5万,纯线上审核,3分钟放款,随借随还,按日计息,日利万分之五"}
     * code : S
     */

    private ProductdtlBean productdtl;
    private String code;

    public ProductdtlBean getProductdtl() {
        return productdtl;
    }

    public void setProductdtl(ProductdtlBean productdtl) {
        this.productdtl = productdtl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static class ProductdtlBean {
        /**
         * applycondition : 18-50周岁我国公民，无不良征信记录。
         * code : BONJ-CL0001
         * minrate : 日0.05%
         * advantage : “随鑫花”是小额互联网贷款产品，秒批最高5万额度
         * bankicon : https://xmx.njcb.com.cn/portal/images/cfcPortal_64X64.dd9e3c3c.ico
         * characteristic : “随鑫花”是小额互联网贷款产品，秒批最高5万额度|APP在线申请、在线放款，无需至网点面签！|申请流程：|注册登录APP|扫描上传身份证|验证银行卡|人脸识别|设置交易密码|填写税后年收入|征信授权
         * maxlimit : 50000
         * bigicon : http://58.87.76.219:8080/FinancialHub/common/image/bankprd/BONJCL0001.png
         * name : 随鑫花
         * approvaltime : 最快3分钟
         * term : 6/12/24月
         * bankname : 南京银行
         * keyword : 纯信用,无抵押,无担保,最高可达5万,纯线上审核,3分钟放款,随借随还,按日计息,日利万分之五
         */

        private String applycondition;
        private String code;
        private String minrate;
        private String advantage;
        private String bankicon;
        private String characteristic;
        private int maxlimit;
        private String bigicon;
        private String name;
        private String approvaltime;
        private String term;
        private String bankname;
        private String keyword;

        public String getApplycondition() {
            return applycondition;
        }

        public void setApplycondition(String applycondition) {
            this.applycondition = applycondition;
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

        public String getBankicon() {
            return bankicon;
        }

        public void setBankicon(String bankicon) {
            this.bankicon = bankicon;
        }

        public String getCharacteristic() {
            return characteristic;
        }

        public void setCharacteristic(String characteristic) {
            this.characteristic = characteristic;
        }

        public int getMaxlimit() {
            return maxlimit;
        }

        public void setMaxlimit(int maxlimit) {
            this.maxlimit = maxlimit;
        }

        public String getBigicon() {
            return bigicon;
        }

        public void setBigicon(String bigicon) {
            this.bigicon = bigicon;
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

        public String getBankname() {
            return bankname;
        }

        public void setBankname(String bankname) {
            this.bankname = bankname;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }
    }
}
