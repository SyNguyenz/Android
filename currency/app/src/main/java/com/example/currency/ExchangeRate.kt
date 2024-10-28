package com.example.currency

data class ExchangeRate(
    val base: String,
    val rates: Map<String, Double>
)