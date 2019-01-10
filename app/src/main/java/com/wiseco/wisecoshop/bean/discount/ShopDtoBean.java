package com.wiseco.wisecoshop.bean.discount;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by wiseco on 2018/12/26.
 */

public  class ShopDtoBean {
    /**
     * shopId : 57a446840926a84a3d6945a3
     * shopName : 屈臣氏(北京富贵园店)
     * logoSmall : https://qnimg.billbear.cn/images/amap/5757ef9d?imageMogr2/crop/!196x196a0a0/thumbnail/!102p
     * address : 广渠门内大街35-6号
     * miles : 100
     * actDescribe : {"中信银行":"9积分星巴克"}
     */

    @SerializedName("shopId")
    private String shopId;
    @SerializedName("shopName")
    private String shopName;
    @SerializedName("logoSmall")
    private String logoSmall;
    @SerializedName("address")
    private String address;
    @SerializedName("miles")
    private int miles;
    private Map<String ,String> actDescribe;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getLogoSmall() {
        return logoSmall;
    }

    public void setLogoSmall(String logoSmall) {
        this.logoSmall = logoSmall;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMiles() {
        return miles;
    }

    public void setMiles(int miles) {
        this.miles = miles;
    }

    public Map<String,String> getActDescribe() {
        return actDescribe;
    }

    public void setActDescribe( Map<String ,String> actDescribe) {
        this.actDescribe = actDescribe;
    }


}