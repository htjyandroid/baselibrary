package com.kyle.thirdpushmodule;


import android.os.Bundle;
import android.util.Log;

import cn.jpush.android.service.JCommonService;

public class PushService extends JCommonService {

    public void onCreate(Bundle savedInstanceState) {

        Log.e("jiguang", "**** PushService onCreate ******" );
    }
}
