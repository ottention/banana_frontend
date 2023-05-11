package com.example.banana.auth

import android.provider.Settings.Global.getString
import android.util.Log
import com.example.banana.R
import com.example.banana.model.LoginGoogleRequestModel
import com.example.banana.model.LoginGoogleResponseModel
import com.example.banana.model.SendAccessTokenModel
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class LoginRepository {

    val TAG = "LoginRepository"
    private val getAccessTokenBaseUrl = "https://www.googleapis.com"
    private val sendAccessTokenBaseUrl = "server_base_url" // 받아오기

    fun getAccessToken(authCode:String) {
        LoginService.loginRetrofit(getAccessTokenBaseUrl).getAccessToken(
            request = LoginGoogleRequestModel(
                grant_type = "authorization_code",
                client_id = "418803016352-m76e2uci5o15ode6am21dpqf602uogkq.apps.googleusercontent.com",
                client_secret = "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",
                code = authCode.orEmpty()
            )
        ).enqueue(object : retrofit2.Callback<LoginGoogleResponseModel> {
            override fun onResponse(call: Call<LoginGoogleResponseModel>, response: Response<LoginGoogleResponseModel>) {
                if(response.isSuccessful) {
                    val accessToken = response.body()?.access_token.orEmpty()
                    Log.d(TAG, "accessToken: $accessToken")
//                    sendAccessToken(accessToken)
                }
            }
            override fun onFailure(call: Call<LoginGoogleResponseModel>, t: Throwable) {
                Log.e(TAG, "getOnFailure: ",t.fillInStackTrace() )
            }
        })
    }

    fun sendAccessToken(accessToken:String){
        LoginService.loginRetrofit(sendAccessTokenBaseUrl).sendAccessToken(
            accessToken = SendAccessTokenModel(accessToken)
        ).enqueue(object : retrofit2.Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e(TAG, "sendOnFailure: ${t.fillInStackTrace()}", )
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    Log.d(TAG, "sendOnResponse: ${response.body()}")
                }
            }

        })
    }

}



