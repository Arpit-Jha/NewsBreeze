package com.example.newsbreeze.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.newsbreeze.model.HeadlineResponse
import com.example.newsbreeze.model.News
import com.example.newsbreeze.repository.SavedNewsRepository
import com.example.newsbreeze.repository.HeadlinesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(context: Context)
{
    private val TAG = "ViewModel"

    private val scope = CoroutineScope(Dispatchers.IO)
    private val headlinesRepository = HeadlinesRepository()
    private val savedNewsRepository = SavedNewsRepository(context)

    fun getTopHeadlines(keyword: String?, countryCode: String): LiveData<HeadlineResponse> {
        return headlinesRepository.getHeadlines(keyword, countryCode)
    }

    fun upsert(news: News)
    {
        scope.launch {
            savedNewsRepository.upsertNews(news)
        }
    }

    fun delete(news: News)
    {
        scope.launch {
            savedNewsRepository.deleteNews(news)
        }
    }

    fun searchNews(query: String): LiveData<List<News>>
    {
        return savedNewsRepository.searchNews(query)
    }

    fun getAllSavedNews(): LiveData<List<News>>
    {
        return savedNewsRepository.getAllSavedNews()
    }
}