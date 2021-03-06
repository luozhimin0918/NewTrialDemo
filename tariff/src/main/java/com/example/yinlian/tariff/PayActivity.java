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
import com.example.yinlian.tariff.index.NetWorkUtils;
import com.example.yinlian.tariff.lisetener.PayStateListenerManager;
import com.example.yinlian.tariff.model.ForTarilRespJson;
import com.example.yinlian.tariff.model.IntentParame;
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
    TextView RemainingDayText,xuMoney,shiyong,tariffTag,probationDay,setMealDesc,adTextTitle,recyText;
    LinearLayout discountLinear,shopButLinear,back_imag_linear;//试用按钮，立即开通按钮,返回键
    private List<TariffRespJson.DataBean.TariffInfoListBean> priceInfoList = new ArrayList<>();
    private int SelectTaoPosition = -1;//默认选择的套餐下标
    private boolean  ishaveDingdang=false;//是否有开通订单
    private String   ProbatinTariffDesc="";//申请试用的参数套餐详情
    ApiManager apiManager ;
    PriceLanAdapter adapter;
    String appId = null;//6694fb55b3b446809aec8002b9a7a0e8
    String appKey = null;//ac6d287a30ef498c89ae2bb7fd27889d
    String callPayAppKey=null;
    Activity  mActivity;
    private boolean isFreeUseOrShop =true;//true表示购买，false表示试用
    private String  TariffDescStr="";//购买成功的套餐名
    private int RemainingDaysStr;//购买成功的套餐下剩余天数
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContentView(R.layout.pay_mian);
        apiManager= ApiManager.getInstance(this);
        mActivity=this;
        init();
        getIntentInit();
        doing();
    }

    private void getIntentInit() {
        if(getIntent()!=null){
            //app传来的参数
            IntentParame intentParame   = (IntentParame) getIntent().getSerializableExtra("payIntent");
            if(intentParame!=null){
                try{
                    appId=intentParame.getAppId();
                    appKey=intentParame.getAppKey();
                    callPayAppKey=intentParame.getAppKey();
                    tariffTag.setText(intentParame.getSetMealName());
                    adTextTitle.setText(intentParame.getAdTextTitle());
                    setMealDesc.setText(Html.fromHtml(intentParame.getSetMealDesc()));
                }catch (NullPointerException e){

                }

            }
        }
    }

    private void doing() {
       LoadingDialog.showLoadingDialog(this);//显示进度条


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
                int probationIntSelect  =priceInfoList.get(SelectTaoPosition).getProbation();
                if(!ishaveDingdang&&probationIntSelect>0){
                    discountLinear.setVisibility(View.VISIBLE);
                    probationDay.setText("免费试用"+probationIntSelect+"天");
                    ProbatinTariffDesc= priceInfoList.get(SelectTaoPosition).getTariffDesc();//申请试用的参数
                }else{
                    discountLinear.setVisibility(View.GONE);
                }
                shiyong.setVisibility(View.GONE);
                xuMoney.setVisibility(View.VISIBLE);
                isFreeUseOrShop=true;
                discountLinear.setBackgroundResource(R.drawable.bg_center_white);

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        if(!NetWorkUtils.isNetworkConnected(this)){
            Toast.makeText(getApplicationContext(),"请检测网络连接",Toast.LENGTH_LONG).show();
            LoadingDialog.hideLoadingDialog();//取消加载进度条
            finish();
        }

        getSetListOrder();




    }

    private void getSetListOrder() {
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
                    priceInfoList.clear();
                    priceInfoList.addAll(tariffInfoList);
                    //测试数据
                    for (int i = 0; i < 3; i++) {
                        TariffRespJson.DataBean.TariffInfoListBean priceInfo = new TariffRespJson.DataBean.TariffInfoListBean();
                        priceInfo.setTariffDesc(">>"+i);
                        priceInfo.setTariffTag("TTTT"+i);
                        if(i%2==1){
                            priceInfo.setProbation(0);
                        }else{
                            priceInfo.setProbation(18);
                        }
                        priceInfo.setOriginalPrice(320+i*10);
                        priceInfo.setPresentPrice(120+i*10);
                        priceInfo.setServiceTerm(3*i);
//                        priceInfoList.add(priceInfo);
                    }
                    SelectTaoPosition=-1;//刷新数据的下标选择初始
                    adapter.setCanOnClick(true);//初始化可以点击
                    adapter.notifyDataSetChanged();


                    KLog.d("getTariffInfo", tariffRespJson.getMsg());
                    apiManager.getOrderInfo(appId, appKey, reqDetailJson, new ApiManager.RespCallBack() {
                        @Override
                        public void onResponse(String jsonRespString) {
                            KLog.json("getOrderInfo", jsonRespString);
                            OrderinfiRespJson orderinfiRespJson = JSON.parseObject(jsonRespString, OrderinfiRespJson.class);

                            if (orderinfiRespJson != null && orderinfiRespJson.getData() != null) {
                                String respDetailJson = orderinfiRespJson.getData().getRespDetail();
//                                respDetailJson=null;//todo
                                if (respDetailJson != null) {
                                    /**
                                     *有订单
                                     */
                                    ishaveDingdang=true;//有订单
                                    List<OrderinfiTime> orderinfiTimeList = JSON.parseArray(respDetailJson, OrderinfiTime.class);
                                    int RemainingDays = orderinfiTimeList.get(0).getRemainingDays();
                                    RemainingDaysStr=RemainingDays;//回调剩余天数赋值
                                    TariffDescStr=orderinfiTimeList.get(0).getTariffDesc();//回调购买的套餐描述
                                    if (RemainingDays == 0) {//套餐过期后，提示框出现
                                        RemainingDayText.setText("已过期");
                                    } else {
                                        //还剩多少天
                                        String   upString = "还剩<font color='#FB493F'><bold>" + RemainingDays + "</bold></font>天";
                                        RemainingDayText.setText(Html.fromHtml(upString));
                                        String  selectTariDesc = orderinfiTimeList.get(0).getTariffDesc();
                                        if(selectTariDesc!=null){
                                            for(int d=0;d<priceInfoList.size();d++){
                                                if(priceInfoList.get(d).getTariffDesc().equals(selectTariDesc)){
                                                    priceInfoList.get(d).setIsDefaulted(1);
                                                }else{
                                                    priceInfoList.get(d).setIsDefaulted(0);
                                                }
                                            }
                                            adapter.setCanOnClick(false);//不可以点击选项了
                                            adapter.notifyDataSetChanged();
                                        }
                                    }
                                    xuMoney.setText("立即续费");//开通了服务，显示立即续费
                                    discountLinear.setVisibility(View.GONE);//有订单后都没有试用期了

                                }
                            }else{
                                /**
                                 *没订单
                                 */
                                ishaveDingdang=false;//没有订单
                                RemainingDayText.setText("未开通");
                                xuMoney.setText("立即开通");//未开通了服务，显示立即开通
                                //查出后台默认套餐是否有试用期
                                int probationInt=0;
                                for(int j=0;j<priceInfoList.size();j++){
                                    if(priceInfoList.get(j).getIsDefaulted()==1){
                                        probationInt= priceInfoList.get(j).getProbation();
                                        ProbatinTariffDesc= priceInfoList.get(j).getTariffDesc();//申请试用的参数
                                        break;
                                    }
                                }
                                if(probationInt>0){
                                    discountLinear.setVisibility(View.VISIBLE);//没订单，根据套餐显示试用期了
                                    probationDay.setText("免费试用"+probationInt+"天");
                                }
                            }
                            LoadingDialog.hideLoadingDialog();//消失进度条
                        }
                    }, new ApiManager.RespErrorCallBack() {
                        @Override
                        public void onError(String errorStr) {
                            LoadingDialog.hideLoadingDialog();//取消加载进度条
                            KLog.json("getOrderInfo", errorStr);
                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(),tariffRespJson.getMsg(),Toast.LENGTH_LONG).show();
                    mActivity.finish();
                }


            }
        }, new ApiManager.RespErrorCallBack() {
            @Override
            public void onError(String errorStr) {
                KLog.d("getTariffInfo", errorStr);
                recyText.setVisibility(View.VISIBLE);//获取不到套餐，显示此设备未配置套餐
                LoadingDialog.hideLoadingDialog();//取消加载进度条
            }
        });
    }

    private void init() {
        back_imag = findViewById(R.id.back_imag);
        back_imag.setOnClickListener(this);
        recycler_view = findViewById(R.id.recycler_view);
        RemainingDayText=findViewById(R.id.RemainingDayText);
        xuMoney=findViewById(R.id.xuMoney);
        shiyong=findViewById(R.id.shiyong);
        discountLinear=findViewById(R.id.discountLinear);
        discountLinear.setOnClickListener(this);
        tariffTag=findViewById(R.id.tariffTag);
        shopButLinear=findViewById(R.id.shopButLinear);
        shopButLinear.setOnClickListener(this);
        back_imag_linear=findViewById(R.id.back_imag_linear);
        back_imag_linear.setOnClickListener(this);
        probationDay=findViewById(R.id.probationDay);
        setMealDesc=findViewById(R.id.setMealDesc);
        adTextTitle=findViewById(R.id.adTextTitle);
        recyText=findViewById(R.id.recyText);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        PayStateListenerManager.getInstance().onComBuyState(ishaveDingdang,RemainingDaysStr,TariffDescStr);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.back_imag||i==R.id.back_imag_linear) {
            finish();
            PayStateListenerManager.getInstance().onComBuyState(ishaveDingdang,RemainingDaysStr,TariffDescStr);
        }
        if(i==R.id.discountLinear){
            if(isFreeUseOrShop){
                isFreeUseOrShop=false;
                discountLinear.setBackgroundResource(R.drawable.bg_jianbian_select);
                shiyong.setVisibility(View.VISIBLE);
                xuMoney.setVisibility(View.GONE);
            }else{
                isFreeUseOrShop=true;
                discountLinear.setBackgroundResource(R.drawable.bg_center_white);
                xuMoney.setVisibility(View.VISIBLE);
                shiyong.setVisibility(View.GONE);
            }
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
            if(priceInfoList!=null&&priceInfoList.size()>0&&SelectTaoPosition!=-1){

                if(isFreeUseOrShop){//购买
                    int priceNow = (int) (priceInfoList.get(SelectTaoPosition).getPresentPrice()*100);//现价
                    String goodName =priceInfoList.get(SelectTaoPosition).getTariffDesc();//商品描述
                    if(priceNow==0){
                        LoadingDialog.showLoadingDialog(this);//显示进度条
                        ZeroCallNotPay();
                    }else{
                        callPay(priceNow,goodName);
                    }

                }else {//试用
                    LoadingDialog.showLoadingDialog(this);//显示进度条
                    ReqDetailJson reqDetailJson = new ReqDetailJson();
                    reqDetailJson.setTariffDesc(ProbatinTariffDesc);
                    apiManager.getForTrial(appId, appKey, reqDetailJson, new ApiManager.RespCallBack() {
                        @Override
                        public void onResponse(String jsonRespString) {
                            KLog.json("ApigetForTrial",jsonRespString);

                            try{
                                ForTarilRespJson forTarilRespJson =JSON.parseObject(jsonRespString,ForTarilRespJson.class);
                                if(forTarilRespJson.getState().equals("0001")){
                                    //成功申请试用，状态初始化
                                    isFreeUseOrShop=true;
                                    discountLinear.setBackgroundResource(R.drawable.bg_center_white);
                                    xuMoney.setVisibility(View.VISIBLE);
                                    shiyong.setVisibility(View.GONE);
                                    getSetListOrder();//申请试用成功，刷新界面
                                    Toast.makeText(getApplicationContext(),forTarilRespJson.getMsg(),Toast.LENGTH_LONG).show();
                                }else {
                                    Toast.makeText(getApplicationContext(),forTarilRespJson.getMsg(),Toast.LENGTH_LONG).show();
                                }
                                LoadingDialog.hideLoadingDialog();//消失进度条
                            }catch (Exception e){

                            }
                        }
                    }, new ApiManager.RespErrorCallBack() {
                        @Override
                        public void onError(String errorStr) {
                            KLog.json("ApigetForTrial",errorStr);
                            LoadingDialog.hideLoadingDialog();//消失进度条
                            Toast.makeText(getApplicationContext(),"试用期申请失败",Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        }

    }

    private void ZeroCallNotPay(){
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
                    getSetListOrder();//刷新剩余天数等的数据
                    //  finish();
                                                /*  GlobalValueManager.getInstance().setPayTaoProbeValue(false);//购买套餐成功，允许开探针
                                                  Intent intent = new Intent(mContext, ProbeService.class);
                                                  intent.putExtra("openOrCloseProbe", "open");
                                                  startService(intent);*/
                }
                LoadingDialog.hideLoadingDialog();//消失进度条
            }
        }, new ApiManager.RespErrorCallBack() {
            @Override
            public void onError(String errorStr) {
                KLog.d("ApiRecordPay",errorStr);
                Toast.makeText(getApplicationContext(),"购买失败",Toast.LENGTH_LONG).show();
                LoadingDialog.hideLoadingDialog();//消失进度条
            }
        });
    }
    /**
     * callPay支付
     * @param priceNow 支付金额
     * @param goodName 支付订单名称
     */
    private void callPay(int priceNow, String goodName) {
        new RewardPay().pay(getApplicationContext(),callPayAppKey,goodName,priceNow+"", new RewardPay.OnPayResultListener() {
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
                               getSetListOrder();//刷新剩余天数等的数据
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
