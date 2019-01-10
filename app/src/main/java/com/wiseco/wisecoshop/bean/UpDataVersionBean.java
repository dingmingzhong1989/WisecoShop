package com.wiseco.wisecoshop.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by wiseco on 2018/11/8.
 */

public class UpDataVersionBean {
    /**
     * code : S
     * item : {"version":"1.1.0","content":"升级生活优惠，强化用户体验","necessary":false}
     * isSuccess : true
     */

    @SerializedName("code")
    private String code;
    @SerializedName("item")
    private ItemBean item;
    @SerializedName("isSuccess")
    private boolean isSuccess;

    public static UpDataVersionBean objectFromData(String str) {

        return new Gson().fromJson(str, UpDataVersionBean.class);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ItemBean getItem() {
        return item;
    }

    public void setItem(ItemBean item) {
        this.item = item;
    }

    public boolean isIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public static class ItemBean {
        /**
         * version : 1.1.0
         * content : 升级生活优惠，强化用户体验
         * necessary : false
         */

        @SerializedName("version")
        private String version;
        @SerializedName("content")
        private String content;
        @SerializedName("necessary")
        private boolean necessary;

        public static ItemBean objectFromData(String str) {

            return new Gson().fromJson(str, ItemBean.class);
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public boolean isNecessary() {
            return necessary;
        }

        public void setNecessary(boolean necessary) {
            this.necessary = necessary;
        }
    }
   /* *//**
     * code : S
     * item : {"version":"version","content":"content"}
     * isSuccess : true
     *//*

    private String code;
    private ItemBean item;
    private boolean isSuccess;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ItemBean getItem() {
        return item;
    }

    public void setItem(ItemBean item) {
        this.item = item;
    }

    public boolean isIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public static class ItemBean {
        *//**
         * version : version
         * content : content
         *//*

        private String version;
        private String content;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }*/
}
