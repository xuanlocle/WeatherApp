package com.xuanlocle.weatherapp.util.dates

import java.text.SimpleDateFormat
import java.util.*

object DateTimeHelper {
    private const val FORMAT_DATETIME = "dd MMM yyyy"


    fun convertTimestampToDate(timeStamp: Long): String{
        return SimpleDateFormat(FORMAT_DATETIME).format(Date(timeStamp))
    }

    fun convertToFormat(time: Long): String? {
        val format = SimpleDateFormat(FORMAT_DATETIME)
        return format.format(Date(time*1000))
    }
}