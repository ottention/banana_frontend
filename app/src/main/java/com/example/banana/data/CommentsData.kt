package com.example.banana.data

import java.time.LocalDate

data class CommentsData(
    var writer : String,
    var content : String,
    var isGuestBookLike : Boolean,
    var localDateTime : String
)
