package com.example.cryptosample.util

import androidx.appcompat.app.AppCompatDelegate

enum class ThemeMode {
    Light,
    Dark
}

object ThemeHelper {

    //Applies the theme to the app based on the selected by the user
    fun applyTheme(theme: ThemeMode) {
        when (theme) {
            ThemeMode.Light -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            ThemeMode.Dark -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

}