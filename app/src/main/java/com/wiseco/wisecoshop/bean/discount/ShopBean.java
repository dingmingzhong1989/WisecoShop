package com.wiseco.wisecoshop.bean.discount;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wiseco on 2018/12/27.
 */

public class ShopBean {
    /**
     * shopActivity : {"shopId":"57a446840926a84a3d6945a3","shopName":"屈臣氏(北京富贵园店)",
     * "logoSmall":"https://qnimg.billbear.cn/images/amap/5757ef9d?imageMogr2/crop/!196x196a0a0/thumbnail/!102p",
     * "latitude":31.172096,"longitude":121.391724,"address":"广渠门内大街35-6号","miles":100,"acts":
     * [{"id":"5a05139bc95d7a4a36d9748f","title":"9积分星巴克","bankName":"中信银行","category":"餐饮","bigImgUrl":"活动图片","availableTime":"每天 10:00-23:55"},{"id":"5a05139bc95d7a4a36d9748f","title":"9积分星巴克","bankName":"中信银行","category":"餐饮","bigImgUrl":"活动图片","availableTime":"每天 10:00-23:55"},{"id":"5a05139bc95d7a4a36d9748f","title":"9积分星巴克","bankName":"中信银行","category":"餐饮","bigImgUrl":"活动图片","availableTime":"每天 10:00-23:55"},{"id":"5a05139bc95d7a4a36d9748f","title":"9积分星巴克","bankName":"中信银行","category":"餐饮","bigImgUrl":"活动图片","availableTime":"每天 10:00-23:55"},{"id":"5a05139bc95d7a4a36d9748f","title":"9积分星巴克","bankName":"中信银行","category":"餐饮","bigImgUrl":"活动图片","availableTime":"每天 10:00-23:55"},{"id":"5a05139bc95d7a4a36d9748f","title":"9积分星巴克","bankName":"中信银行","category":"餐饮","bigImgUrl":"活动图片","availableTime":"每天 10:00-23:55"},{"id":"5a05139bc95d7a4a36d9748f","title":"9积分星巴克","bankName":"中信银行","category":"餐饮","bigImgUrl":"活动图片","availableTime":"每天 10:00-23:55"},{"id":"5a05139bc95d7a4a36d9748f","title":"9积分星巴克","bankName":"中信银行","category":"餐饮","bigImgUrl":"活动图片","availableTime":"每天 10:00-23:55"},{"id":"5a05139bc95d7a4a36d9748f","title":"9积分星巴克","bankName":"中信银行","category":"餐饮","bigImgUrl":"活动图片","availableTime":"每天 10:00-23:55"},{"id":"5a05139bc95d7a4a36d9748f","title":"9积分星巴克","bankName":"中信银行","category":"餐饮","bigImgUrl":"活动图片","availableTime":"每天 10:00-23:55"}]}
     * code : S
     */

    @SerializedName("shopActivity")
    private ShopActivityBean shopActivity;
    @SerializedName("code")
    private String code;

    public ShopActivityBean getShopActivity() {
        return shopActivity;
    }

    public void setShopActivity(ShopActivityBean shopActivity) {
        this.shopActivity = shopActivity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
