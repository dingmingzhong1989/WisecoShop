package com.wiseco.wisecoshop.bean.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by wiseco on 2018/12/24.
 */

public  class CardBrandsListBean implements Serializable {
    /**
     * id : 1
     * name : 万事达-银联
     * nemaen : no
     * logourl : no
     * gmtCreate : 1545645341000
     */

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("nemaen")
    private String nemaen;
    @SerializedName("logourl")
    private String logourl;
    @SerializedName("gmtCreate")
    private long gmtCreate;

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

    public String getNemaen() {
        return nemaen;
    }

    public void setNemaen(String nemaen) {
        this.nemaen = nemaen;
    }

    public String getLogourl() {
        return logourl;
    }

    public void setLogourl(String logourl) {
        this.logourl = logourl;
    }

    public long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}