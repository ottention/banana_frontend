package com.example.banana.model

import com.google.gson.annotations.SerializedName

data class LoginGoogleResponseModel (
    @SerializedName("accessToken") var accessToken: String,
    @SerializedName("refreshToken") var refreshToken: String,
)