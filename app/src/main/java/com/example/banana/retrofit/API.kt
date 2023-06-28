package com.example.banana.retrofit

import com.example.banana.data.ResponseGetQRCode
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface API {

    //qr 코드 가져오기
    @GET("businessCard/3/qrcode/v3")
    fun getQRCode(
        @Header("Authorization") Authorization : String
    ) : Call<String>

}