package com.example.banana.retrofit

import com.example.banana.data.getCardResponseModel
import com.example.banana.data.saveCardDataResponseModel
import com.example.banana.data.saveCardRequestModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import com.example.banana.data.ResponseGetQRCode
import com.example.banana.data.ResponsegetMyCardComments


interface API {

        @POST("businessCard/save")
    fun saveMyCard(
        @Header("Authorization") Authorization: String,
        @Body saveCardRequestModel : saveCardRequestModel

    ): Call<saveCardDataResponseModel>

    @GET("businessCard/{businessCardId}")
    fun getCard(
        @Header("Authorization") Authorization: String,
        @Path("businessCardId") cardId: Long
    ): Call<getCardResponseModel>

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