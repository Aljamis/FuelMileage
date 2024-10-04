package com.example.fuelmileage.common

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun DateTimeToString( theDate: LocalDateTime ) : String {
    val formatter = DateTimeFormatter.ofPattern("LLLL d, Y k:m")
    return theDate.format( formatter )
}