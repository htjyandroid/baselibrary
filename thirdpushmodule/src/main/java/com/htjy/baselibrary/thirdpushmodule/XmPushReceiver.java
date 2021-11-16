package com.htjy.baselibrary.thirdpushmodule;

import android.content.Context;

import com.tencent.android.mipush.XMPushMessageReceiver;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;

import cn.jpush.android.service.PluginXiaomiPlatformsReceiver;

/**
 * Created by besttimer on 2021/11/16
 */
public class XmPushReceiver extends XMPushMessageReceiver {

    final PluginXiaomiPlatformsReceiver receiver = new PluginXiaomiPlatformsReceiver();

    @Override
    public void onReceivePassThroughMessage(final Context context, final MiPushMessage message) {
        receiver.onReceivePassThroughMessage(context, message);
    }

    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage message) {
        receiver.onNotificationMessageClicked(context, message);
    }

    @Override
    public void onNotificationMessageArrived(Context context, MiPushMessage message) {
        receiver.onNotificationMessageArrived(context, message);
    }

    @Override
    public void onCommandResult(Context context, MiPushCommandMessage message) {
        receiver.onCommandResult(context, message);
    }

    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage message) {
        receiver.onReceiveRegisterResult(context, message);
    }


}
