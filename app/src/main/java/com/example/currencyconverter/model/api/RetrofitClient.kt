package com.example.currencyconverter.model.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://open.er-api.com/v6/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val exchangeRateApi: ExchangeRateCurrencyApi = retrofit.create(ExchangeRateCurrencyApi::class.java)

}