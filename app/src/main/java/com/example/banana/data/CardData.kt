package com.example.banana.data

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import java.io.Serializable


data class cardRequestData(
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
): Serializable

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
): Serializable

data class Link (
    var link : String,
    var linkText : String,
    var isFront : Boolean,
    var coordinate : Coordinate
): Serializable

data class Image (
    var isFront : Boolean,
    var imageUrl : String,
    var coordinate : Coordinate
): Serializable

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
): Serializable
{
    data class Contents (
        @SerializedName("content")
        var content : String?,
        @SerializedName("contentSize")
        var contentSize : String?,
        @SerializedName("coordinate")
        var coordinate : Coordinate?,
    ): Serializable
    data class FrontImageCoordinates (
        @SerializedName("xAxis")
        var xAxis : Float?,
        @SerializedName("yAxis")
        var yAxis : Float?
    ): Serializable

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
): Serializable


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
    ): Serializable
    data class BackImageCoordinates (
        @SerializedName("xAxis")
        var xAxis : Float?,
        @SerializedName("yAxis")
        var yAxis : Float?
    ): Serializable
}


data class Coordinate (
    var xAxis : Float?,
    var yAxis : Float?
): Serializable


data class AddCardDataResponseModel (
    var businessCardId : Long
): Serializable