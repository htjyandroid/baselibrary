package com.htjy.baselibrary.tpnspushmodule;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;

/**
 * tpns启动页
 */
public class TpnsLoadActivity extends Activity {
    private static final String TAG = "TpnsLoadActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tpns_activity_open);
        //获取厂商通道的消息
        handleOpenClick();
    }

    /**
     * 处理点击事件，当前启动配置的Activity都是使用
     * Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
     * 方式启动，只需要在onCreat中调用此方法进行处理
     */
    private void handleOpenClick() {
        Log.d(TAG, "用户点击打开了通知");

        Uri uri = getIntent().getData();
        if (uri != null) {
            Set<String> names = uri.getQueryParameterNames();
            JSONObject jsonObject = new JSONObject();
            for (String name : names) {
                try {
                    jsonObject.put(name, uri.getQueryParameter(name));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            String extras = jsonObject.toString();
            TpnsHandleUtil.runNotifyMessageOpened(extras);
            Log.d(TAG, "通知内容:" + extras);
        }

        finish();
    }

}
