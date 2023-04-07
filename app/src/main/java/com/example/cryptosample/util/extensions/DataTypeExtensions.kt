package com.example.cryptosample.util.extensions

import android.annotation.SuppressLint
import java.util.*

fun String?.emptyIfNull(): String {
    return this ?: ""
}

fun String?.trimParanthesis(): String {
    return this?.replace(Regex("[()]"), "") ?: ""
}

@SuppressLint("SimpleDateFormat")
fun String?.formattedDate(dateFormat: DateFormat = DateFormat.yyyy_MM_dd): Date? =
    this?.let {
        try {
            SimpleDateFormat(dateFormat.format).parse(this)
        } catch (e: Exception) {
            null
        }
    }

fun Double?.dollarString(): String {
    return this?.let {
        val numberFormat = DecimalFormat("#,##0.00")
        "US$ ${numberFormat.format(this)}"
    } ?: ""
}