package com.wiseco.wisecoshop.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wiseco on 2018/12/28.
 */

public class HomeBean {
    /**
     * code : S
     * partner : [{"id":34,"name":"浦发银行","imageUrl":"https://m.wisecofincloud.com/images/common/partner/pf.png","platform":"1","position":"pt"},{"id":33,"name":"建设银行","imageUrl":"https://m.wisecofincloud.com/images/common/partner/jh.png","platform":"1","position":"pt"},{"id":32,"name":"兴业银行","imageUrl":"https://m.wisecofincloud.com/images/common/partner/xy.png","platform":"1","position":"pt"},{"id":31,"name":"中国银行","imageUrl":"https://m.wisecofincloud.com/images/common/partner/zg.png","platform":"1","position":"pt"},{"id":30,"name":"招商银行","imageUrl":"https://m.wisecofincloud.com/images/common/partner/zs.png","platform":"1","position":"pt"},{"id":29,"name":"中信银行","imageUrl":"https://m.wisecofincloud.com/images/common/partner/zx.png","platform":"1","position":"pt"},{"id":28,"name":"广发银行","imageUrl":"https://m.wisecofincloud.com/images/common/partner/gf.png","platform":"1","position":"pt"},{"id":27,"name":"哈银","imageUrl":"https://m.wisecofincloud.com/images/common/partner/haying.png","platform":"1","position":"pt"},{"id":26,"name":"HSBC","imageUrl":"https://m.wisecofincloud.com/images/common/partner/hf.png","platform":"1","position":"pt"},{"id":25,"name":"华夏银行","imageUrl":"https://m.wisecofincloud.com/images/common/partner/hx.png","platform":"1","position":"pt"},{"id":24,"name":"交通银行","imageUrl":"https://m.wisecofincloud.com/images/common/partner/jt.png","platform":"1","position":"pt"},{"id":23,"name":"平安银行","imageUrl":"https://m.wisecofincloud.com/images/common/partner/pa.png","platform":"1","position":"pt"}]
     * banner : [{"id":36,"name":"newAppBanner","imageUrl":"https://m.wisecofincloud.com/images/common/banner/bannernewapp.png","platform":"1","position":"bn"},{"id":35,"name":"newAppBanner","imageUrl":"https://m.wisecofincloud.com/images/common/banner/bannernewapp.png","platform":"1","position":"bn"}]
     */

    @SerializedName("code")
    private String code;
    @SerializedName("partner")
    private List<PartnerBean> partner;
    @SerializedName("banner")
    private List<BannerBean> banner;
    @SerializedName("littleRabbit")
    private boolean littleRabbit;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public boolean getLittleRabbit() {
        return littleRabbit;
    }

    public void setLittleRabbit(boolean littleRabbit) {
        this.littleRabbit = littleRabbit;
    }


    public List<PartnerBean> getPartner() {
        return partner;
    }

    public void setPartner(List<PartnerBean> partner) {
        this.partner = partner;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }



}
