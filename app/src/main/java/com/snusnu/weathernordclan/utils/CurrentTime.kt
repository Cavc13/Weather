package com.snusnu.weathernordclan.utils

import java.text.SimpleDateFormat
import java.util.*

fun getCurrentDate(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    return dateFormat.format(Date()).toString()
}

fun getCurrentHour(): Int {
    val dateFormat = SimpleDateFormat("HH", Locale.ENGLISH)
    return dateFormat.format(Date()).toInt()

}

fun getCurrentMinutes(): Int {
    val dateFormat = SimpleDateFormat("mm", Locale.ENGLISH)
    return dateFormat.format(Date()).toInt()
}