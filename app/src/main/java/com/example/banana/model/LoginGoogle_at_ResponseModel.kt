package com.example.banana.model

import com.google.gson.annotations.SerializedName

data class LoginGoogle_at_ResponseModel (
    @SerializedName("access_token") var access_token: String,
)