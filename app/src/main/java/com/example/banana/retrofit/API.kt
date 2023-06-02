package com.example.banana.retrofit

import com.example.banana.data.ResponseGetQRCode
import retrofit2.Call
import retrofit2.http.GET

interface API {

    //qr 코드 가져오기
    @GET("businessCard/1/qrcode/v3")
    fun getQRCode() : Call<ResponseGetQRCode>

}