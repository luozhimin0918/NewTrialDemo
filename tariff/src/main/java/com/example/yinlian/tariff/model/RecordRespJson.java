package com.example.yinlian.tariff.model;

import java.io.Serializable;

/**
 * Created by Luozhimin on 2018-06-21.10:49
 */
public class RecordRespJson implements Serializable {


    /**
     * msg : 业务处理成功!新增支付流水成功
     * data : {"gmtModified":"","tariffTag":"套餐一","orderId":"","totalPrice":1,"totalTerm":1,"remainingDays":41,"orderStatus":"2","updater":"","posId":"925fb95c7c67400a8fb2bac7bfdab8bf","artId":"","lastPaymentTerm":1,"isDeleted":0,"probationStartTime":"2018-10-15 10:16:22","appId":"b384e0b90c274493ad13aea8becd01ab","startTime":"2018-10-15 10:16:22","lastPaymentPrice":0,"tariffDesc":"客流分析月套餐","creator":"","apvId":"cfe97529f04d441ca81029ed91949377","probationEndTime":"2018-10-25 10:16:22","gmtCreate":"","lastPaymentTime":"2018-10-15 15:13:24","endTime":"2018-11-25 15:13:24","ageId":"00010703"}
     * state : 0001
     */

    private String msg;
    private DataBean data;
    private String state;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static class DataBean implements Serializable {
        /**
         * gmtModified :
         * tariffTag : 套餐一
         * orderId :
         * totalPrice : 1
         * totalTerm : 1
         * remainingDays : 41
         * orderStatus : 2
         * updater :
         * posId : 925fb95c7c67400a8fb2bac7bfdab8bf
         * artId :
         * lastPaymentTerm : 1
         * isDeleted : 0
         * probationStartTime : 2018-10-15 10:16:22
         * appId : b384e0b90c274493ad13aea8becd01ab
         * startTime : 2018-10-15 10:16:22
         * lastPaymentPrice : 0
         * tariffDesc : 客流分析月套餐
         * creator :
         * apvId : cfe97529f04d441ca81029ed91949377
         * probationEndTime : 2018-10-25 10:16:22
         * gmtCreate :
         * lastPaymentTime : 2018-10-15 15:13:24
         * endTime : 2018-11-25 15:13:24
         * ageId : 00010703
         */

        private String gmtModified;
        private String tariffTag;
        private String orderId;
        private int totalPrice;
        private int totalTerm;
        private int remainingDays;
        private String orderStatus;
        private String updater;
        private String posId;
        private String artId;
        private int lastPaymentTerm;
        private int isDeleted;
        private String probationStartTime;
        private String appId;
        private String startTime;
        private int lastPaymentPrice;
        private String tariffDesc;
        private String creator;
        private String apvId;
        private String probationEndTime;
        private String gmtCreate;
        private String lastPaymentTime;
        private String endTime;
        private String ageId;

        public String getGmtModified() {
            return gmtModified;
        }

        public void setGmtModified(String gmtModified) {
            this.gmtModified = gmtModified;
        }

        public String getTariffTag() {
            return tariffTag;
        }

        public void setTariffTag(String tariffTag) {
            this.tariffTag = tariffTag;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public int getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
        }

        public int getTotalTerm() {
            return totalTerm;
        }

        public void setTotalTerm(int totalTerm) {
            this.totalTerm = totalTerm;
        }

        public int getRemainingDays() {
            return remainingDays;
        }

        public void setRemainingDays(int remainingDays) {
            this.remainingDays = remainingDays;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getUpdater() {
            return updater;
        }

        public void setUpdater(String updater) {
            this.updater = updater;
        }

        public String getPosId() {
            return posId;
        }

        public void setPosId(String posId) {
            this.posId = posId;
        }

        public String getArtId() {
            return artId;
        }

        public void setArtId(String artId) {
            this.artId = artId;
        }

        public int getLastPaymentTerm() {
            return lastPaymentTerm;
        }

        public void setLastPaymentTerm(int lastPaymentTerm) {
            this.lastPaymentTerm = lastPaymentTerm;
        }

        public int getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(int isDeleted) {
            this.isDeleted = isDeleted;
        }

        public String getProbationStartTime() {
            return probationStartTime;
        }

        public void setProbationStartTime(String probationStartTime) {
            this.probationStartTime = probationStartTime;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public int getLastPaymentPrice() {
            return lastPaymentPrice;
        }

        public void setLastPaymentPrice(int lastPaymentPrice) {
            this.lastPaymentPrice = lastPaymentPrice;
        }

        public String getTariffDesc() {
            return tariffDesc;
        }

        public void setTariffDesc(String tariffDesc) {
            this.tariffDesc = tariffDesc;
        }

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public String getApvId() {
            return apvId;
        }

        public void setApvId(String apvId) {
            this.apvId = apvId;
        }

        public String getProbationEndTime() {
            return probationEndTime;
        }

        public void setProbationEndTime(String probationEndTime) {
            this.probationEndTime = probationEndTime;
        }

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public String getLastPaymentTime() {
            return lastPaymentTime;
        }

        public void setLastPaymentTime(String lastPaymentTime) {
            this.lastPaymentTime = lastPaymentTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getAgeId() {
            return ageId;
        }

        public void setAgeId(String ageId) {
            this.ageId = ageId;
        }
    }
}
