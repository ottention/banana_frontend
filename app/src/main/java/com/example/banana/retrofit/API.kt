package com.example.banana.retrofit

import com.example.banana.data.getCardResponseModel
import com.example.banana.data.saveCardDataResponseModel
import com.example.banana.data.saveCardRequestModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface API {

        @POST("businessCard/save/v2")
    fun saveMyCard(
        @Header("Authorization") Authorization: String,
        @Body saveCardRequestModel : saveCardRequestModel

    ): Call<saveCardDataResponseModel>

    @GET("businessCard/{businessCardId}")
    fun getCard(
        @Header("Authorization") Authorization: String,
        @Path("businessCardId") cardId: Long
    ): Call<getCardResponseModel>

}