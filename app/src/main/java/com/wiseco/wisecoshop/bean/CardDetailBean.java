package com.wiseco.wisecoshop.bean;

/**
 * Created by wiseco on 2018/10/23.
 */

public class CardDetailBean {
    /**
     * productdtl : {"applycondition":"年满18岁，征信良好，有固定收入","code":"ICBC-CC0001","advantage":"年轻客群、星座粉丝","bankicon":"http://m.icbc.com.cn/Portal_Resources/Touch/images/startico.GIF","characteristic":"生日当天消费10倍积分  优酷视频VIP会员或爱奇艺VIP会员免费尊贵体验  容时、容差、0外汇兑换手续费 |任意消费即可获赠账户安全险，最高获赔5万元","maxlimit":0,"bigicon":"http://58.87.76.219:8080/FinancialHub/common/image/bankprd/ICBCCC0001.png","name":"宇宙星座信用卡","approvaltime":"30分钟","bankname":"工商银行","keyword":"银联,VISA,免年费,生日福利"}
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
         * applycondition : 年满18岁，征信良好，有固定收入
         * code : ICBC-CC0001
         * advantage : 年轻客群、星座粉丝
         * bankicon : http://m.icbc.com.cn/Portal_Resources/Touch/images/startico.GIF
         * characteristic : 生日当天消费10倍积分  优酷视频VIP会员或爱奇艺VIP会员免费尊贵体验  容时、容差、0外汇兑换手续费 |任意消费即可获赠账户安全险，最高获赔5万元
         * maxlimit : 0
         * bigicon : http://58.87.76.219:8080/FinancialHub/common/image/bankprd/ICBCCC0001.png
         * name : 宇宙星座信用卡
         * approvaltime : 30分钟
         * bankname : 工商银行
         * keyword : 银联,VISA,免年费,生日福利
         */

        private String applycondition;
        private String code;
        private String advantage;
        private String bankicon;
        private String characteristic;
        private int maxlimit;
        private String bigicon;
        private String name;
        private String approvaltime;
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
