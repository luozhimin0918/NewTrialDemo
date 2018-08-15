package com.ums.asus.umspaydemo.utils;

import android.content.Context;
import android.nfc.Tag;
import android.os.Looper;
import android.util.Log;

import com.socks.library.KLog;
import com.ums.tms.app.interact.AppInteractHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import android.os.Handler;
import android.widget.Toast;


/**
 * Created by Luozhimin on 2018/8/3.9:31
 */
public class CallPayUtill {

    private static final String TAG = "CallPayUtillLog";//调用者权限值

    private static  String mAppKey = "d12fa7e992fa4ef383bc49f2040fc40e";//调用者权限值
    private static  String mGoodsName = "商品名称";
    private static  String mAmount = "";//支付总金额，单位：分
    private static  String mPackName="com.ums.ecard";

    //--服务费（分润，第三方应用可用）
    public   void CallPaytestC(Context context,int mAmount) {
        this.mAmount=mAmount*100+"";//单位为分，整数的字符串格式
        JSONObject paramData = new JSONObject();
        try {
            paramData.put("dataType", "203");
        } catch (Exception e) {
            // TODO: handle exception
        }
        test(context, getQrParamData("213"), paramData);
    }

    public void test(final Context context, final JSONObject qrParamData, final JSONObject creditcardParamData) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String curtime = sdf.format(date);
        try {
            creditcardParamData.put("appKey", mAppKey);						//调用者权限值
            creditcardParamData.put("packName",mPackName);	//调用者包名
            creditcardParamData.put("goodsName", mGoodsName);					//商品名称
            creditcardParamData.put("amount", mAmount);						//支付总金额，单位：分
            creditcardParamData.put("curtime", curtime);					//时间戳
        } catch (Exception e) {
            // TODO: handle exception
        }
        AppInteractHelper.callPay(context, new AppInteractHelper.PayListener() {
            @Override
            public String getPayParamData(int payType) {
                // TODO Auto-generated method stub
                KLog.d(TAG, "-getPayParamData-payType:"+payType);

                if (payType == AppInteractHelper.PayListener.PAY_TYPE_CREDITCARD) {
                    KLog.d(creditcardParamData.toString());
                    return creditcardParamData.toString();
                } else if (payType == AppInteractHelper.PayListener.PAY_TYPE_QR) {
                    KLog.d(qrParamData.toString());
                    return qrParamData.toString();
                }

                return null;
            }
            @Override
            public String onBillGenerated(int payType, String result) {
                // TODO Auto-generated method stub
                Log.i(TAG, "-onBillGenerated-payType:"+payType+", result:"+result);
                resolveBillInfo(result);
                // 如果是内购（非分润）则须返回如下信息，否则返回null即可
                //if (payType == PayListener.PAY_TYPE_QR) {
                //	return installBillQueryInfo(result);
                //}
                return null;
            }
            @Override
            public void onPayReturned(int payType, String result) {
                // TODO Auto-generated method stub
                KLog.i(TAG, "-onPayReturned-payType:"+payType+", result:"+result);
                onPayResult(context, result);
            }
        });
    }
    public void onPayResult(final Context context, String result) {
        try {
            JSONObject resultJson = new JSONObject(result);
            String resultCode = getJSONString(resultJson, "state");
            String resultMsg = getJSONString(resultJson, "msg");
            String payResult = getJSONString(resultJson, "payResult");//支付结果，"00"支付失败，"01"支付成功，"02"支付渠道尚未同步支付结果（或未支付）
           KLog.json(TAG,result);
            if (!"01".equals(resultCode)) { // 接口调用失败
               /* Looper.prepare();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        Toast.makeText(context,"接口调用失败",Toast.LENGTH_LONG);

                    }
                }, 0);
                Looper.loop();*/
                KLog.d(TAG,"接口调用失败");
                return;
            }
            if ("01".equals(payResult)) {//支付成功
//                Toast.makeText(context,"支付成功",Toast.LENGTH_LONG);
                KLog.d(TAG,"支付成功");

            } else if ("02".equals(payResult)) {//支付渠道尚未同步支付结果（或未支付）
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        queryPayResult(context);
                    }
                }, 1000);
            } else {// unpaid
                //重新去支付
//                Toast.makeText(context,"重新去支付",Toast.LENGTH_LONG);
                KLog.d(TAG,"重新去支付");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //--支付结果查询（均可用）
    public void queryPayResult(final Context context) {
        JSONObject paramData = new JSONObject();
        try {
            paramData.put("dataType", "200");
            paramData.put("packName", context.getPackageName());
            paramData.put("appKey", mAppKey);
            paramData.put("payDataType", mBillInfo.getPayDataType());
            paramData.put("billNo", mBillInfo.getBillNo());
            paramData.put("billDate", mBillInfo.getBillDate());
//			paramData.put("responseFast", "00");//是否立即返回, 00为是（默认），01为否（极限超时时间，直到账单/悦单系统有结果返回）
            //内购时必须组装以下参数
//			paramData.put("billQueryInfo", installBillQueryInfo(paramData.toString()));

            //以下为可选参数
            paramData.put("showView", "01");//是否显示界面,00为否（默认），01为是
            paramData.put("goodsName", mGoodsName);//商品名称, 当showView为01时必需
            paramData.put("amount", mAmount);//总金额,单位：分, 当showView为01时必需
        } catch (Exception e) {
            // TODO: handle exception
        }
        AppInteractHelper.queryPayResult(context, paramData.toString(), new AppInteractHelper.PayResultListener() {
            @Override
            public void onPayResultReturned(String result) {
                // TODO Auto-generated method stub
                Log.i(TAG, "-onPayResultReturned-result:"+result);
                onPayResult(context, result);
            }
        });
    }
    public void resolveBillInfo(String result) {
        if (result != null) {
            try {
                JSONObject resultData = new JSONObject(result);
                mBillInfo.setPayDataType(getJSONString(resultData, "payDataType"));
                mBillInfo.setOrderNo(getJSONString(resultData, "orderNo"));
                mBillInfo.setBillNo(getJSONString(resultData, "billNo"));
                mBillInfo.setBillDate(getJSONString(resultData, "billDate"));
                mBillInfo.setRespon(getJSONString(resultData, "respon"));//内购时才有此数据
                KLog.json(TAG,result);
            } catch (Exception e) {
               KLog.e(TAG, "-Exception:"+e.toString());
            }
        }
    }


    private static String getJSONString(JSONObject jObj, String key) throws JSONException {
        if (jObj != null && jObj.has(key)) {
            return jObj.getString(key);
        }
        return null;
    }

    public JSONObject getQrParamData(String qrDataType) {
        try {
            JSONObject qrParamData = new JSONObject();
            qrParamData.put("dataType", qrDataType);
            qrParamData.put("billInfo", installBillInfo());
            return qrParamData;
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject installBillInfo() {
        try {
            JSONObject billInfo = new JSONObject();
            billInfo.put("totalAmount", mAmount);         	 //支付总金额，单位：分
            JSONObject good = new JSONObject();
            good.put("quantity",  "1");			 //商品数量
            good.put("goodsId",  "0001");			 //商品ID
            good.put("price",  mAmount);                //商品单价，单位：分
            good.put("goodsCategory",  "0001");      //商品分类
            good.put("body",  "商品说明");            //商品说明
            good.put("goodsName",  mGoodsName);       //商品名称
            JSONArray goods = new JSONArray();
            goods.put(good);
            billInfo.put("goods", goods);
            billInfo.put("billDesc", "账单描述:二维码支付测试"); //账单描述
            return billInfo;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    public BillInfo mBillInfo = new BillInfo();
    public class BillInfo {
        private String payDataType = null;	//支付请求类型
        private String orderNo = null;		//订单号
        private String billNo = null;		//账单号
        private String billDate = null;		//账单日期
        private String respon = null;		//内购数据
        public String getPayDataType() {
            return payDataType;
        }
        public void setPayDataType(String payDataType) {
            this.payDataType = payDataType;
        }
        public String getOrderNo() {
            return orderNo;
        }
        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }
        public String getBillNo() {
            return billNo;
        }
        public void setBillNo(String billNo) {
            this.billNo = billNo;
        }
        public String getBillDate() {
            return billDate;
        }
        public void setBillDate(String billDate) {
            this.billDate = billDate;
        }
        public String getRespon() {
            return respon;
        }
        public void setRespon(String respon) {
            this.respon = respon;
        }
    }

}
