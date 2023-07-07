package com.example.banana.retrofit

import android.widget.ImageView
import com.example.banana.data.ResponseGetQRCode
import com.example.banana.data.ResponsegetMyCardComments
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface API {

    //qr 코드 가져오기
    @GET("businessCard/1/qrcode/v4")
    fun getQRCode(
        @Header("Authorization") Authorization : String
    ) : Call<ResponseGetQRCode>

    //자신의 명함 방명록 조회
    @GET("banana/businessCard/8/guestBook?page=0&sort=id")
    fun getMyCardComments(
        @Header("Authorization") Authorization : String
    ) : Call<ResponsegetMyCardComments>

}