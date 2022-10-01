package com.example.newsbreeze.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsbreeze.model.HeadlineResponse
import com.example.newsbreeze.util.ApiClient
import com.example.newsbreeze.util.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeadlinesRepository
{
    private val TAG = "HeadlinesRepository"

    private val apiKey = "58923f75765e4461a84f194a7a86098e"
    private val apiInterface: ApiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)

    fun getHeadlines(keyword: String?, countryCode: String): LiveData<HeadlineResponse>
    {
        val data = MutableLiveData<HeadlineResponse>()

        if (keyword == null)
        {
            apiInterface.getNews(countryCode, apiKey).enqueue(
                object : Callback<HeadlineResponse> {
                    override fun onResponse(
                        call: Call<HeadlineResponse>,
                        response: Response<HeadlineResponse>
                    ) {
                        if (response.body() != null) data.value = response.body()
                    }

                    override fun onFailure(call: Call<HeadlineResponse>, t: Throwable) {
                        Log.e(TAG, "onFailure: ", t)
                    }
                }
            )
        }
        else
        {
            apiInterface.searchNews(keyword, apiKey).enqueue(
                object : Callback<HeadlineResponse> {
                    override fun onResponse(
                        call: Call<HeadlineResponse>,
                        response: Response<HeadlineResponse>
                    ) {
                        if (response.body() != null) data.value = response.body()
                    }

                    override fun onFailure(call: Call<HeadlineResponse>, t: Throwable) {
                        Log.e(TAG, "onFailure: ", t)
                    }
                }
            )
        }

        return data
    }
}