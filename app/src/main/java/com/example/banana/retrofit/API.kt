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
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query


interface API {

    // auth
    //qr 코드 가져오기
    @GET("businessCard/1/qrcode/v3")
    fun getQRCode(): Call<ResponseGetQRCode>

    @POST("oauth2/v4/token")
    fun getAccessToken(
        @Body request: LoginGoogle_at_RequestModel
    ): Call<LoginGoogle_at_ResponseModel>

    // kakao login
    @POST("token")
    @FormUrlEncoded
    fun sendkakaoToken(
        @Field("accessToken") accessToken: String
    ): Call<LoginKaKaoResponseModel>

    // google login
    @POST("idToken")
    @FormUrlEncoded
    fun sendGoogleToken(
        @Field("idToken") idToken: String
    ): Call<LoginGoogleResponseModel>

    // request updated accessToken
    @POST("reissue")
    @FormUrlEncoded
    fun reissue(
        @Field("refreshToken") refreshToken: String
    ): Call<reIssueResponseModel>

    // card 생성
    @POST("businessCard/save")
    fun saveMyCard(
        @Header("Authorization") Authorization: String,
        @Body cardRequestData: cardRequestData

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
        @Header("Authorization") Authorization: String
    ): Call<ResponseGetQRCode>

    //자신의 명함 방명록 조회
    @GET("banana/businessCard/{cardId}/guestBook")
    fun getMyCardComments(
        @Header("Authorization") Authorization: String,
        @Path("cardId") cardId: Long,
        @Query("page") page: Int,

        ): Call<ArrayList<comment>>

    // 특정 타인 명함에 작성한 방명록 가져오기
    @GET("banana/businessCard/{guestBookId}/otherGuestBooks")
    fun getComment(
        @Header("Authorization") Authorization: String,
        @Path("guestBookId") guestBookId: Long,
    ): Call<ArrayList<comment>>

    // 타인명함에 방명록 쓰기
    @POST("banana/businessCard/{businessCardId}/writeGuestBook")
    fun addComment(
        @Header("Authorization") Authorization: String,
        @Path("businessCardId") businessCardId: Long,
        @Body content: ccomment
    ): Call<Long>

    // 타인명함에 작성한 방명록 지우기
    @POST("businessCard/myGuestBook/{guestBookId}/delete")
    fun removeComment(
        @Header("Authorization") Authorization: String,
        @Path("guestBookId") commentId: Long,
    ): Call<Null>

    // 타인명함에 작성한 방명록 수정하기
    @POST("businessCard/myGuestBook/{guestBookId}/edit")
    fun updateComment(
        @Header("Authorization") Authorization: String,
        @Path("guestBookId") commentId: Long,
        @Body content: ccomment
    ): Call<Null>

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
        @Body cardRequestData: cardRequestData

    ): Call<Null>

    //내가 작성한 방명록 조회
    @GET("banana/myWrittenGuestBooks")
    fun getCommentsIwrote(
        @Header("Authorization") Authorization: String,
        @Query("page") page: Int
    ): Call<ArrayList<comment>>

    //홈 화면 cardId 조회
    @GET("businessCard/home")
    fun getBusinessCardId(
        @Header("Authorization") Authorization: String,
    ): Call<ArrayList<businessCardIdData>>

    // 좋아요
    @POST("businessCard/{businessCardId}/like")
    fun like(
        @Header("Authorization") Authorization: String,
        @Path("businessCardId") cardId: Long
    ): Call<Responselike>


    // 좋아요 취소
    @DELETE("businessCard/{businessCardId}/cancelLike")
    fun unlike(
        @Header("Authorization") Authorization: String,
        @Path("businessCardId") cardId: Long
    ): Call<Responselike>

    // 작성한 노트 조회
    @GET("banana/wallet/{id}/notes")
    fun getNote(
        @Header("Authorization") Authorization: String,
        @Path("id") cardId: Long,
        ): Call<ArrayList<ResponseNote>>

    // 저장한 명함에 노트 추가
    @POST("banana/wallet/{id}/notes")
    fun addNote(
        @Header("Authorization") Authorization: String,
        @Path("id") cardId: Long,
        @Body note: note
    ): Call<Long>

    // 노트 수정
    @PUT("banana/wallet/{id}/notes/{noteId}")
    fun updateNote(
        @Header("Authorization") Authorization: String,
        @Path("id") id: Long,
        @Path("noteId") noteId: Long,
        @Body note: note
    ): Call<Null>

    // 노트 삭제
    @DELETE("banana/wallet/{id}/notes/{noteId}")
    fun deleteNote(
        @Header("Authorization") Authorization: String,
        @Path("id") id: Long,
        @Path("noteId") noteId: Long,

        ): Call<Null>

}