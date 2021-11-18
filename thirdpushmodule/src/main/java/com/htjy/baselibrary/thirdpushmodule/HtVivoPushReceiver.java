package com.htjy.baselibrary.thirdpushmodule;

import android.content.Context;

import com.tencent.android.vivopush.VivoPushMessageReceiver;

/**
 * Created by besttimer on 2021/11/18
 */
public class HtVivoPushReceiver extends VivoPushMessageReceiver {

    final JpushPluginVivoMessageReceiver jpushPluginVivoMessageReceiver = new JpushPluginVivoMessageReceiver();

    @Override
    public void onReceiveRegId(Context context, String s) {
        super.onReceiveRegId(context, s);
        jpushPluginVivoMessageReceiver.onReceiveRegId(context, s);
    }
}
