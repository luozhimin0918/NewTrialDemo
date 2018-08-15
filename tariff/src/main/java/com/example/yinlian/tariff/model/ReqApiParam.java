package com.example.yinlian.tariff.model;

import java.io.Serializable;

/**
 * Created by Luozhimin on 2018-06-22.9:53
 */
public class ReqApiParam implements Serializable {


    /**
     * reqDetail : str
     * appInfo : {"appName":"靓丽前台-银商版","appId":"afd2baf088034179b4c98826b4d9fcca","appPackName":"com.shboka.beautyorderums","appVersionCode":"3.0.6.1"}
     * interType : BMP-QUERY
     * version : 001
     * deviceInfo : {"prodCode":"19","firmCode":"109","deviceSn":"0820043480"}
     * mac : ums2018
     */

    private String reqDetail;
    private AppInfoBean appInfo;
    private String interType;
    private String version;
    private DeviceInfoBean deviceInfo;
    private String mac;

    public String getReqDetail() {
        return reqDetail;
    }

    public void setReqDetail(String reqDetail) {
        this.reqDetail = reqDetail;
    }

    public AppInfoBean getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AppInfoBean appInfo) {
        this.appInfo = appInfo;
    }

    public String getInterType() {
        return interType;
    }

    public void setInterType(String interType) {
        this.interType = interType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public DeviceInfoBean getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfoBean deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public static class AppInfoBean {
        /**
         * appName : 靓丽前台-银商版
         * appId : afd2baf088034179b4c98826b4d9fcca
         * appPackName : com.shboka.beautyorderums
         * appVersionCode : 3.0.6.1
         */

        private String appName;
        private String appId;
        private String appPackName;
        private String appVersionCode;

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getAppPackName() {
            return appPackName;
        }

        public void setAppPackName(String appPackName) {
            this.appPackName = appPackName;
        }

        public String getAppVersionCode() {
            return appVersionCode;
        }

        public void setAppVersionCode(String appVersionCode) {
            this.appVersionCode = appVersionCode;
        }
    }

    public static class DeviceInfoBean {
        /**
         * prodCode : 19
         * firmCode : 109
         * deviceSn : 0820043480
         */

        private String prodCode;
        private String firmCode;
        private String deviceSn;

        public String getProdCode() {
            return prodCode;
        }

        public void setProdCode(String prodCode) {
            this.prodCode = prodCode;
        }

        public String getFirmCode() {
            return firmCode;
        }

        public void setFirmCode(String firmCode) {
            this.firmCode = firmCode;
        }

        public String getDeviceSn() {
            return deviceSn;
        }

        public void setDeviceSn(String deviceSn) {
            this.deviceSn = deviceSn;
        }
    }
}
