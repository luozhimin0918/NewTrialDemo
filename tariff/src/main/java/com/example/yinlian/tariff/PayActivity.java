package com.example.yinlian.tariff;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.yinlian.tariff.index.ApiManager;
import com.example.yinlian.tariff.lisetener.PayStateListenerManager;
import com.example.yinlian.tariff.model.PriceInfo;
import com.example.yinlian.tariff.model.ReqDetailJson;
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
        ApiManager apiManager = ApiManager.getInstance(this);

        final ReqDetailJson reqDetailJson = new ReqDetailJson();
        reqDetailJson.setTariffDescList("");
        apiManager.getTariffInfo("6694fb55b3b446809aec8002b9a7a0e8", "ac6d287a30ef498c89ae2bb7fd27889d", reqDetailJson, new ApiManager.RespCallBack() {
            @Override
            public void onResponse(String jsonRespString) {
                     KLog.json("getTariffInfo",jsonRespString);
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
