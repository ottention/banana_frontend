package com.example.banana.data

import android.net.Uri
import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody



data class saveCardRequestModel(
    @SerializedName("isPublic")
    var isPublic : Boolean,
    @SerializedName("isPresent")
    var isPresent : Boolean,
    @SerializedName("frontContents")
    var frontContents : ArrayList<Contents>,
    @SerializedName("frontLinks")
    var frontLinks : ArrayList<Link>,
    @SerializedName("frontImages")
    var frontImages : ArrayList<Image>,
    @SerializedName("frontTemplateColor")
    var frontTemplateColor : String,
    @SerializedName("backContents")
    var backContents : ArrayList<Contents>,
    @SerializedName("backLinks")
    var backLinks : ArrayList<Link>,
    @SerializedName("backImages")
    var backImages : ArrayList<Image>,
    @SerializedName("backTemplateColor")
    var backTemplateColor : String,
    @SerializedName("tags")
    var tags : ArrayList<String>

)
data class getCardResponseModel(
    @SerializedName("isPublic")
    var isPublic : Boolean ?,
    @SerializedName("isPresent")
    var isPresent : Boolean ?,
    @SerializedName("frontContents")
    var frontContents : ArrayList<Contents>?,
    @SerializedName("frontLinks")
    var frontLinks : ArrayList<Link>?,
    @SerializedName("frontImages")
    var frontImages : ArrayList<Image>?,
    @SerializedName("frontTemplateColor")
    var frontTemplateColor : String?,
    @SerializedName("backContents")
    var backContents : ArrayList<Contents>?,
    @SerializedName("backLinks")
    var backLinks : ArrayList<Link>?,
    @SerializedName("backImages")
    var backImages : ArrayList<Image>?,
    @SerializedName("backTemplateColor")
    var backTemplateColor : String?,
    @SerializedName("tags")
    var tags : ArrayList<String>?
)

data class Link (
    var link : String,
    var linkText : String,
    var isFront : Boolean,
    var coordindate : Coordinate
)

data class Image (
    var isFront : Boolean,
    var imageUrl : String,
    var coordinate : Coordinate
)

data class SaveFrontBusinessCardRequest(
//    var frontTemplateColor : String,
    @SerializedName("isPublic")
    var isPublic : Boolean,
    @SerializedName("isPresent")
    var isPresent : Boolean,
    @SerializedName("contents")
    var contents : ArrayList<Contents>?,
    @SerializedName("frontImageCoordinates")
    var frontImageCoordinates: ArrayList<FrontImageCoordinates>?
)
{
    data class Contents (
        @SerializedName("content")
        var content : String?,
        @SerializedName("contentSize")
        var contentSize : String?,
        @SerializedName("coordinate")
        var coordinate : Coordinate?,
    )
    data class FrontImageCoordinates (
        @SerializedName("xAxis")
        var xAxis : Float?,
        @SerializedName("yAxis")
        var yAxis : Float?
    )

}

data class Contents (
    @SerializedName("content")
    var content : String?,
    @SerializedName("contentSize")
    var contentSize : String?,
    @SerializedName("coordinate")
    var coordinate : Coordinate?,
    @SerializedName("isFront")
    var isFront : Boolean
)


data class SaveBackBusinessCardRequest(
    @SerializedName("contents")
    var contents : ArrayList<SaveBackBusinessCardRequest.Contents>?,
    @SerializedName("backImageCoordinates")
    var backImageCoordinates:  ArrayList<SaveBackBusinessCardRequest.BackImageCoordinates>?
){
    data class Contents (
        @SerializedName("content")
        var content : String?,
        @SerializedName("contentSize")
        var contentSize : String?,
        @SerializedName("coordinate")
        var coordinate : Coordinate?,
    )
    data class BackImageCoordinates (
        @SerializedName("xAxis")
        var xAxis : Float?,
        @SerializedName("yAxis")
        var yAxis : Float?
    )
}


data class Coordinate (
    var xAxis : Float?,
    var yAxis : Float?
)

data class saveCardDataRequestModel (
    @SerializedName("frontRequest")
    var frontRequest : SaveFrontBusinessCardRequest,
    @SerializedName("backRequest")
    var backRequest : SaveBackBusinessCardRequest,
    @SerializedName("frontImages")
    var frontImages : ArrayList<MultipartBody.Part>,
    @SerializedName("backImages")
    var backImages : ArrayList<MultipartBody.Part>,
    @SerializedName("tagRequest")
    var tagRequest : ArrayList<String>
)

data class frontImages(
    var mutableList: ArrayList<MultipartBody.Part>?
)

data class backImages(
    var mutableList: ArrayList<MultipartBody.Part>?
)

data class saveCardDataResponseModel (
    var businessCardId : Long
)