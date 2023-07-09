package com.example.banana.data


data class CommentsData(
    var writer : String,
    var content : String,
    var isGuestBookLike : Boolean,
    var localDateTime : String
)
