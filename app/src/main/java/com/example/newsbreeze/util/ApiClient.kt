package com.example.newsbreeze.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient
{
    private const val url = "https://newsapi.org/v2/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getApiClient(): Retrofit
    {
        return retrofit
    }
}