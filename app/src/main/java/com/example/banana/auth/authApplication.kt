package com.example.banana.auth

import android.app.Application
import android.content.Context
import com.example.banana.R
import com.example.banana.model.PreferenceUtil
import com.kakao.sdk.common.KakaoSdk

class authApplication : Application() {

    companion object {
        var appContext : Context? = null
        lateinit var prefs : PreferenceUtil
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        prefs = PreferenceUtil(applicationContext)
        // kakao auth 초기화
        KakaoSdk.init(this,getString(R.string.kakao_app_key))
    }

}