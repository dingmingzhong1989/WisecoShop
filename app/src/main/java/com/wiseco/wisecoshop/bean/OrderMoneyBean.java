package com.wiseco.wisecoshop.bean;

import java.util.List;

/**
 * Created by wiseco on 2018/11/12.
 */

public class OrderMoneyBean {


    /**
     * code : S
     * item : [{"productName":"随鑫花","currencySymbol":"¥","maxLimit":50000,"term":"6/12/24个月自由借款","minRate":"日利率0.066%高峰","stateName":"已提交申请","id":3,"state":0,"applyDate":"2018-10-11","smallIcon":"http://58.87.76.219:8080/FinancialHub/common/image/bankprd/BONJCL0001.png","bigIcon":"http://58.87.76.219:8080/FinancialHub/common/image/bankprd/BONJCL0001.png","keyWord":"纯信用,无抵押,无担保,最高可达5万,纯线上审核,3分钟放款,随借随还,按日计息,日利万分之五","advantage":"\"随鑫花\"是小额互联网贷款产品，秒批最高5万额度","characteristic":"\"随鑫花\"是小额互联网贷款产品，秒批最高5万额度， APP在线申请、在线放款，无需至网点面签！ \\n申请流程：\\n注册登录APP-扫描上传身份证-验证银行卡-人脸识别-设置交易密码-填写税后年收入-征信授权"}]
     */

    private String code;
    private List<ItemBean> item;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ItemBean> getItem() {
        return item;
    }

    public void setItem(List<ItemBean> item) {
        this.item = item;
    }

    public static class ItemBean {
        /**
         * productName : 随鑫花
         * currencySymbol : ¥
         * maxLimit : 50000
         * term : 6/12/24个月自由借款
         * minRate : 日利率0.066%高峰
         * stateName : 已提交申请
         * id : 3
         * state : 0
         * applyDate : 2018-10-11
         * smallIcon : http://58.87.76.219:8080/FinancialHub/common/image/bankprd/BONJCL0001.png
         * bigIcon : http://58.87.76.219:8080/FinancialHub/common/image/bankprd/BONJCL0001.png
         * keyWord : 纯信用,无抵押,无担保,最高可达5万,纯线上审核,3分钟放款,随借随还,按日计息,日利万分之五
         * advantage : "随鑫花"是小额互联网贷款产品，秒批最高5万额度
         * characteristic : "随鑫花"是小额互联网贷款产品，秒批最高5万额度， APP在线申请、在线放款，无需至网点面签！ \n申请流程：\n注册登录APP-扫描上传身份证-验证银行卡-人脸识别-设置交易密码-填写税后年收入-征信授权
         */

        private String productName;
        private String currencySymbol;
        private int maxLimit;
        private String term;
        private String minRate;
        private String stateName;
        private int id;
        private int state;
        private String applyDate;
        private String smallIcon;
        private String bigIcon;
        private String keyWord;
        private String advantage;
        private String characteristic;
        private String applyDateStr;
        private String bankIcon;
        private int approvedAmount;

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getCurrencySymbol() {
            return currencySymbol;
        }

        public void setCurrencySymbol(String currencySymbol) {
            this.currencySymbol = currencySymbol;
        }

        public int getMaxLimit() {
            return maxLimit;
        }

        public void setMaxLimit(int maxLimit) {
            this.maxLimit = maxLimit;
        }
        public int getApprovedAmount() {
            return approvedAmount;
        }

        public void setApprovedAmount(int approvedAmount) {
            this.approvedAmount = approvedAmount;
        }


        public String getTerm() {
            return term;
        }

        public void setTerm(String term) {
            this.term = term;
        }

        public String getMinRate() {
            return minRate;
        }

        public void setMinRate(String minRate) {
            this.minRate = minRate;
        }

        public String getStateName() {
            return stateName;
        }

        public void setStateName(String stateName) {
            this.stateName = stateName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getApplyDate() {
            return applyDate;
        }

        public void setApplyDate(String applyDate) {
            this.applyDate = applyDate;
        }
        public String getApplyDateStr() {

            return applyDateStr;
        }

        public void setApplyDateStr(String applyDateStr) {
            this.applyDateStr = applyDateStr;
        }
        public String getSmallIcon() {
            return smallIcon;
        }

        public void setSmallIcon(String smallIcon) {
            this.smallIcon = smallIcon;
        }

        public String getBigIcon() {
            return bigIcon;
        }

        public void setBigIcon(String bigIcon) {
            this.bigIcon = bigIcon;
        }
        public String getBankIcon() {
            return bankIcon;
        }

        public void setBankIcon(String bankIcon) {
            this.bankIcon = bankIcon;
        }

        public String getKeyWord() {
            return keyWord;
        }

        public void setKeyWord(String keyWord) {
            this.keyWord = keyWord;
        }

        public String getAdvantage() {
            return advantage;
        }

        public void setAdvantage(String advantage) {
            this.advantage = advantage;
        }

        public String getCharacteristic() {
            return characteristic;
        }

        public void setCharacteristic(String characteristic) {
            this.characteristic = characteristic;
        }
    }
}
