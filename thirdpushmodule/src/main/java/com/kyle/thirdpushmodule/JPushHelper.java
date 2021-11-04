package com.kyle.thirdpushmodule;

import android.app.Application;
import android.content.Context;

import cn.jpush.android.api.JPushInterface;

/**
 * 极光推送工具
 * Created by besttimer on 2021/11/4
 */
public class JPushHelper {

    /**
     * 初始化jpush
     *
     * @param application application
     * @param debugMode   是否debug模式，正式版本请关闭
     */
    public static void init(Application application, boolean debugMode) {
        JPushInterface.setDebugMode(debugMode); // 设置开启日志,发布时请关闭日志
        JPushInterface.init(application); // 初始化 JPush
    }

    /**
     * 获取RegistrationID
     *
     * @param application application
     * @return 返回RegistrationID
     */
    public static String getRegistrationID(Application application) {
        return JPushInterface.getRegistrationID(application);
    }

    /**
     * 清空通知
     */
    public static void clearAllNotifications(Context context) {
        JPushInterface.clearAllNotifications(context);
    }

    /**
     * 通知处理设置
     */
    public static void setiNotifyHandle(INotifyHandle iNotifyHandle) {
        NotifyHandleUtil.setiNotifyHandle(iNotifyHandle);
    }

    /**
     * 通知到达处理(一般不包括厂商推送)
     */
    public static void runNotifyArrived(Context context, HtCustomMessage htCustomMessage) {
        NotifyHandleUtil.runNotifyArrived(context, htCustomMessage);
    }

    /**
     * 通知点击处理
     */
    public static void runNotifyMessageOpened(String extras) {
        NotifyHandleUtil.runNotifyMessageOpened(extras);
    }

}
