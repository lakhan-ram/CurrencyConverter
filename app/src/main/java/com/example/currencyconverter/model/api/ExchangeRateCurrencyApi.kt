package com.example.currencyconverter.model.api

import com.example.currencyconverter.model.entities.ExchangeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRateCurrencyApi {

    @GET("latest")
    fun getExchangeRates(
        @Query("base") base: String,
    ): Call<ExchangeResponse>

}