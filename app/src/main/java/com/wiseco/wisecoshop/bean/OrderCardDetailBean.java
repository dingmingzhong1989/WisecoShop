package com.wiseco.wisecoshop.bean;

import java.util.List;

/**
 * Created by wiseco on 2018/11/12.
 */

public class OrderCardDetailBean {
    /**
     * code : S
     * item : {"applyLog":{"productName":"省呗现金分期","currencySymbol":"¥","maxLimit":50000,"minRate":"月息0.49%","stateName":"申请成功","id":123455678,"state":1,"applyDate":"2018-11-12T10:09:02.119+0000","productCode":"SMY-CL0001","bigIcon":"/images/creditcard/WIBC-CC0001.png","keyWord":"1|出行便利,3|消费优惠,4|超值享受,5|商旅人士","advantage":"境内外机场免费接送服务|全球机场贵宾厅畅行礼遇|境外刷卡减免国外交易汇兑手续费","characteristic":"境内外机场免费接送服务(境内4次、境外2次)|·全球机场贵宾厅不限次畅行礼遇(本人可享12次免费畅行，当年可免费携带随行2人次)|·尊享本人4天北京首都国际机场周边免费停车服务,24小时专人看管您的爱车,并提供机场与停车场之间的免费接驳服务|·积分超值兑换航空里程|·境外刷卡减免国外交易汇兑手续费|·最高1000万公共交通工具意外险及快乐旅行保险,航班延误定额赔偿保障|·年费900元,卡片一经核发,无论激活与否,即收取年费|温馨提示: 权益细则以银行信用卡官网为准","productTermNumber":6,"applyCondition":"18周岁以上中国公民, 征信情况良好。"},"userDto":{"name":"李**","mobile":"151****0897"},"loanRecords":[{"eventTime":"2018-11-12T10:09:02.119+0000","eventName":"未完成申请"},{"eventTime":"2018-11-12T10:09:02.119+0000","eventName":"通过(未放款)"},{"eventTime":"2018-11-12T10:09:02.119+0000","eventName":"通过(已放款)"}]}
     * isSuccess : true
     */

    private String code;
    private ItemBean item;
    private boolean isSuccess;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ItemBean getItem() {
        return item;
    }

    public void setItem(ItemBean item) {
        this.item = item;
    }

    public boolean isIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public static class ItemBean {
        /**
         * applyLog : {"productName":"省呗现金分期","currencySymbol":"¥","maxLimit":50000,"minRate":"月息0.49%","stateName":"申请成功","id":123455678,"state":1,"applyDate":"2018-11-12T10:09:02.119+0000","productCode":"SMY-CL0001","bigIcon":"/images/creditcard/WIBC-CC0001.png","keyWord":"1|出行便利,3|消费优惠,4|超值享受,5|商旅人士","advantage":"境内外机场免费接送服务|全球机场贵宾厅畅行礼遇|境外刷卡减免国外交易汇兑手续费","characteristic":"境内外机场免费接送服务(境内4次、境外2次)|·全球机场贵宾厅不限次畅行礼遇(本人可享12次免费畅行，当年可免费携带随行2人次)|·尊享本人4天北京首都国际机场周边免费停车服务,24小时专人看管您的爱车,并提供机场与停车场之间的免费接驳服务|·积分超值兑换航空里程|·境外刷卡减免国外交易汇兑手续费|·最高1000万公共交通工具意外险及快乐旅行保险,航班延误定额赔偿保障|·年费900元,卡片一经核发,无论激活与否,即收取年费|温馨提示: 权益细则以银行信用卡官网为准","productTermNumber":6,"applyCondition":"18周岁以上中国公民, 征信情况良好。"}
         * userDto : {"name":"李**","mobile":"151****0897"}
         * loanRecords : [{"eventTime":"2018-11-12T10:09:02.119+0000","eventName":"未完成申请"},{"eventTime":"2018-11-12T10:09:02.119+0000","eventName":"通过(未放款)"},{"eventTime":"2018-11-12T10:09:02.119+0000","eventName":"通过(已放款)"}]
         */

        private ApplyLogBean applyLog;
        private UserDtoBean userDto;
        private List<LoanRecordsBean> loanRecords;

        public ApplyLogBean getApplyLog() {
            return applyLog;
        }

        public void setApplyLog(ApplyLogBean applyLog) {
            this.applyLog = applyLog;
        }

        public UserDtoBean getUserDto() {
            return userDto;
        }

        public void setUserDto(UserDtoBean userDto) {
            this.userDto = userDto;
        }

        public List<LoanRecordsBean> getLoanRecords() {
            return loanRecords;
        }

        public void setLoanRecords(List<LoanRecordsBean> loanRecords) {
            this.loanRecords = loanRecords;
        }

        public static class ApplyLogBean {
            /**
             * productName : 省呗现金分期
             * currencySymbol : ¥
             * maxLimit : 50000
             * minRate : 月息0.49%
             * stateName : 申请成功
             * id : 123455678
             * state : 1
             * applyDate : 2018-11-12T10:09:02.119+0000
             * productCode : SMY-CL0001
             * bigIcon : /images/creditcard/WIBC-CC0001.png
             * keyWord : 1|出行便利,3|消费优惠,4|超值享受,5|商旅人士
             * advantage : 境内外机场免费接送服务|全球机场贵宾厅畅行礼遇|境外刷卡减免国外交易汇兑手续费
             * characteristic : 境内外机场免费接送服务(境内4次、境外2次)|·全球机场贵宾厅不限次畅行礼遇(本人可享12次免费畅行，当年可免费携带随行2人次)|·尊享本人4天北京首都国际机场周边免费停车服务,24小时专人看管您的爱车,并提供机场与停车场之间的免费接驳服务|·积分超值兑换航空里程|·境外刷卡减免国外交易汇兑手续费|·最高1000万公共交通工具意外险及快乐旅行保险,航班延误定额赔偿保障|·年费900元,卡片一经核发,无论激活与否,即收取年费|温馨提示: 权益细则以银行信用卡官网为准
             * productTermNumber : 6
             * applyCondition : 18周岁以上中国公民, 征信情况良好。
             */

            private String productName;
            private String currencySymbol;
            private int maxLimit;
            private String minRate;
            private String stateName;
            private int id;
            private int state;
            private String applyDate;
            private String productCode;
            private String bigIcon;
            private String keyWord;
            private String advantage;
            private String characteristic;
            private int productTermNumber;
            private String applyCondition;
            private String applyDateStr;
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

            public String getProductCode() {
                return productCode;
            }

            public void setProductCode(String productCode) {
                this.productCode = productCode;
            }

            public String getBigIcon() {
                return bigIcon;
            }

            public void setBigIcon(String bigIcon) {
                this.bigIcon = bigIcon;
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

            public int getProductTermNumber() {
                return productTermNumber;
            }

            public void setProductTermNumber(int productTermNumber) {
                this.productTermNumber = productTermNumber;
            }

            public String getApplyCondition() {
                return applyCondition;
            }

            public void setApplyCondition(String applyCondition) {
                this.applyCondition = applyCondition;
            }
        }

        public static class UserDtoBean {
            /**
             * name : 李**
             * mobile : 151****0897
             */

            private String name;
            private String mobile;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }
        }

        public static class LoanRecordsBean {
            /**
             * eventTime : 2018-11-12T10:09:02.119+0000
             * eventName : 未完成申请
             */

            private String eventTime;
            private String eventName;
            private String eventTimeStr;

            public void setEventTimeStr(String eventTimeStr) {
                this.eventTimeStr = eventTimeStr;
            }

            public String getEventTimeStr() {

                return eventTimeStr;
            }
            public String getEventTime() {
                return eventTime;
            }

            public void setEventTime(String eventTime) {
                this.eventTime = eventTime;
            }

            public String getEventName() {
                return eventName;
            }

            public void setEventName(String eventName) {
                this.eventName = eventName;
            }
        }
    }
}
