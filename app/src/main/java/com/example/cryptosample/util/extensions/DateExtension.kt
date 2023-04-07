package com.example.cryptosample.util.extensions

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import java.sql.Date

enum class DateFormat(val format: String) {
    yyyy_MM_dd("yyyy-MM-dd"),
    MMM_yy_w("MMM yy 'week' W"),
    MMM_yy("MMM yy")
}

@SuppressLint("SimpleDateFormat")
fun Date?.formattedString(dateFormat: DateFormat = DateFormat.yyyy_MM_dd): String =
    try {
        SimpleDateFormat(dateFormat.format).format(this)
    } catch (e: Exception) {
        ""
    }