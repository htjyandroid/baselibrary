package com.htjy.baselibrary.thirdpushmodule;

import android.content.Context;
import android.content.Intent;

import com.meizu.cloud.pushsdk.handler.MzPushMessage;
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.platform.message.PushSwitchStatus;
import com.meizu.cloud.pushsdk.platform.message.RegisterStatus;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;
import com.meizu.cloud.pushsdk.platform.message.SubTagsStatus;
import com.meizu.cloud.pushsdk.platform.message.UnRegisterStatus;
import com.tencent.android.mzpush.MZPushMessageReceiver;

import cn.jpush.android.service.PluginMeizuPlatformsReceiver;

/**
 * Created by besttimer on 2021/11/17
 */
public class HtMzPushReceiver extends MZPushMessageReceiver {

    final PluginMeizuPlatformsReceiver receiver = new PluginMeizuPlatformsReceiver();

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        receiver.onReceive(context, intent);
    }

    @Override
    public void onRegister(Context context, String s) {
        super.onRegister(context, s);
        receiver.onRegister(context, s);
    }

    @Override
    public void onMessage(Context context, String s) {
        super.onMessage(context, s);
        receiver.onMessage(context, s);
    }

    @Override
    public void onNotificationArrived(Context context, MzPushMessage mzPushMessage) {
        super.onNotificationArrived(context, mzPushMessage);
        receiver.onNotificationArrived(context, mzPushMessage);
    }

    @Override
    public void onNotificationClicked(Context context, MzPushMessage mzPushMessage) {
        super.onNotificationClicked(context, mzPushMessage);
        receiver.onNotificationClicked(context, mzPushMessage);
    }


    @Override
    public void onUnRegister(Context context, boolean b) {
        super.onUnRegister(context, b);
        receiver.onUnRegister(context, b);
    }

    @Override
    public void onPushStatus(Context context, PushSwitchStatus pushSwitchStatus) {
        super.onPushStatus(context, pushSwitchStatus);
        receiver.onPushStatus(context, pushSwitchStatus);
    }

    @Override
    public void onRegisterStatus(Context context, RegisterStatus registerStatus) {
        super.onRegisterStatus(context, registerStatus);
        receiver.onRegisterStatus(context, registerStatus);
    }

    @Override
    public void onUnRegisterStatus(Context context, UnRegisterStatus unRegisterStatus) {
        super.onUnRegisterStatus(context, unRegisterStatus);
        receiver.onUnRegisterStatus(context, unRegisterStatus);
    }

    @Override
    public void onSubTagsStatus(Context context, SubTagsStatus subTagsStatus) {
        super.onSubTagsStatus(context, subTagsStatus);
        receiver.onSubTagsStatus(context, subTagsStatus);
    }

    @Override
    public void onSubAliasStatus(Context context, SubAliasStatus subAliasStatus) {
        super.onSubAliasStatus(context, subAliasStatus);
        receiver.onSubAliasStatus(context, subAliasStatus);
    }

    @Override
    public void onUpdateNotificationBuilder(PushNotificationBuilder pushNotificationBuilder) {
        super.onUpdateNotificationBuilder(pushNotificationBuilder);
        receiver.onUpdateNotificationBuilder(pushNotificationBuilder);
    }

}
