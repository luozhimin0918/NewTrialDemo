package com.example.yinlian.tariff.model;

import java.util.List;

/**
 * Created by Luozhimin on 2018/8/2.16:55
 */
public class TariffInfoRespBean {

    /**
     * msg : 业务处理成功!
     * data : {"tariffInfoList":[{"tariffDesc":"一个月","tariffTag":"套餐一","probation":15,"isDefaulted":1,"originalPrice":10,"presentPrice":10,"discount":10,"serviceTerm":1},{"tariffDesc":"半年","tariffTag":"套餐二","probation":15,"isDefaulted":2,"originalPrice":60,"presentPrice":54,"discount":9,"serviceTerm":6},{"tariffDesc":"一年","tariffTag":"套餐三","probation":15,"isDefaulted":2,"originalPrice":120,"presentPrice":96,"discount":8,"serviceTerm":12}]}
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

    public static class DataBean {
        private List<TariffInfoListBean> tariffInfoList;

        public List<TariffInfoListBean> getTariffInfoList() {
            return tariffInfoList;
        }

        public void setTariffInfoList(List<TariffInfoListBean> tariffInfoList) {
            this.tariffInfoList = tariffInfoList;
        }

        public static class TariffInfoListBean {
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
    }
}
