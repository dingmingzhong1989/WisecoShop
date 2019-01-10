package com.wiseco.wisecoshop.bean.discount;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wiseco on 2018/12/27.
 */

public class ShopActivityBean {
    /**
     * shopId : 57a446840926a84a3d6945a3
     * shopName : 屈臣氏(北京富贵园店)
     * logoSmall : https://qnimg.billbear.cn/images/amap/5757ef9d?imageMogr2/crop/!196x196a0a0/thumbnail/!102p
     * latitude : 31.172096
     * longitude : 121.391724
     * address : 广渠门内大街35-6号
     * miles : 100
     * acts : [{"id":"5a05139bc95d7a4a36d9748f","title":"9积分星巴克","bankName":"中信银行","category":"餐饮","bigImgUrl":"活动图片","availableTime":"每天 10:00-23:55"},{"id":"5a05139bc95d7a4a36d9748f","title":"9积分星巴克","bankName":"中信银行","category":"餐饮","bigImgUrl":"活动图片","availableTime":"每天 10:00-23:55"},{"id":"5a05139bc95d7a4a36d9748f","title":"9积分星巴克","bankName":"中信银行","category":"餐饮","bigImgUrl":"活动图片","availableTime":"每天 10:00-23:55"},{"id":"5a05139bc95d7a4a36d9748f","title":"9积分星巴克","bankName":"中信银行","category":"餐饮","bigImgUrl":"活动图片","availableTime":"每天 10:00-23:55"},{"id":"5a05139bc95d7a4a36d9748f","title":"9积分星巴克","bankName":"中信银行","category":"餐饮","bigImgUrl":"活动图片","availableTime":"每天 10:00-23:55"},{"id":"5a05139bc95d7a4a36d9748f","title":"9积分星巴克","bankName":"中信银行","category":"餐饮","bigImgUrl":"活动图片","availableTime":"每天 10:00-23:55"},{"id":"5a05139bc95d7a4a36d9748f","title":"9积分星巴克","bankName":"中信银行","category":"餐饮","bigImgUrl":"活动图片","availableTime":"每天 10:00-23:55"},{"id":"5a05139bc95d7a4a36d9748f","title":"9积分星巴克","bankName":"中信银行","category":"餐饮","bigImgUrl":"活动图片","availableTime":"每天 10:00-23:55"},{"id":"5a05139bc95d7a4a36d9748f","title":"9积分星巴克","bankName":"中信银行","category":"餐饮","bigImgUrl":"活动图片","availableTime":"每天 10:00-23:55"},{"id":"5a05139bc95d7a4a36d9748f","title":"9积分星巴克","bankName":"中信银行","category":"餐饮","bigImgUrl":"活动图片","availableTime":"每天 10:00-23:55"}]
     */

    @SerializedName("shopId")
    private String shopId;
    @SerializedName("shopName")
    private String shopName;
    @SerializedName("logoSmall")
    private String logoSmall;
    @SerializedName("latitude")
    private double latitude;
    @SerializedName("longitude")
    private double longitude;
    @SerializedName("address")
    private String address;
    @SerializedName("miles")
    private int miles;
    @SerializedName("acts")
    private List<ActsBean> acts;

    @SerializedName("tel")
    private String tel;

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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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

    public String getTel() {

        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


    public List<ActsBean> getActs() {
        return acts;
    }

    public void setActs(List<ActsBean> acts) {
        this.acts = acts;
    }



}