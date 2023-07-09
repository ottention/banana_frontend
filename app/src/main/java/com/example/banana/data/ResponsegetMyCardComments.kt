package com.example.banana.data

data class ResponsegetMyCardComments(

    val commentsList : ArrayList<comment>

)

data class comment (
    var writer : String,
    var content : String,
    var isGuestBookLike : Boolean,
    var localDateTime : String
        )
