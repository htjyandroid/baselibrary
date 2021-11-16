package com.htjy.baselibrary.tpnspushmodule;

import android.text.TextUtils;

/**
 * Created by besttimer on 2021/11/4
 */
public class TpnsHandleUtil {

    private static TpnsCallback tpnsCallback;
    private static final Object getTokenSync = new Object();
    private static IGetTokenCall iGetTokenCall;

    public static void getToken(IGetTokenCall call) {
        synchronized (getTokenSync) {
            if (!TextUtils.isEmpty(token)) {
                call.onGet(token);
            } else {
                TpnsHandleUtil.iGetTokenCall = call;
            }
        }
    }

    /**
     * 通知处理设置
     */
    public static void setTpnsCallback(TpnsCallback jpushCallback) {
        TpnsHandleUtil.tpnsCallback = jpushCallback;
    }

    /**
     * 通知点击处理
     */
    public static void runNotifyMessageOpened(String extras) {
        if (tpnsCallback != null) {
            tpnsCallback.onNotifyMessageOpened(extras);
        }
    }

    private static String token;

    /**
     * 注册回调
     */
    public static void onRegister(String token) {
        synchronized (getTokenSync) {
            TpnsHandleUtil.token = token;
            if (iGetTokenCall != null) {
                iGetTokenCall.onGet(token);
            }
            iGetTokenCall = null;
        }
    }

}
