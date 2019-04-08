package com.example.yinlian.tariff.lisetener;

/**
 * Created by Luozhimin on 2019/3/8.15:02
 */
public class PayStateListenerManager {
    private payListener mListener;

    private PayStateListenerManager() {
    }

    private static final PayStateListenerManager manager = new PayStateListenerManager();

    public static PayStateListenerManager getInstance() {
        return manager;
    }

    public void setConnectionStateListener(payListener mListener) {
        this.mListener = mListener;
    }

    public void onComBuyState(boolean isbuy,int days,String tariffDesc) {
        if (mListener != null) {
            mListener.onComBuyState( isbuy, days, tariffDesc);
        }
    }


}
