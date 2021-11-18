package com.htjy.baselibrary.tpnspushmodule;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.tpns.baseapi.base.util.ChannelUtils;

/**
 * Created by besttimer on 2021/11/11
 */
public class TpnsHelper {

    public static void init(Application application, boolean isDebug) {
        XGPushConfig.enableDebug(application, isDebug);
        resumePush(application);
    }

    /**
     * 配置小米通道
     */
    private static void configXM(Application application, ApplicationInfo appInfo) {
        String appKey = appInfo.metaData.getString("XIAOMI_APPKEY");
        String appId = appInfo.metaData.getString("XIAOMI_APPID");
        String prefixStr = "MI-";
        if (appId.startsWith(prefixStr)) {
            appId = appId.substring(prefixStr.length());
        }
        if (appKey.startsWith(prefixStr)) {
            appKey = appKey.substring(prefixStr.length());
        }
        XGPushConfig.setMiPushAppId(application, appId);
        XGPushConfig.setMiPushAppKey(application, appKey);
    }

    /**
     * 配置魅族通道
     */
    private static void configMZ(Application application, ApplicationInfo appInfo) {
        String appKey = appInfo.metaData.getString("MEIZU_APPKEY");
        String appid = appInfo.metaData.getString("MEIZU_APPID");
        String prefixStr = "MZ-";
        if (appid.startsWith(prefixStr)) {
            appid = appid.substring(prefixStr.length());
        }
        if (appKey.startsWith(prefixStr)) {
            appKey = appKey.substring(prefixStr.length());
        }
        XGPushConfig.setMzPushAppId(application, appid);
        XGPushConfig.setMzPushAppKey(application, appKey);
    }

    /**
     * 配置OPPO通道
     */
    private static void configOPPO(Application application, ApplicationInfo appInfo) {
        String appKey = appInfo.metaData.getString("OPPO_APPSECRET");
        String appId = appInfo.metaData.getString("OPPO_APPKEY");
        String prefixStr = "OP-";
        if (appId.startsWith(prefixStr)) {
            appId = appId.substring(prefixStr.length());
        }
        if (appKey.startsWith(prefixStr)) {
            appKey = appKey.substring(prefixStr.length());
        }
        XGPushConfig.setOppoPushAppId(application, appId);
        XGPushConfig.setOppoPushAppKey(application, appKey);
    }

    public static void resumePush(Application application) {
        try {
            ApplicationInfo appInfo = application.getPackageManager().getApplicationInfo(application.getPackageName(), PackageManager.GET_META_DATA);
            configXM(application, appInfo);
            configMZ(application, appInfo);
            configOPPO(application, appInfo);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        XGPushConfig.enableOtherPush(application, !ChannelUtils.isBrandHuaWei() && !ChannelUtils.isBrandHonor());
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
