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
    @GET("banana/businessCard/{cardId}/guestBook")
    fun getMyCardComments(
        @Header("Authorization") Authorization : String,
        @Path("cardId") cardId: Long,
        @Query("page") page : Int,

    ) : Call<ArrayList<comment>>

    //내가 작성한 방명록 조회
    @GET("banana/myWrittenGuestBooks")
    fun getCommentsIwrote(
        @Header("Authorization") Authorization : String,
        @Query("page") page : Int
    ) : Call<ArrayList<comment>>

    //홈 화면 cardId 조회
    @GET("businessCard/home")
    fun getBusinessCardId(
        @Header("Authorization") Authorization : String,
    ) : Call<ArrayList<businessCardIdData>>

}