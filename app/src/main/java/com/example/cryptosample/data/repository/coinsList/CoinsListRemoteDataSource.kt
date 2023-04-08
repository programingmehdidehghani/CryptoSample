package com.example.cryptosample.data.repository.coinsList

import com.example.cryptosample.api.ApiInterface
import com.example.cryptosample.api.BaseRemoteDataSource
import com.example.cryptosample.api.Result
import com.example.cryptosample.api.models.Coin
import javax.inject.Inject

class CoinsListRemoteDataSource  @Inject constructor(private val service: ApiInterface) :
    BaseRemoteDataSource() {

    suspend fun coinsList(targetCur: String): Result<List<Coin>> =
        getResult {
            service.coinsList(targetCur)
        }
}