package com.ums.wifiprobe;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.yinlian.tariff.PayActivity;
import com.example.yinlian.tariff.lisetener.PayStateListenerManager;
import com.example.yinlian.tariff.lisetener.payListener;
import com.example.yinlian.tariff.model.IntentParame;

public class MainActivity extends AppCompatActivity implements payListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(new Intent(getApplicationContext(), PayActivity.class));
            }
        });
        PayStateListenerManager.getInstance().setConnectionStateListener(this);
        Intent  pamaIntent =new Intent(this, PayActivity.class);
        IntentParame intentParame =new IntentParame();
        intentParame.setAppId("6694fb55b3b446809aec8002b9a7a0e8");
        intentParame.setAppKey("ac6d287a30ef498c89ae2bb7fd27889d");
        intentParame.setSetMealDesc("\t•\t云端数据无上限存储云端数据无上限存储云端数<br>" +
                "\t•\t本地订单流水信息实时备份<br>" +
                "\t•\t商品明细小票打印（基础版只能使用30天）<br>" +
                "\t•\t30天以外流水实时查询");
        intentParame.setSetMealName("进销存管家高阶版");
        intentParame.setAdTextTitle("购买数据打印，对账事半功倍");
        pamaIntent.putExtra("payIntent",intentParame);
        startActivity(pamaIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onConnecting() {
        Log.d("Connnnn","onConnecting");
    }

    @Override
    public void onConnected() {
        Log.d("Connnnn","onConnected");

    }

    @Override
    public void onDisConnected() {
        Log.d("Connnnn","onDisConnected");

    }
}
