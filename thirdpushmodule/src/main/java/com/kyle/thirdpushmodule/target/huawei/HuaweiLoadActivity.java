package com.kyle.thirdpushmodule.target.huawei;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.kyle.thirdpushmodule.NotifyHandleUtil;
import com.kyle.thirdpushmodule.R;

import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

/**
 * 厂商通道需要通过点击跳转的页面才能获取到消息内容
 * 华为/小米/VIVO 通过 uri_activity 跳转
 * OPPO/FCM 通过 uri_action 跳转
 */
public class HuaweiLoadActivity extends Activity {
    private static final String TAG = "OpenClickActivity";
    /**
     * 消息Id
     **/
    private static final String KEY_MSGID = "msg_id";
    /**
     * 该通知的下发通道
     **/
    private static final String KEY_WHICH_PUSH_SDK = "rom_type";
    /**
     * 通知标题
     **/
    private static final String KEY_TITLE = "n_title";
    /**
     * 通知内容
     **/
    private static final String KEY_CONTENT = "n_content";
    /**
     * 通知附加字段
     **/
    private static final String KEY_EXTRAS = "n_extras";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jpush_activity_open);
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
        String data = null;
        String extras = null;

        //获取华为平台附带的jpush信息（extra）
        if (getIntent().getData() != null) {
            data = getIntent().getData().toString();
        }

        //获取fcm/oppo/小米/vivo 平台附带的jpush信息（extra）
        if (TextUtils.isEmpty(data) && getIntent().getExtras() != null) {
            data = getIntent().getExtras().getString("JMessageExtra");
        }

        if (!TextUtils.isEmpty(data)) {
            try {
                JSONObject jsonObject = new JSONObject(data);
                String msgId = jsonObject.optString(KEY_MSGID);
                byte whichPushSDK = (byte) jsonObject.optInt(KEY_WHICH_PUSH_SDK);
                extras = jsonObject.optString(KEY_EXTRAS);

                //上报点击事件，厂商通道的点击数据需要用户调用该方法上报
                JPushInterface.reportNotificationOpened(this, msgId, whichPushSDK);
            } catch (JSONException e) {
                Log.w(TAG, "parse notification error");
            }
        } else {
            //获取单纯通过极光通道过来的信息
            if (getIntent().getExtras() != null) {
                extras = getIntent().getExtras().getString(JPushInterface.EXTRA_ALERT);
            }
        }
        if (!TextUtils.isEmpty(extras)) {
            NotifyHandleUtil.runNotifyMessageOpened(extras);
        }

        Log.i("jiguang", "msg content is: " + data);
        Log.i("jiguang", "extras content is: " + extras);

        finish();
    }

    private String getPushSDKName(byte whichPushSDK) {
        String name;
        switch (whichPushSDK) {
            case 0:
                name = "jpush";
                break;
            case 1:
                name = "xiaomi";
                break;
            case 2:
                name = "huawei";
                break;
            case 3:
                name = "meizu";
                break;
            case 4:
                name = "oppo";
                break;
            case 5:
                name = "vivo";
                break;
            case 8:
                name = "fcm";
                break;
            default:
                name = "jpush";
        }
        return name;
    }
}
