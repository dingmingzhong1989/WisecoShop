package com.wiseco.wisecoshop.bean.user;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by wiseco on 2019/1/10.
 */

public class AddCardBean {
    /**
     * code : S
     * id : 129
     */

    @SerializedName("code")
    private String code;
    @SerializedName("id")
    private int id;

    public static AddCardBean objectFromData(String str) {

        return new Gson().fromJson(str, AddCardBean.class);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
