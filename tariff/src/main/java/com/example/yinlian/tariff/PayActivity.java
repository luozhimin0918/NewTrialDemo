package com.example.yinlian.tariff;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.alibaba.fastjson.JSON;
import com.example.yinlian.tariff.index.ApiManager;
import com.example.yinlian.tariff.lisetener.PayStateListenerManager;
import com.example.yinlian.tariff.model.OrderinfiRespJson;
import com.example.yinlian.tariff.model.OrderinfiTime;
import com.example.yinlian.tariff.model.PriceInfo;
import com.example.yinlian.tariff.model.ReqDetailJson;
import com.example.yinlian.tariff.model.TariffRespJson;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luozhimin on 2019/3/8.15:10
 */
public class PayActivity extends Activity implements View.OnClickListener {
    ImageButton back_imag;
    RecyclerView recycler_view;
    private List<PriceInfo> priceInfoList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContentView(R.layout.pay_mian);
        init();
        doing();
    }

    private void doing() {
        final ApiManager apiManager = ApiManager.getInstance(this);
        final String appId ="6694fb55b3b446809aec8002b9a7a0e8";
        final String appKey="ac6d287a30ef498c89ae2bb7fd27889d";
        final ReqDetailJson reqDetailJson = new ReqDetailJson();
        reqDetailJson.setTariffDescList("");
        apiManager.getTariffInfo(appId, appKey, reqDetailJson, new ApiManager.RespCallBack() {
            @Override
            public void onResponse(String jsonRespString) {
                     KLog.json("getTariffInfo",jsonRespString);
                TariffRespJson tariffRespJson = JSON.parseObject(jsonRespString, TariffRespJson.class);
                if (tariffRespJson.getData() != null && tariffRespJson.getData().getTariffInfoList() != null) {
                    //有套餐的

                    KLog.d("getTariffInfo", tariffRespJson.getMsg());
                    apiManager.getOrderInfo(appId, appKey, reqDetailJson, new ApiManager.RespCallBack() {
                        @Override
                        public void onResponse(String jsonRespString) {
                            KLog.json("getOrderInfo", jsonRespString);
                            OrderinfiRespJson orderinfiRespJson = JSON.parseObject(jsonRespString, OrderinfiRespJson.class);

                            if (orderinfiRespJson != null && orderinfiRespJson.getData() != null) {
                                String respDetailJson = orderinfiRespJson.getData().getRespDetail();
                                if (respDetailJson == null) {
                                    /**
                                     *没订单
                                     */
                                }else{
                                    /**
                                     *有订单
                                     */
                                    List<OrderinfiTime> orderinfiTimeList = JSON.parseArray(respDetailJson, OrderinfiTime.class);
                                    int RemainingDays = orderinfiTimeList.get(0).getRemainingDays();
                                    if (RemainingDays == 0) {//套餐过期后，提示框出现

                                    }else{
                                        //还剩多少天
                                    }
                                }
                            }

                        }
                    }, new ApiManager.RespErrorCallBack() {
                        @Override
                        public void onError(String errorStr) {

                        }
                    });
                }



            }
        }, new ApiManager.RespErrorCallBack() {
            @Override
            public void onError(String errorStr) {
                KLog.d("getTariffInfo",errorStr);

            }
        });
        for(int i=0;i<5;i++){
            PriceInfo priceInfo=new PriceInfo();
            priceInfo.setTariffDesc("");
            priceInfoList.add(priceInfo);
        }

        //价格栏
        //获取RecyclerView的实例
        //创建一个layoutManager，这里使用LinearLayoutManager指定为线性，也就可以有ListView这样的效果
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //调整RecyclerView的排列方向
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //完成layoutManager设置
        recycler_view.setLayoutManager(layoutManager);
        //创建IconAdapter的实例同时将iconList传入其构造函数
        PriceLanAdapter adapter = new PriceLanAdapter(priceInfoList);
        //完成adapter设置
        recycler_view.setAdapter(adapter);
        adapter.setmOnItemClickListener(new PriceLanAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                KLog.d(position+"");
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    private void init() {
        back_imag=findViewById(R.id.back_imag);
        back_imag.setOnClickListener(this);
        recycler_view=findViewById(R.id.recycler_view);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        PayStateListenerManager.getInstance().connected();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.back_imag) {
            finish();
            PayStateListenerManager.getInstance().connected();
        }

    }
}
