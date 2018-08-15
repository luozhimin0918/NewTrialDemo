package com.example.yinlian.tariff.index;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.yinlian.tariff.model.ReqDetailJson;
import com.socks.library.KLog;
import com.ums.upos.sdk.exception.SdkException;
import com.ums.upos.sdk.system.BaseSystemManager;
import com.ums.upos.sdk.system.OnServiceStatusListener;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Luozhimin on 2018-06-22.12:34
 */
public class ApiManager {
    private static ApiManager apiManager;
    private static RequestQueue requestQueue;
    private BaseSystemManager baseSystemManager;
    private Context context;
    //
    public ApiManager(Context context) {
        //创建一个请求队列
        requestQueue = Volley.newRequestQueue(context);
        baseSystemManager = BaseSystemManager.getInstance();
        this.context=context;
        try {
            baseSystemManager.deviceServiceLogin(context, null, "99999999", new OnServiceStatusListener() {
                @Override
                public void onStatus(int i) {


                    KLog.d("onStatus",""+i);
                }
            });

        } catch (SdkException e) {
            e.printStackTrace();
        }
    }

    public static synchronized ApiManager getInstance(Context context) {
        if (apiManager == null) {
            apiManager = new ApiManager(context);
        }
        return apiManager;
    }
    public interface RespCallBack {
        public void onResponse(String jsonRespString);
    }
    public interface RespErrorCallBack {
        public void onError(String errorStr);
    }
    RespCallBack respCallBack;
    RespErrorCallBack respErrorCallBack;
    private String appId;
    private ReqDetailJson reqDetailJson;
    public void getTariffInfo(String appId, ReqDetailJson reqDetailJson, RespCallBack respCallBack, RespErrorCallBack respErrorCallBack){
        this.respCallBack=respCallBack;
        this.respErrorCallBack=respErrorCallBack;
        this.appId=appId;
        this.reqDetailJson=reqDetailJson;
        Message message=new Message();
        message.arg1=0;
        message.obj=UrUtil.findTariffInfoList;
        message.what=0;
        handler.sendMessage(message);

    }
    public void getForTrial(String appId, ReqDetailJson reqDetailJson, RespCallBack respCallBack, RespErrorCallBack respErrorCallBack){
        this.respCallBack=respCallBack;
        this.respErrorCallBack=respErrorCallBack;
        this.appId=appId;
        this.reqDetailJson=reqDetailJson;
        Message message=new Message();
        message.arg1=1;
        message.obj=UrUtil.forTrial;
        message.what=0;
        handler.sendMessage(message);

    }
    public void getRecordPaymentInfo ( String appId, ReqDetailJson reqDetailJson,RespCallBack respCallBack, RespErrorCallBack respErrorCallBack){
        this.respCallBack=respCallBack;
        this.respErrorCallBack=respErrorCallBack;
        this.appId=appId;
        this.reqDetailJson=reqDetailJson;
        Message message=new Message();
        message.arg1=2;
        message.obj=UrUtil.recordPaymentInfo;
        message.what=0;
        handler.sendMessage(message);

    }
    public void getOrderInfo(String appId, ReqDetailJson reqDetailJson, RespCallBack respCallBack, RespErrorCallBack respErrorCallBack){
        this.respCallBack=respCallBack;
        this.respErrorCallBack=respErrorCallBack;
        this.appId=appId;
        this.reqDetailJson=reqDetailJson;
        Message message=new Message();
        message.arg1=3;
        message.obj=UrUtil.getOrderInfo;
        message.what=0;
        handler.sendMessage(message);

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    JSONObject jsonObjectParam=RepObj.netRespParameFast(context,appId,reqDetailJson,msg.arg1,baseSystemManager);
                    String  dd =jsonObjectParam.toString();
                    KLog.json(dd);
                    JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, msg.obj.toString(), jsonObjectParam, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            respCallBack.onResponse(response.toString());
                        }
                    }, new Response.ErrorListener() {


                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            respErrorCallBack.onError(volleyError.toString());
                        }
                    }){
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<>();
                            headers.put("Accept", "application/json");
                            headers.put("Content-Type", "application/json; charset=UTF-8");
                            return headers;
                        }
                    };

                    stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    requestQueue.add(stringRequest);
                    break;
            }
        }
    };

}
