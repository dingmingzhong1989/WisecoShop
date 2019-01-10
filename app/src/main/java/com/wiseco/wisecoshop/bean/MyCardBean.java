package com.wiseco.wisecoshop.bean;

import com.google.gson.annotations.SerializedName;
import com.wiseco.wisecoshop.bean.user.BankListBean;
import com.wiseco.wisecoshop.bean.user.CardBrandsListBean;
import com.wiseco.wisecoshop.bean.user.UserCardListBean;

import java.util.List;

/**
 * Created by wiseco on 2018/12/24.
 */

public class MyCardBean {
    /**
     * userCardList : []
     * code : S
     * bankList : [{"id":11,"code":"CCB","hasCC":true,"name":"建设银行","shortname":"建设银行","icon":"/images/bankicon/ccb.png"},{"id":12,"code":"CMBC","hasCC":true,"name":"民生银行","shortname":"民生银行","icon":"/images/bankicon/cmbc.png"},{"id":13,"code":"ABC","hasCC":true,"name":"农业银行","shortname":"农业银行","icon":"/images/bankicon/abc.png"},{"id":14,"code":"PINGAN","hasCC":true,"name":"平安银行","shortname":"平安银行","icon":"/images/bankicon/pingan.png"},{"id":15,"code":"WIBC","hasCC":true,"name":"兴业银行","shortname":"兴业银行","icon":"/images/bankicon/wibc.png"},{"id":16,"code":"PABC","hasCC":true,"name":"邮政银行","shortname":"邮政银行","icon":"/images/bankicon/psbc.png"},{"id":17,"code":"CMBCHINA","hasCC":true,"name":"招商银行","shortname":"招商银行","icon":"/images/bankicon/cmbchina.png"},{"id":18,"code":"BOC","hasCC":true,"name":"中国银行","shortname":"中国银行","icon":"/images/bankicon/boc.png"},{"id":19,"code":"CITIC","hasCC":true,"name":"中信银行","shortname":"中信银行","icon":"/images/bankicon/citic.png"},{"id":1,"code":"SPDB","hasCC":true,"name":"浦发银行","shortname":"浦发银行","icon":"/images/bankicon/spdb.png"},{"id":3,"code":"ICBC","hasCC":true,"name":"工商银行","shortname":"工商银行","icon":"/images/bankicon/icbc.png"},{"id":6,"code":"BANKCOMM","hasCC":true,"name":"交通银行","shortname":"交通银行","icon":"/images/bankicon/bankcomm.png"},{"id":7,"code":"CEBB","hasCC":true,"name":"光大银行","shortname":"光大银行","icon":"/images/bankicon/cebb.png"},{"id":8,"code":"CGBC","hasCC":true,"name":"广发银行","shortname":"广发银行","icon":"/images/bankicon/cgbc.png"},{"id":9,"code":"HXBC","hasCC":true,"name":"华夏银行","shortname":"华夏银行","icon":"/images/bankicon/hxbc.png"},{"id":20,"code":"BOSC","hasCC":true,"name":"上海银行","shortname":"上海银行","icon":"/images/bankicon/bosc.png"},{"id":10,"code":"HSBC","hasCC":true,"name":"汇丰银行","shortname":"汇丰银行","icon":"/images/bankicon/hsbc.png"}]
     * cardBrandsList : [{"id":1,"name":"万事达-银联","nemaen":"no","logourl":"no","gmtCreate":1545645341000},{"id":2,"name":"JCB-银联","nemaen":"no","logourl":"no","gmtCreate":1545645343000},{"id":3,"name":"VISA-银联","nemaen":"no","logourl":"no","gmtCreate":1545645343000},{"id":4,"name":"运通-银联","nemaen":"no","logourl":"no","gmtCreate":1545645343000},{"id":5,"name":"大莱-银联","nemaen":"no","logourl":"no","gmtCreate":1545645343000},{"id":6,"name":"银联","nemaen":"no","logourl":"no","gmtCreate":1545645343000},{"id":7,"name":"万事达","nemaen":"no","logourl":"no","gmtCreate":1545645344000},{"id":8,"name":"JCB","nemaen":"no","logourl":"no","gmtCreate":1545645344000},{"id":9,"name":"VISA","nemaen":"no","logourl":"no","gmtCreate":1545645344000},{"id":10,"name":"运通","nemaen":"no","logourl":"no","gmtCreate":1545645344000},{"id":11,"name":"大莱","nemaen":"no","logourl":"no","gmtCreate":1545645344000}]
     */

    @SerializedName("code")
    private String code;
    @SerializedName("userCardList")
    private List<UserCardListBean> userCardList;
    @SerializedName("bankList")
    private List<BankListBean> bankList;
    @SerializedName("cardBrandsList")
    private List<CardBrandsListBean> cardBrandsList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<UserCardListBean> getUserCardList() {
        return userCardList;
    }

    public void setUserCardList(List<UserCardListBean> userCardList) {
        this.userCardList = userCardList;
    }

    public List<BankListBean> getBankList() {
        return bankList;
    }

    public void setBankList(List<BankListBean> bankList) {
        this.bankList = bankList;
    }

    public List<CardBrandsListBean> getCardBrandsList() {
        return cardBrandsList;
    }

    public void setCardBrandsList(List<CardBrandsListBean> cardBrandsList) {
        this.cardBrandsList = cardBrandsList;
    }


}
