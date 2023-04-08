package com.example.cryptosample.ui.home

import com.example.cryptosample.core.common.BaseViewModel
import com.example.cryptosample.data.repository.settings.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeActivityViewModel @Inject constructor(private val repository: SettingsRepository) : BaseViewModel() {

    fun isDarkModeOn () : Boolean{
        return repository.isDarkModeEnabled()
    }
}