package com.example.cryptosample.ui.home.coinsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cryptosample.core.common.BaseViewModel
import com.example.cryptosample.data.local.database.CoinsListEntity
import com.example.cryptosample.data.repository.coinsList.CoinsListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CoinListViewModel @Inject constructor(private val repository: CoinsListRepository) :
    BaseViewModel() {

    val coinsListData = repository.allCoinsLD

    private val _favouriteStock = MutableLiveData<CoinsListEntity?>()
    val favouriteStock: LiveData<CoinsListEntity?> = _favouriteStock


    fun isListEmpty(): Boolean {
        return coinsListData.value?.isEmpty() ?: true
    }

    fun loadCoinsFromApi(targetCur: String = "usd") {
        if (repository.loadData()) {
            viewModelScope.launch(Dispatchers.IO) {
                _isLoading.postValue(true)
                repository.coinsList(targetCur)
                _isLoading.postValue(false)
            }
        }
    }

    fun updateFavouriteStatus(symbol: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.updateFavouriteStatus(symbol)
            when (result) {
                is com.example.cryptosample.api.Result.Success -> _favouriteStock.postValue(result.data)
                is com.example.cryptosample.api.Result.Error -> _toastError.postValue(result.message)
                else -> {}
            }
        }
    }

}