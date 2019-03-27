package com.example.yinlian.tariff;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.yinlian.tariff.index.ApiManager;
import com.example.yinlian.tariff.lisetener.PayStateListenerManager;
import com.example.yinlian.tariff.model.OrderinfiRespJson;
import com.example.yinlian.tariff.model.OrderinfiTime;
import com.example.yinlian.tariff.model.RecordRespJson;
import com.example.yinlian.tariff.model.ReqDetailJson;
import com.example.yinlian.tariff.model.TariffRespJson;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;
import android.os.Handler;

/**
 * Created by Luozhimin on 2019/3/8.15:10
 */
public class PayActivity extends Activity implements View.OnClickListener {
    ImageButton back_imag;
    RecyclerView recycler_view;
    TextView RemainingDayText,xuMoney,tariffTag;
    LinearLayout discountLinear,shopButLinear;//试用按钮，立即开通按钮
    private List<TariffRespJson.DataBean.TariffInfoListBean> priceInfoList = new ArrayList<>();
    private int SelectTaoPosition = -1;//默认选择的套餐下标
    ApiManager apiManager ;
    String appId = "6694fb55b3b446809aec8002b9a7a0e8";
    String appKey = "ac6d287a30ef498c89ae2bb7fd27889d";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContentView(R.layout.pay_mian);
        apiManager= ApiManager.getInstance(this);
        init();
        doing();
    }

    private void doing() {
        final PriceLanAdapter adapter;
        //价格栏
        //获取RecyclerView的实例
        //创建一个layoutManager，这里使用LinearLayoutManager指定为线性，也就可以有ListView这样的效果
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //调整RecyclerView的排列方向
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //完成layoutManager设置
        recycler_view.setLayoutManager(layoutManager);
        //创建IconAdapter的实例同时将iconList传入其构造函数
        adapter = new PriceLanAdapter(priceInfoList);
        //完成adapter设置
        recycler_view.setAdapter(adapter);
        adapter.setmOnItemClickListener(new PriceLanAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                KLog.d(position + "");
                SelectTaoPosition=position;

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

        final ReqDetailJson reqDetailJson = new ReqDetailJson();
        reqDetailJson.setTariffDescList("");
        apiManager.getTariffInfo(appId, appKey, reqDetailJson, new ApiManager.RespCallBack() {
            @Override
            public void onResponse(String jsonRespString) {
                KLog.json("getTariffInfo", jsonRespString);
                TariffRespJson tariffRespJson = JSON.parseObject(jsonRespString, TariffRespJson.class);
                if (tariffRespJson.getData() != null && tariffRespJson.getData().getTariffInfoList() != null) {
                    //有套餐的
                    List<TariffRespJson.DataBean.TariffInfoListBean> tariffInfoList= tariffRespJson.getData().getTariffInfoList();
                    priceInfoList.addAll(tariffInfoList);
                    adapter.notifyDataSetChanged();


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
                                    RemainingDayText.setText("未开通");
                                    xuMoney.setText("立即开通");//未开通了服务，显示立即开通
                                } else {
                                    /**
                                     *有订单
                                     */
                                    List<OrderinfiTime> orderinfiTimeList = JSON.parseArray(respDetailJson, OrderinfiTime.class);
                                    int RemainingDays = orderinfiTimeList.get(0).getRemainingDays();
                                    if (RemainingDays == 0) {//套餐过期后，提示框出现
                                        RemainingDayText.setText("已过期");
                                    } else {
                                        //还剩多少天
                                      String   upString = "还剩<font color='#FB493F'><bold>" + RemainingDays + "</bold></font>天";
                                        RemainingDayText.setText(Html.fromHtml(upString));
                                    }
                                    xuMoney.setText("立即续费");//开通了服务，显示立即续费
                                    discountLinear.setVisibility(View.GONE);//有订单后都没有试用期了
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
                KLog.d("getTariffInfo", errorStr);

            }
        });
        for (int i = 0; i < 5; i++) {
            TariffRespJson.DataBean.TariffInfoListBean priceInfo = new TariffRespJson.DataBean.TariffInfoListBean();
            priceInfo.setTariffDesc("");
//            priceInfoList.add(priceInfo);
        }


    }

    private void init() {
        back_imag = findViewById(R.id.back_imag);
        back_imag.setOnClickListener(this);
        recycler_view = findViewById(R.id.recycler_view);
        RemainingDayText=findViewById(R.id.RemainingDayText);
        xuMoney=findViewById(R.id.xuMoney);
        discountLinear=findViewById(R.id.discountLinear);
        tariffTag=findViewById(R.id.tariffTag);
        shopButLinear=findViewById(R.id.shopButLinear);
        shopButLinear.setOnClickListener(this);
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
        if(i==R.id.shopButLinear){
            if(SelectTaoPosition==-1){//如果没有选中套餐列表，找到默认套餐
                    for(int j=0;j<priceInfoList.size();j++){
                        if(priceInfoList.get(j).getIsDefaulted()==1){
                           SelectTaoPosition=j;
                            break;
                        }
                    }
            }
            KLog.d(""+SelectTaoPosition);
            int priceNow = (int) (priceInfoList.get(SelectTaoPosition).getPresentPrice()*100);//现价
            String goodName =priceInfoList.get(SelectTaoPosition).getTariffDesc();//商品描述
            callPay(priceNow,goodName);
        }

    }

    /**
     * callPay支付
     * @param priceNow 支付金额
     * @param goodName 支付订单名称
     */
    private void callPay(int priceNow, String goodName) {
        new RewardPay().pay(getApplicationContext(),goodName,priceNow+"", new RewardPay.OnPayResultListener() {
            @Override
            public void onPayResult(int resultCode) {
                if(resultCode==0){
                  KLog.d("onPayResult",resultCode+" >>>>0");
                    ReqDetailJson reqDetailJson = new ReqDetailJson();
                    TariffRespJson.DataBean.TariffInfoListBean modelTari = priceInfoList.get(SelectTaoPosition);
                    reqDetailJson.setTariffDesc(modelTari.getTariffDesc());//套餐描述
                    reqDetailJson.setPaymentPrice(modelTari.getPresentPrice()+"");//现价
                    reqDetailJson.setPaymentTerm(modelTari.getServiceTerm()+"");//服务期
                    reqDetailJson.setPurchaseQuantity("1");
                    apiManager.getRecordPaymentInfo(appId, appKey, reqDetailJson, new ApiManager.RespCallBack() {
                        @Override
                        public void onResponse(String jsonRespString) {
                            KLog.json("ApiRecordPay",jsonRespString);
                            RecordRespJson recordRespJson =JSON.parseObject(jsonRespString,RecordRespJson.class);
                            if(recordRespJson.getState().equals("0001")){
                              //  finish();
                                                /*  GlobalValueManager.getInstance().setPayTaoProbeValue(false);//购买套餐成功，允许开探针
                                                  Intent intent = new Intent(mContext, ProbeService.class);
                                                  intent.putExtra("openOrCloseProbe", "open");
                                                  startService(intent);*/
                            }
                        }
                    }, new ApiManager.RespErrorCallBack() {
                        @Override
                        public void onError(String errorStr) {
                            KLog.d("ApiRecordPay",errorStr);
                        }
                    });
                    myHandler.sendEmptyMessage(0);//支付成功

                }else if(resultCode==-1){
                    KLog.d("onPayResult",resultCode+" >>>>-1");
                    myHandler.sendEmptyMessage(-1);// 接口调用失败

                }else{
                    KLog.d("onPayResult",resultCode+" >>>>失败");
                    myHandler.sendEmptyMessage(2);//  支付失败，重新支付

                }
            }
        });
    }
    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Toast.makeText(getApplicationContext(),"支付成功",Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    Toast.makeText(getApplicationContext(),"支付失败，重新支付",Toast.LENGTH_LONG).show();
                    break;
                case -1:
                    Toast.makeText(getApplicationContext(),"接口调用失败",Toast.LENGTH_LONG).show();
                    break;
            }

        }
    };
}
