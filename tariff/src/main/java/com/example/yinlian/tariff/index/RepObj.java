package com.example.yinlian.tariff.index;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.yinlian.tariff.model.AppInfoJSon;
import com.example.yinlian.tariff.model.DeviceInfoJSon;
import com.example.yinlian.tariff.model.ReqApiParam;
import com.example.yinlian.tariff.model.ReqDetailJson;
import com.socks.library.KLog;
import com.ums.upos.sdk.exception.CallServiceException;
import com.ums.upos.sdk.exception.SdkException;
import com.ums.upos.sdk.system.BaseSystemManager;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * Created by Luozhimin on 2018-06-22.13:01
 */
public class RepObj {
    public static JSONObject netRespParame(Context context, BaseSystemManager baseSystemManager){
        JSONObject jsonObject =new JSONObject();
        try {
            ReqDetailJson reqDetailJson=new ReqDetailJson();
            //"{\"tariffDescList\":\"默认套餐内容,默认套餐二\",\"tariffDesc\":\"默认套餐内容\"}"
            reqDetailJson.setTariffDescList("默认套餐内容,默认套餐二");
            jsonObject.put("reqDetail", JSON.toJSONString(reqDetailJson));
            jsonObject.put("interType", "BMP-QUERY");
            jsonObject.put("version", "001");
            jsonObject.put("mac","ums2018");
            AppInfoJSon appInfoJSon = new AppInfoJSon();
            appInfoJSon.setAppName("靓丽前台-银商版");
            appInfoJSon.setAppId("afd2baf088034179b4c98826b4d9fcca");
            String appPackname= Utills.getAppProcessName(context);//获取包名
            appInfoJSon.setAppPackName("com.shboka.beautyorderums");
            appInfoJSon.setAppVersionCode("3.0.6.1");
            jsonObject.put("appInfo", JSON.toJSONString(appInfoJSon));
            KLog.json("appInfoJson", JSON.toJSONString(appInfoJSon));

            DeviceInfoJSon deviceInfoJSon = new DeviceInfoJSon();
            deviceInfoJSon.setProdCode("19");//产品型号
            deviceInfoJSon.setFirmCode("109");//厂商代码

            //获取sn
           String deviceInfoMap  = null;
            try {
                deviceInfoMap = baseSystemManager.readSN();
//                deviceInfoMap=baseSystemManager.getDeviceInfo().toString();
            } catch (SdkException e) {
                e.printStackTrace();
            } catch (CallServiceException e) {
                e.printStackTrace();
            }catch (NullPointerException e){

            }
            KLog.d("deviceInfo",deviceInfoMap);
            deviceInfoJSon.setDeviceSn("0820043480");//终端硬件序列号
            jsonObject.put("deviceInfo",JSON.toJSONString(deviceInfoJSon));
            KLog.json("deviceInfo",JSON.toJSONString(deviceInfoJSon));


        } catch (JSONException e) {
            e.printStackTrace();
        }






        return jsonObject;
    }
    public static JSONObject netRespParameFast(Context context,String appId,ReqDetailJson reqDetailJson, int swichCount,BaseSystemManager baseSystemManager){
        JSONObject  retuJSonObje=null;
        com.alibaba.fastjson.JSONObject jsonObject = null;
        ////请求参数
     /*   ReqDetailJson reqDetailJson=new ReqDetailJson();
        switch (swichCount){
            case 0:
                reqDetailJson.setTariffDescList("默认套餐内容,默认套餐二");
                break;
            case 1:
                reqDetailJson.setTariffDesc("默认套餐内容");
                break;
            case 2:
                reqDetailJson.setTariffDesc("默认套餐内容");//套餐描述(文字说明)
                reqDetailJson.setPaymentPrice("1200");//套餐总价
                reqDetailJson.setPurchaseQuantity("20");//购买套餐的数量
                reqDetailJson.setPaymentTerm("30");//购买套餐的总周期
                break;
            case 3:
                reqDetailJson.setTariffDescList("默认套餐内容,默认套餐二");
                break;

        }
*/
       ////应用信息
        ReqApiParam.AppInfoBean appInfoJSon = new ReqApiParam.AppInfoBean();
        appInfoJSon.setAppName("E核销");
        appInfoJSon.setAppId(appId);//"afd2baf088034179b4c98826b4d9fcca"
        String appPackname= Utills.getAppProcessName(context);//获取包名
        String appName=Utills.getAppName(context);//获取应用名
        String versionCode=Utills.getVersionName(context);//获取版本名
        appInfoJSon.setAppPackName("com.ums.ecard");
        appInfoJSon.setAppVersionCode("1.0.15");
       ////设备信息
        ReqApiParam.DeviceInfoBean deviceInfoJSon = new ReqApiParam.DeviceInfoBean();
        deviceInfoJSon.setProdCode("19");//产品型号
        deviceInfoJSon.setFirmCode("LANDI APOS A8");//厂商代码
          //获取sn
        String deviceInfoMap  = null;
        try {
            deviceInfoMap = baseSystemManager.readSN();
              deviceInfoMap=baseSystemManager.getDeviceInfo().toString();
        } catch (SdkException e) {
            e.printStackTrace();
        } catch (CallServiceException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
        }
        deviceInfoJSon.setDeviceSn("50630309");//终端硬件序列号

     /*   ReqApiParam  reqApiParam=new ReqApiParam();
        reqApiParam.setReqDetail(JSON.toJSONString(reqDetailJson));//请求参数
        reqApiParam.setAppInfo(JSON.toJSONString(appInfoJSon));//应用信息
        reqApiParam.setInterType("BMP-QUERY");//接口类型
        reqApiParam.setVersion("001");//接口版本号：默认为“001”
        reqApiParam.setDeviceInfo(JSON.toJSONString(deviceInfoJSon));//设备信息
//        reqApiParam.setMac("ums2018"); //通讯校验参数

*/

        jsonObject=new com.alibaba.fastjson.JSONObject();

            jsonObject.put("reqDetail",JSON.toJSONString(reqDetailJson));
            jsonObject.put("appInfo",JSON.toJSONString(appInfoJSon));
            jsonObject.put("interType","BMP-QUERY");
            jsonObject.put("version","001");
            jsonObject.put("deviceInfo",JSON.toJSONString(deviceInfoJSon));


//            String allStr =  com.alibaba.fastjson.JSONObject.toJSONString(jsonObject, SerializerFeature.PrettyFormat);
            String allStr= jsonObject.toString();
            KLog.d("REp",allStr);
            String textTwo =AesUtil.encrypt(allStr,"d12fa7e992fa4ef3");
            jsonObject.put("mac",textTwo);


        try {
           retuJSonObje= new JSONObject(jsonObject.toJSONString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return retuJSonObje;
    }
}
