package com.example.currencyconverter.model.entities

data class ExchangeResponse(
    val base: String,
    val rates: Map<String, Double>
)