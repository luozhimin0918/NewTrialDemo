package com.ums.asus.newtrialdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.yinlian.tariff.index.ApiManager;
import com.example.yinlian.tariff.model.ReqDetailJson;

public class MainActivity extends AppCompatActivity {
    ApiManager apiManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiManager = ApiManager.getInstance(getApplicationContext());
        ReqDetailJson reqDetailJson = new ReqDetailJson();
        reqDetailJson.setTariffDescList("");
        apiManager.getTariffInfo("4637b348589c493fbac91b6b5f0029f1","d12fa7e992fa4ef3", reqDetailJson, new ApiManager.RespCallBack() {
            @Override
            public void onResponse(String s) {
                Log.d("onResponse",s.toString());
            }
        }, new ApiManager.RespErrorCallBack() {
            @Override
            public void onError(String s) {
                Log.d("onResponse",s.toString());
            }
        });
    }
}
