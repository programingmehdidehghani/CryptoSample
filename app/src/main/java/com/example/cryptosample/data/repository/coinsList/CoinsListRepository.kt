package com.example.cryptosample.data.repository.coinsList

import com.example.cryptosample.api.succeeded
import com.example.cryptosample.data.local.database.CoinsListEntity
import com.example.cryptosample.data.local.prefs.PreferenceStorage
import com.example.cryptosample.util.Constants
import java.util.*
import javax.inject.Inject

class CoinsListRepository @Inject constructor(
    private val coinsListRemoteDataSource: CoinsListRemoteDataSource,
    private val coinsListDataSource: CoinsListDataSource,
    private val preferenceStorage: PreferenceStorage
) {
    val allCoinsLD = coinsListDataSource.allCoinsLD

    suspend fun coinsList(targetCur: String) {
        when (val result = coinsListRemoteDataSource.coinsList(targetCur)) {
            is com.example.cryptosample.api.Result.Success -> {
                if (result.succeeded) {
                    val favSymbols = coinsListDataSource.favouriteSymbols()

                    val customStockList = result.data.let {
                        it.filter { item -> item.symbol.isNullOrEmpty().not() }
                            .map { item ->
                                CoinsListEntity(
                                    item.symbol ?: "",
                                    item.id,
                                    item.name,
                                    item.price,
                                    item.changePercent,
                                    item.image,
                                    favSymbols.contains(item.symbol)
                                )
                            }
                    }

                    coinsListDataSource.insertCoinsIntoDB(customStockList)

                    preferenceStorage.timeLoadedAt = Date().time

                    com.example.cryptosample.api.Result.Success(true)
                } else {
                    com.example.cryptosample.api.Result.Error(Constants.GENERIC_ERROR)
                }
            }
            else -> result as com.example.cryptosample.api.Result.Error
        }
    }

    suspend fun updateFavouriteStatus(symbol: String): com.example.cryptosample.api.Result<CoinsListEntity> {
        val result = coinsListDataSource.updateFavouriteStatus(symbol)
        return result?.let {
            com.example.cryptosample.api.Result.Success(it)
        } ?: com.example.cryptosample.api.Result.Error(Constants.GENERIC_ERROR)
    }

    fun loadData(): Boolean {
        val lastLoadedDate = preferenceStorage.timeLoadedAt
        val currentDataMillis = Date().time
        return currentDataMillis - lastLoadedDate > 10 * 1000
    }
}