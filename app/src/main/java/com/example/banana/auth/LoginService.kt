package com.example.banana.auth

import com.example.banana.model.LoginGoogleResponseModel
import com.example.banana.model.LoginGoogle_at_RequestModel
import com.example.banana.model.LoginGoogle_at_ResponseModel
import com.example.banana.model.LoginKaKaoResponseModel
import com.example.banana.model.reIssueResponseModel
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {

    @POST("oauth2/v4/token")
    fun getAccessToken(
        @Body request: LoginGoogle_at_RequestModel
    ):Call<LoginGoogle_at_ResponseModel>

    @POST("token")
    @FormUrlEncoded
    fun sendkakaoToken(
        @Field("accessToken") accessToken: String
    ):Call<LoginKaKaoResponseModel>


    @POST("idToken")
    @FormUrlEncoded
    fun sendGoogleToken(
        @Field("idToken") idToken: String
    ):Call<LoginGoogleResponseModel>

    @POST("reissue")
    @FormUrlEncoded
    fun reissue(
        @Field("userId") userId: String
    ):Call<reIssueResponseModel>

    companion object {

        private val gson = GsonBuilder().setLenient().create()

        fun loginRetrofit(baseUrl: String): LoginService {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(LoginService::class.java)
        }

    }
}
