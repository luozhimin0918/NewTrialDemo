package com.example.yinlian.tariff.model;

import java.io.Serializable;

/**
 * Created by Luozhimin on 2019/3/22.15:20
 */
public class PriceInfo implements Serializable {
    /**
     * tariffDesc : 一个月
     * tariffTag : 套餐一
     * probation : 15
     * isDefaulted : 1
     * originalPrice : 10
     * presentPrice : 10
     * discount : 10
     * serviceTerm : 1
     */

    private String tariffDesc;
    private String tariffTag;
    private int probation;
    private int isDefaulted;
    private int originalPrice;
    private int presentPrice;
    private int discount;
    private int serviceTerm;

    public String getTariffDesc() {
        return tariffDesc;
    }

    public void setTariffDesc(String tariffDesc) {
        this.tariffDesc = tariffDesc;
    }

    public String getTariffTag() {
        return tariffTag;
    }

    public void setTariffTag(String tariffTag) {
        this.tariffTag = tariffTag;
    }

    public int getProbation() {
        return probation;
    }

    public void setProbation(int probation) {
        this.probation = probation;
    }

    public int getIsDefaulted() {
        return isDefaulted;
    }

    public void setIsDefaulted(int isDefaulted) {
        this.isDefaulted = isDefaulted;
    }

    public int getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(int originalPrice) {
        this.originalPrice = originalPrice;
    }

    public int getPresentPrice() {
        return presentPrice;
    }

    public void setPresentPrice(int presentPrice) {
        this.presentPrice = presentPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getServiceTerm() {
        return serviceTerm;
    }

    public void setServiceTerm(int serviceTerm) {
        this.serviceTerm = serviceTerm;
    }
}
