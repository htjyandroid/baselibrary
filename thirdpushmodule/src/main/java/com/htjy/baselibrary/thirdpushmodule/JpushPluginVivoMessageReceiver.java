package com.htjy.baselibrary.thirdpushmodule;

import android.content.Context;

import com.vivo.push.model.UPSNotificationMessage;
import com.vivo.push.sdk.OpenClientPushMessageReceiver;

import cn.jpush.android.helper.Logger;
import cn.jpush.android.thirdpush.vivo.a;

/**
 * Created by besttimer on 2021/11/18
 */
public class JpushPluginVivoMessageReceiver extends OpenClientPushMessageReceiver {

    @Override
    public void onNotificationMessageClicked(Context context, UPSNotificationMessage upsNotificationMessage) {

    }

    @Override
    public void onReceiveRegId(Context var1, String var2) {
        Logger.dd("PushMessageReceiver", "Vivo regId:" + var2);

        try {
            a.a(var2, var1);
        } catch (Throwable var4) {
            Logger.ww("PushMessageReceiver", "onReceiveRegId error#", var4);
        }

    }

}
