package com.example.currencyconverter.model.repositories

import com.example.currencyconverter.model.api.RetrofitClient
import com.example.currencyconverter.model.entities.ExchangeResponse

class CurrencyRateRepository {
    private val api = RetrofitClient.exchangeRateApi

    fun getExchangeRate(baseCurrency: String): ExchangeResponse? {
        return api.getExchangeRates(baseCurrency).execute().body()
    }
}