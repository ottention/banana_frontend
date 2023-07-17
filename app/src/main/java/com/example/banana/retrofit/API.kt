package com.example.banana.retrofit

import com.example.banana.data.*
import com.example.banana.model.LoginGoogleResponseModel
import com.example.banana.model.LoginGoogle_at_RequestModel
import com.example.banana.model.LoginGoogle_at_ResponseModel
import com.example.banana.model.LoginKaKaoResponseModel
import com.example.banana.model.reIssueResponseModel
import org.apache.commons.lang3.ObjectUtils.Null
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface API {

    // auth
    //qr 코드 가져오기
    @GET("businessCard/1/qrcode/v3")
    fun getQRCode() : Call<ResponseGetQRCode>

    @POST("oauth2/v4/token")
    fun getAccessToken(
        @Body request: LoginGoogle_at_RequestModel
    ):Call<LoginGoogle_at_ResponseModel>


    // kakao login
    @POST("token")
    @FormUrlEncoded
    fun sendkakaoToken(
        @Field("accessToken") accessToken: String
    ):Call<LoginKaKaoResponseModel>


    // google login
    @POST("idToken")
    @FormUrlEncoded
    fun sendGoogleToken(
        @Field("idToken") idToken: String
    ):Call<LoginGoogleResponseModel>

    // request updated accessToken
    @POST("reissue")
    @FormUrlEncoded
    fun reissue(
        @Field("refreshToken") refreshToken: String
    ):Call<reIssueResponseModel>

    // 내가 가진 카드들 id값 받아오기
    @GET("businessCard/home")
    fun getMyCards(
        @Header("Authorization") Authorization: String,
    ): Call<ArrayList<businessCardId>>

    // card 생성
    @POST("businessCard/save")
    fun saveMyCard(
        @Header("Authorization") Authorization: String,
        @Body cardRequestData : cardRequestData

    ): Call<AddCardDataResponseModel>

    // 내 card 조회
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


    // 자신의 카드 삭제
    @DELETE("businessCard/{businessCardId}/delete")
    fun removeMyCard(
        @Header("Authorization") Authorization: String,
        @Path("businessCardId") cardId: Long
    ): Call<Null>

    // card 수정
    @PATCH("businessCard/{businessCardId}/update")
    fun updateMyCard(
        @Header("Authorization") Authorization: String,
        @Path("businessCardId") cardId: Long,
        @Body cardRequestData : cardRequestData

    ): Call<Null>

}