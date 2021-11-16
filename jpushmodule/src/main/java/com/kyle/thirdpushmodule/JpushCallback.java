package com.kyle.thirdpushmodule;

import android.content.Context;

/**
 * 推送消息和通知回调
 * Created by besttimer on 2021/10/29
 */
public interface JpushCallback {

    /**
     * 极光通道和魅族通道的点击通知回调
     */
    void onNotifyMessageOpened(String extras);

    /**
     * 注册回调
     */
    void onRegister(Context context, String registrationId);

}
