package com.example.yinlian.tariff.model;

import java.io.Serializable;

/**
 * Created by Luozhimin on 2018-06-22.9:53
 */
public class ReqDetailJson implements Serializable {

    /**
     * tariffDescList : str
     * tariffDesc : str
     * paymentPrice : flt
     * purchaseQuantity : num
     * paymentTerm : num
     */

    private String tariffDescList;
    private String tariffDesc;
    private String paymentPrice;
    private String purchaseQuantity;
    private String paymentTerm;

    public String getTariffDescList() {
        return tariffDescList;
    }

    public void setTariffDescList(String tariffDescList) {
        this.tariffDescList = tariffDescList;
    }

    public String getTariffDesc() {
        return tariffDesc;
    }

    public void setTariffDesc(String tariffDesc) {
        this.tariffDesc = tariffDesc;
    }

    public String getPaymentPrice() {
        return paymentPrice;
    }

    public void setPaymentPrice(String paymentPrice) {
        this.paymentPrice = paymentPrice;
    }

    public String getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(String purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public String getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(String paymentTerm) {
        this.paymentTerm = paymentTerm;
    }
}
