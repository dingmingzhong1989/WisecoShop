package com.wiseco.wisecoshop.bean.goods;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wiseco on 2018/12/28.
 */

public class MainFragmentGoodsBean {
    /**
     * code : S
     * ccRL : [{"prdclass":"CC","maxlimit":50000,"code":"CMBCHINA-CC0004","advantage":"首年免年费|刷卡额度最高5万元|享5折美食，9元兑电影券","name":"招行腾讯视频VIP联名卡","approvaltime":"30分钟","smallicon":"/images/creditcard/CMBCHINA-CC0004.png","bankicon":"/images/bankicon/cmbchina.png","keyword":"1|达标好礼,2|吃喝玩乐,4|多重优惠,5|会员赠送"},{"prdclass":"CC","maxlimit":80000,"code":"SPDB-CC0002","advantage":"免年费、免版面费|女性专属，与美联名|消费送红包，超值好礼","name":"浦发美丽女人卡之花语卡-枫情万种","approvaltime":"30分钟","smallicon":"/images/creditcard/SPDB-CC0002.png","bankicon":"/images/bankicon/spdb.png","keyword":"4|刷卡金,1|多重礼品,3|女神花束,5|与美相遇"}]
     * clRL : [{"prdclass":"CL","maxlimit":200000,"code":"HRBB-CL0001","minrate":"日息0.03%","advantage":"纯信用,无抵押,无担保,月息低至0.49%","name":"哈银有卡贷","approvaltime":"最快1分钟","term":"6/12/18/24月","bankicon":"/images/bankicon/hrbb.png","keyword":"1|纯信用,2|无抵押,3|无担保,4|快速审批,5|秒速放款"}]
     */

    @SerializedName("code")
    private String code;
    @SerializedName("ccRL")
    private List<CcRLBean> ccRL;
    @SerializedName("clRL")
    private List<ClRLBean> clRL;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }



    public List<CcRLBean> getCcRL() {
        return ccRL;
    }

    public void setCcRL(List<CcRLBean> ccRL) {
        this.ccRL = ccRL;
    }

    public List<ClRLBean> getClRL() {
        return clRL;
    }

    public void setClRL(List<ClRLBean> clRL) {
        this.clRL = clRL;
    }


}
