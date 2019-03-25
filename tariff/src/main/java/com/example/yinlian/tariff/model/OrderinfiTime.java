package com.example.yinlian.tariff.model;

import java.io.Serializable;

/**
 * Created by Luozhimin on 2018-06-21.10:49
 */
public class OrderinfiTime implements Serializable {


    /**
     * endTime : 2018-10-21 16:57:26
     * lastPaymentPrice : 0.0
     * lastPaymentTerm : 0
     * orderStatus : 1
     * probationStartTime : 2018-10-11 16:57:26
     * remainingDays : 10
     * startTime : 2018-10-11 16:57:26
     * tariffDesc : 客流分析月套餐
     * totalPrice : 0.0
     */

    private String endTime;
    private double lastPaymentPrice;
    private int lastPaymentTerm;
    private String orderStatus;
    private String probationStartTime;
    private int remainingDays;
    private String startTime;
    private String tariffDesc;
    private double totalPrice;

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public double getLastPaymentPrice() {
        return lastPaymentPrice;
    }

    public void setLastPaymentPrice(double lastPaymentPrice) {
        this.lastPaymentPrice = lastPaymentPrice;
    }

    public int getLastPaymentTerm() {
        return lastPaymentTerm;
    }

    public void setLastPaymentTerm(int lastPaymentTerm) {
        this.lastPaymentTerm = lastPaymentTerm;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getProbationStartTime() {
        return probationStartTime;
    }

    public void setProbationStartTime(String probationStartTime) {
        this.probationStartTime = probationStartTime;
    }

    public int getRemainingDays() {
        return remainingDays;
    }

    public void setRemainingDays(int remainingDays) {
        this.remainingDays = remainingDays;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getTariffDesc() {
        return tariffDesc;
    }

    public void setTariffDesc(String tariffDesc) {
        this.tariffDesc = tariffDesc;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
