package com.kyle.thirdpushmodule;

import android.content.Context;

/**
 * Created by besttimer on 2021/11/4
 */
public class NotifyHandleUtil {

    private static INotifyHandle iNotifyHandle;

    /**
     * 通知处理设置
     */
    public static void setiNotifyHandle(INotifyHandle iNotifyHandle) {
        NotifyHandleUtil.iNotifyHandle = iNotifyHandle;
    }

    /**
     * 通知到达处理(一般不包括厂商推送)
     */
    public static void runNotifyArrived(Context context, HtCustomMessage htCustomMessage) {
        if (iNotifyHandle != null) {
            iNotifyHandle.onMessage(context, htCustomMessage);
        }
    }

    /**
     * 通知点击处理
     */
    public static void runNotifyMessageOpened(String extras) {
        if (iNotifyHandle != null) {
            iNotifyHandle.onNotifyMessageOpened(extras);
        }
    }

    /**
     * 注册回调
     */
    public static void onRegister(Context context, String registrationId) {
        if (iNotifyHandle != null) {
            iNotifyHandle.onRegister(context, registrationId);
        }
    }

}
