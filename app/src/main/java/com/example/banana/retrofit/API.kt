package com.example.banana.retrofit

import com.example.banana.data.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


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
    @GET("businessCard/2/qrcode")
    fun getQRCode(
        @Header("Authorization") Authorization : String
    ) : Call<ResponseGetQRCode>

    //자신의 명함 방명록 조회
    @GET("banana/businessCard/1/guestBook")
    fun getMyCardComments(
        @Header("Authorization") Authorization : String,
        @Query("page") page : Int,

    ) : Call<ArrayList<comment>>

    // 타인명함에 방명록 쓰기
    @POST("banana/businessCard/{businessCardId}/writeGuestBook")
    fun addComment(
        @Header("Authorization") Authorization : String,
        @Path("businessCardId") businessCardId : Long,
        @Body content : ccomment
        ) : Call<Long>

}