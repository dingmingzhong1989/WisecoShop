package com.wiseco.wisecoshop.bean.discount;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wiseco on 2018/12/26.
 */

public class NearbyShopBean {
    /**
     * code : S
     * shopDto : [{"shopId":"57a446840926a84a3d6945a3","shopName":"屈臣氏(北京富贵园店)","logoSmall":"https://qnimg.billbear.cn/images/amap/5757ef9d?imageMogr2/crop/!196x196a0a0/thumbnail/!102p","address":"广渠门内大街35-6号","miles":100,"actDescribe":{"中信银行":"9积分星巴克"}},{"shopId":"57a446840926a84a3d6945a3","shopName":"屈臣氏(北京富贵园店)","logoSmall":"https://qnimg.billbear.cn/images/amap/5757ef9d?imageMogr2/crop/!196x196a0a0/thumbnail/!102p","address":"广渠门内大街35-6号","miles":100,"actDescribe":{"中信银行":"9积分星巴克"}},{"shopId":"57a446840926a84a3d6945a3","shopName":"屈臣氏(北京富贵园店)","logoSmall":"https://qnimg.billbear.cn/images/amap/5757ef9d?imageMogr2/crop/!196x196a0a0/thumbnail/!102p","address":"广渠门内大街35-6号","miles":100,"actDescribe":{"中信银行":"9积分星巴克"}},{"shopId":"57a446840926a84a3d6945a3","shopName":"屈臣氏(北京富贵园店)","logoSmall":"https://qnimg.billbear.cn/images/amap/5757ef9d?imageMogr2/crop/!196x196a0a0/thumbnail/!102p","address":"广渠门内大街35-6号","miles":100,"actDescribe":{"中信银行":"9积分星巴克"}},{"shopId":"57a446840926a84a3d6945a3","shopName":"屈臣氏(北京富贵园店)","logoSmall":"https://qnimg.billbear.cn/images/amap/5757ef9d?imageMogr2/crop/!196x196a0a0/thumbnail/!102p","address":"广渠门内大街35-6号","miles":100,"actDescribe":{"中信银行":"9积分星巴克"}},{"shopId":"57a446840926a84a3d6945a3","shopName":"屈臣氏(北京富贵园店)","logoSmall":"https://qnimg.billbear.cn/images/amap/5757ef9d?imageMogr2/crop/!196x196a0a0/thumbnail/!102p","address":"广渠门内大街35-6号","miles":100,"actDescribe":{"中信银行":"9积分星巴克"}},{"shopId":"57a446840926a84a3d6945a3","shopName":"屈臣氏(北京富贵园店)","logoSmall":"https://qnimg.billbear.cn/images/amap/5757ef9d?imageMogr2/crop/!196x196a0a0/thumbnail/!102p","address":"广渠门内大街35-6号","miles":100,"actDescribe":{"中信银行":"9积分星巴克"}},{"shopId":"57a446840926a84a3d6945a3","shopName":"屈臣氏(北京富贵园店)","logoSmall":"https://qnimg.billbear.cn/images/amap/5757ef9d?imageMogr2/crop/!196x196a0a0/thumbnail/!102p","address":"广渠门内大街35-6号","miles":100,"actDescribe":{"中信银行":"9积分星巴克"}},{"shopId":"57a446840926a84a3d6945a3","shopName":"屈臣氏(北京富贵园店)","logoSmall":"https://qnimg.billbear.cn/images/amap/5757ef9d?imageMogr2/crop/!196x196a0a0/thumbnail/!102p","address":"广渠门内大街35-6号","miles":100,"actDescribe":{"中信银行":"9积分星巴克"}},{"shopId":"57a446840926a84a3d6945a3","shopName":"屈臣氏(北京富贵园店)","logoSmall":"https://qnimg.billbear.cn/images/amap/5757ef9d?imageMogr2/crop/!196x196a0a0/thumbnail/!102p","address":"广渠门内大街35-6号","miles":100,"actDescribe":{"中信银行":"9积分星巴克"}}]
     */

    @SerializedName("code")
    private String code;
    @SerializedName("shopDto")
    private List<ShopDtoBean> shopDto;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ShopDtoBean> getShopDto() {
        return shopDto;
    }

    public void setShopDto(List<ShopDtoBean> shopDto) {
        this.shopDto = shopDto;
    }




}
