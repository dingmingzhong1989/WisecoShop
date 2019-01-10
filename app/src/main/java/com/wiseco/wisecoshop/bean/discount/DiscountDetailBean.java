package com.wiseco.wisecoshop.bean.discount;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wiseco on 2018/12/27.
 */

public class DiscountDetailBean {
    /**
     * code : S
     * promActivity : {"id":"adtdtdtdd","title":"9积分星巴克",
     * "way":"在中国银行「缤纷生活」购劵",
     * "bigimgurl":"https://qnpic.billbear.cn/Fi_DTc8PJ0CCv6y1i7Sl25ReseH0",
     * "bankname":"中国银行",
     * "startdate":1534032488000,
     * "enddate":1541981288000,"category1":
     * "美食","category2":"咖啡"
     * ,"type":"打折",
     * "payment":"在线支付",
     * "paybrand":"银联",
     * "availabletime":"每天 10:00-23:55",
     * "availablecity":"上海",
     * "note":"随时退、过期退、免预约",
     * "subjecttype":"线上",
     * "content":"活动期间，招商银行信用卡持卡人**每周三上午 10:00** 至招行「掌上生活 APP」50 元购「味多美」100 元代金券"}
     */

    @SerializedName("code")
    private String code;
    @SerializedName("promActivity")
    private PromActivityBean promActivity;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public PromActivityBean getPromActivity() {
        return promActivity;
    }

    public void setPromActivity(PromActivityBean promActivity) {
        this.promActivity = promActivity;
    }


}
