package com.kyle.thirdpushmodule;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

/**
 * Created by besttimer on 2021/11/4
 */
public class JpushHandleUtil {

    private static JpushCallback jpushCallback;
    private static final Object getRegistrationIDSync = new Object();
    private static IGetRegistrationIdCall iGetRegistrationIdCall;

    public static void getRegistrationID(Application application, IGetRegistrationIdCall call) {
        synchronized (getRegistrationIDSync) {
            String registrationId = JPushHelper.getRegistrationID(application);
            if (!TextUtils.isEmpty(registrationId)) {
                call.onGet(registrationId);
            } else {
                JpushHandleUtil.iGetRegistrationIdCall = call;
            }
        }
    }

    /**
     * 通知处理设置
     */
    public static void setiNotifyHandle(JpushCallback jpushCallback) {
        JpushHandleUtil.jpushCallback = jpushCallback;
    }

    /**
     * 通知点击处理
     */
    public static void runNotifyMessageOpened(String extras) {
        if (jpushCallback != null) {
            jpushCallback.onNotifyMessageOpened(extras);
        }
    }

    /**
     * 注册回调
     */
    public static void onRegister(Context context, String registrationId) {
        if (jpushCallback != null) {
            jpushCallback.onRegister(context, registrationId);
        }
        synchronized (getRegistrationIDSync) {
            if (iGetRegistrationIdCall != null) {
                iGetRegistrationIdCall.onGet(registrationId);
            }
        }
    }

}
