package com.wiseco.wisecoshop.bean;

import com.google.gson.annotations.SerializedName;
import com.wiseco.wisecoshop.bean.discount.CategoryListBean;

import java.util.List;

/**
 * Created by wiseco on 2018/12/24.
 */

public class LifePromBean {
    /**
     * code : S
     * bannerList : [{"id":15,"name":"生活优惠","imageUrl":"https://m.wisecofincloud.com/images/common/banner/banner1.jpg","platform":"1","position":"pb"}]
     * categoryList : [{"id":38,"name":"美食","mongoId":"579828420926a85e90f2bbe1","icon":"/images/common/category/meishi.png"},{"id":213,"name":"文娱","mongoId":"59224b430926a862244afc32","icon":"/images/common/category/wenyu.png"},{"id":214,"name":"积分","mongoId":"59224b430926a862244afc33","icon":"/images/common/category/jifen.png"},{"id":215,"name":"保障","mongoId":"59224b430926a862244afc34","icon":"/images/common/category/baozheng.png"},{"id":216,"name":"用车","mongoId":"59224b430926a862244afc35","icon":"/images/common/category/yongce.png"},{"id":217,"name":"医疗","mongoId":"59224b430926a862244afc36","icon":"/images/common/category/yiliao.png"},{"id":218,"name":"教育","mongoId":"59224b430926a862244afc37","icon":"/images/common/category/jiaoyu.png"},{"id":219,"name":"旅游出行","mongoId":"59224b430926a862244afc38","icon":"/images/common/category/luyou.png"},{"id":220,"name":"境外购物","mongoId":"59224b430926a862244afc39","icon":"/images/common/category/jingwaigouwu.png"},{"id":221,"name":"运动健身","mongoId":"59224b430926a862244afc3a","icon":"/images/common/category/jiansheng.png"},{"id":222,"name":"购物","mongoId":"59224b430926a862244afc3b","icon":"/images/common/category/gouwu.png"},{"id":223,"name":"丽人","mongoId":"59224b430926a862244afc3c","icon":"/images/common/category/liren.png"},{"id":225,"name":"开卡礼","mongoId":"59224b430926a862244afc3e","icon":"/images/common/category/kaikali.png"},{"id":226,"name":"其他","mongoId":"59224b430926a862244afc3f","icon":"/images/common/category/qita.png"},{"id":300,"name":"家居建材","mongoId":"597988ac0926a8057258c67f","icon":"/images/common/category/jiajujiancai.png"},{"id":301,"name":"不限场景","mongoId":"597988ac0926a8057258c680","icon":"/images/common/category/buxianchangjin.png"}]
     */

    @SerializedName("code")
    private String code;
    @SerializedName("bannerList")
    private List<BannerListBean> bannerList;
    @SerializedName("categoryList")
    private List<CategoryListBean> categoryList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<BannerListBean> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<BannerListBean> bannerList) {
        this.bannerList = bannerList;
    }

    public List<CategoryListBean> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryListBean> categoryList) {
        this.categoryList = categoryList;
    }

    public static class BannerListBean {
        /**
         * id : 15
         * name : 生活优惠
         * imageUrl : https://m.wisecofincloud.com/images/common/banner/banner1.jpg
         * platform : 1
         * position : pb
         */

        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
        @SerializedName("imageUrl")
        private String imageUrl;
        @SerializedName("platform")
        private String platform;
        @SerializedName("position")
        private String position;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }
    }


}
