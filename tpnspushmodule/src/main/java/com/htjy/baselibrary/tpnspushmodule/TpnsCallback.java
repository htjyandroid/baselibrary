package com.htjy.baselibrary.tpnspushmodule;

/**
 * 推送消息和通知回调
 * Created by besttimer on 2021/10/29
 */
public interface TpnsCallback {

    /**
     * 点击通知回调
     */
    void onNotifyMessageOpened(String extras);

}
