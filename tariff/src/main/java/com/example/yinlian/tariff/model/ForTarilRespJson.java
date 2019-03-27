package com.example.yinlian.tariff.model;

import java.io.Serializable;

/**
 * Created by Luozhimin on 2018-06-21.10:49
 */
public class ForTarilRespJson implements Serializable {


    /**
     * msg : 业务处理成功!成功申请试用！
     * data : {"respDetail":"{\"probationStartTime\":\"2018-10-15 10:16:22\",\"remainingDays\":10,\"probationEndTime\":\"2018-10-25 10:16:22\"}","appInfo":"{\"appId\":\"6694fb55b3b446809aec8002b9a7a0e8\",\"appName\":\"客流分析\",\"appPackName\":\"com.ums.wifiprobe\",\"appVersionCode\":\"1.1.3\"}","interType":"BMP-QUERY","version":"001","deviceInfo":"{\"appId\":\"6694fb55b3b446809aec8002b9a7a0e8\",\"appName\":\"客流分析\",\"appPackName\":\"com.ums.wifiprobe\",\"appVersionCode\":\"1.1.3\"}","mac":"6E00ECE476E6751B2D056D062F246B6B04FB0E3AC33DA5218883FDAF4B9B51596E95708BB213CC01F8CB33018FF1DE2C46A212994137D045799D7B5930C2810A07DA3637DD693ECD2B8B79667F8397C022A08A9850EB72F1829FE75F844051FDC1B597D654710449E288AFE1555D0660200277929EB7AFEAA53B7DE355F28360CA273DA8289CE0504A2C05DE133ACF03FB817D46976D8A2954F75995E5338F91C022359D1BF360E50A9F06616E8000ACB63BE666DCACA9558F2BEB09903351E1BA585B80C5CA7C238975C1DCDCD275A40BFF632E6E18F46A7DB96B3FA8D7CD88DF7913929C43EECB8F358CBA447C861E3CFDA58DF8BA3B4EB5668815A52E9B0DC8250AEC9EEE50753801B0750C86FCAEDC17AB13F4250624797DCBF59E5297A151E2CFD70422B1E71013235C324AB657804BCA9D17B06D7B70C8C4F400E2680D2B426A73795281ECDCA3370226092D1FC7E0058759761F955965DB501281DDE5CD9F755314547D2B69C537FC9FC04AA6"}
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
         * respDetail : {"probationStartTime":"2018-10-15 10:16:22","remainingDays":10,"probationEndTime":"2018-10-25 10:16:22"}
         * appInfo : {"appId":"6694fb55b3b446809aec8002b9a7a0e8","appName":"客流分析","appPackName":"com.ums.wifiprobe","appVersionCode":"1.1.3"}
         * interType : BMP-QUERY
         * version : 001
         * deviceInfo : {"appId":"6694fb55b3b446809aec8002b9a7a0e8","appName":"客流分析","appPackName":"com.ums.wifiprobe","appVersionCode":"1.1.3"}
         * mac : 6E00ECE476E6751B2D056D062F246B6B04FB0E3AC33DA5218883FDAF4B9B51596E95708BB213CC01F8CB33018FF1DE2C46A212994137D045799D7B5930C2810A07DA3637DD693ECD2B8B79667F8397C022A08A9850EB72F1829FE75F844051FDC1B597D654710449E288AFE1555D0660200277929EB7AFEAA53B7DE355F28360CA273DA8289CE0504A2C05DE133ACF03FB817D46976D8A2954F75995E5338F91C022359D1BF360E50A9F06616E8000ACB63BE666DCACA9558F2BEB09903351E1BA585B80C5CA7C238975C1DCDCD275A40BFF632E6E18F46A7DB96B3FA8D7CD88DF7913929C43EECB8F358CBA447C861E3CFDA58DF8BA3B4EB5668815A52E9B0DC8250AEC9EEE50753801B0750C86FCAEDC17AB13F4250624797DCBF59E5297A151E2CFD70422B1E71013235C324AB657804BCA9D17B06D7B70C8C4F400E2680D2B426A73795281ECDCA3370226092D1FC7E0058759761F955965DB501281DDE5CD9F755314547D2B69C537FC9FC04AA6
         */

        private String respDetail;
        private String appInfo;
        private String interType;
        private String version;
        private String deviceInfo;
        private String mac;

        public String getRespDetail() {
            return respDetail;
        }

        public void setRespDetail(String respDetail) {
            this.respDetail = respDetail;
        }

        public String getAppInfo() {
            return appInfo;
        }

        public void setAppInfo(String appInfo) {
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

        public String getDeviceInfo() {
            return deviceInfo;
        }

        public void setDeviceInfo(String deviceInfo) {
            this.deviceInfo = deviceInfo;
        }

        public String getMac() {
            return mac;
        }

        public void setMac(String mac) {
            this.mac = mac;
        }
    }
}
