package com.htjy.baselibrary.tpnspushmodule;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;

/**
 * Created by besttimer on 2021/11/11
 */
public class TpnsHelper {

    public static void init(Application application, boolean isDebug) {
        XGPushConfig.enableDebug(application, isDebug);
        resumePush(application);
    }

    public static void resumePush(Application application) {
        try {
            ApplicationInfo appInfo = application.getPackageManager().getApplicationInfo(application.getPackageName(), PackageManager.GET_META_DATA);
            String XIAOMI_APPKEY = appInfo.metaData.getString("XIAOMI_APPKEY");
            String XIAOMI_APPID = appInfo.metaData.getString("XIAOMI_APPID");
            String prefixStr = "MI-";
            if (XIAOMI_APPID.startsWith(prefixStr)) {
                XIAOMI_APPID = XIAOMI_APPID.substring(prefixStr.length());
            }
            if (XIAOMI_APPKEY.startsWith(prefixStr)) {
                XIAOMI_APPKEY = XIAOMI_APPKEY.substring(prefixStr.length());
            }
            XGPushConfig.setMiPushAppId(application, XIAOMI_APPID);
            XGPushConfig.setMiPushAppKey(application, XIAOMI_APPKEY);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        XGPushConfig.enableOtherPush(application, true);
        XGPushManager.registerPush(application, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
                //token在设备卸载重装的时候有可能会变
                Log.d("TPush", "注册成功，设备token为：" + data);
                if (data != null) {
                    TpnsHandleUtil.onRegister(data.toString());
                }
            }

            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
            }
        });
    }

    public static void stopPush(Application application) {
        XGPushConfig.enableOtherPush(application, false);
        XGPushManager.unregisterPush(application);
    }

    public static void getToken(IGetTokenCall call) {
        TpnsHandleUtil.getToken(call);
    }

    /**
     * 通知处理设置
     */
    public static void setiNotifyHandle(TpnsCallback jpushCallback) {
        TpnsHandleUtil.setTpnsCallback(jpushCallback);
    }

}
