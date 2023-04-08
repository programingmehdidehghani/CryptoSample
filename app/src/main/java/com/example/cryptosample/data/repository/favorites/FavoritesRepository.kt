package com.example.cryptosample.data.repository.favorites

import androidx.lifecycle.LiveData
import com.example.cryptosample.data.local.database.CoinsListEntity
import com.example.cryptosample.util.Constants
import javax.inject.Inject

class FavoritesRepository @Inject constructor(private val favoritesDataSource: FavoritesDataSource) {

    val favoriteCoins: LiveData<List<CoinsListEntity>> = favoritesDataSource.favoriteCoins

    suspend fun favoriteSymbols(): List<String> = favoritesDataSource.favouriteSymbols()

    suspend fun updateFavouriteStatus(symbol: String): com.example.cryptosample.api.Result<CoinsListEntity> {
        val result = favoritesDataSource.updateFavouriteStatus(symbol)
        return result?.let {
            com.example.cryptosample.api.Result.Success(it)
        } ?: com.example.cryptosample.api.Result.Error(Constants.GENERIC_ERROR)
    }
}