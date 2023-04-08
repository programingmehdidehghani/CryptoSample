package com.example.cryptosample.data.repository.projectProfile

import com.example.cryptosample.api.ApiInterface
import com.example.cryptosample.api.BaseRemoteDataSource
import com.example.cryptosample.api.models.HistoricalPriceResponse
import javax.inject.Inject

class ProjectProfileRemoteDataSource @Inject constructor(private val service: ApiInterface) : BaseRemoteDataSource(){

    //fetch historical price from backend
    suspend fun historicalPrice(symbol: String, targetCurrency: String, days: Int = 30): com.example.cryptosample.api.Result<HistoricalPriceResponse> =
        getResult { service.historicalPrice(symbol, targetCurrency, days) }

}