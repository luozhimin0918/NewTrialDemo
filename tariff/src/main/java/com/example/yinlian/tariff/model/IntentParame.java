package com.example.yinlian.tariff.model;

import java.io.Serializable;

/**
 * Created by Luozhimin on 2018-06-21.10:49
 */
public class IntentParame implements Serializable {
    private String setMealName ;//套餐名称
    private String setMealDesc;//套餐详情
    private String adTextTitle;//促销广告标题
    private String appId;//计费接口的appId
    private String appKey;//计费接口的appKey

    public String getAdTextTitle() {
        return adTextTitle;
    }

    public void setAdTextTitle(String adTextTitle) {
        this.adTextTitle = adTextTitle;
    }

    public String getSetMealName() {
        return setMealName;
    }

    public void setSetMealName(String setMealName) {
        this.setMealName = setMealName;
    }

    public String getSetMealDesc() {
        return setMealDesc;
    }

    public void setSetMealDesc(String setMealDesc) {
        this.setMealDesc = setMealDesc;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
