package com.example.yinlian.tariff.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Luozhimin on 2018/8/20.10:42
 */
public class TariffRespJson implements Serializable {


    /**
     * msg : 业务处理成功!
     * data : {"tariffInfoList":[{"tariffDesc":"客流分析月套餐","tariffTag":"套餐一","probation":10,"isDefaulted":1,"originalPrice":2,"presentPrice":1,"discount":5,"serviceTerm":1}]}
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

    public static class DataBean  implements Serializable {
        private List<TariffInfoListBean> tariffInfoList;

        public List<TariffInfoListBean> getTariffInfoList() {
            return tariffInfoList;
        }

        public void setTariffInfoList(List<TariffInfoListBean> tariffInfoList) {
            this.tariffInfoList = tariffInfoList;
        }

        public static class TariffInfoListBean  implements Serializable {
            /**
             * tariffDesc : 客流分析月套餐
             * tariffTag : 套餐一
             * probation : 10
             * isDefaulted : 1
             * originalPrice : 2
             * presentPrice : 1
             * discount : 5
             * serviceTerm : 1
             */

            private String tariffDesc;
            private String tariffTag;
            private int probation;
            private int isDefaulted;
            private double originalPrice;
            private double presentPrice;
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

            public double getOriginalPrice() {
                return originalPrice;
            }

            public void setOriginalPrice(double originalPrice) {
                this.originalPrice = originalPrice;
            }

            public double getPresentPrice() {
                return presentPrice;
            }

            public void setPresentPrice(double presentPrice) {
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
    }
}
