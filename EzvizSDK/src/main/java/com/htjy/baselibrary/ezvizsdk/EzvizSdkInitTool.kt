package com.htjy.baselibrary.ezvizsdk

import android.app.Application
import com.videogo.openapi.EZOpenSDK

/**
 * Created by besttimer on 2021/10/27
 */
object EzvizSdkInitTool {

    fun initSdk(application: Application, appKey: String) {
        // APP_KEY请替换成自己申请的
        EZOpenSDK.initLib(application, appKey)
    }

    fun updateAccessToken(accessToken: String) {
        val ezvizSDK: EZOpenSDK = EZOpenSDK.getInstance()
        ezvizSDK.setAccessToken(accessToken)
    }

}