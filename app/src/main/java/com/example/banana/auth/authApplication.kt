package com.example.banana.auth

import android.app.Application
import android.content.Context
import com.example.banana.R
import com.kakao.sdk.common.KakaoSdk

class authApplication : Application() {

    companion object {
        var appContext : Context? = null
    }
    override fun onCreate() {
        super.onCreate()
        appContext = this
        KakaoSdk.init(this,getString(R.string.kakao_app_key))
    }
}