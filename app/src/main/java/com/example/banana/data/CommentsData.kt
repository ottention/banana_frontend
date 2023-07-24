package com.example.banana.data



data class comment (
    var guestBookId : Long,
    var writer : String,
    var content : String,
    var isGuestBookLike : Boolean,
    var localDateTime : String
)
