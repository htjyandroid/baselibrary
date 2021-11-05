package com.kyle.thirdpushmodule;

import android.content.Context;

/**
 * 推送消息和通知回调
 * Created by besttimer on 2021/10/29
 */
public interface INotifyHandle {

    /**
     * 极光通道和魅族通道的点击通知回调
     */
    void onNotifyMessageOpened(String extras);

    /**
     * 自定义消息回调；通知的回调和自定义消息的回调不同，通知的回调在onNotifyMessageArrived
     */
    void onMessage(Context context, HtCustomMessage htCustomMessage);

    /**
     * 注册回调
     */
    void onRegister(Context context, String registrationId);

}
