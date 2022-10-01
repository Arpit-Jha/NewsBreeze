package com.example.newsbreeze.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.newsbreeze.model.News
import com.example.newsbreeze.util.SavedNewsDatabase

class SavedNewsRepository(context: Context)
{
    private val savedNewsDao = SavedNewsDatabase.getDatabase(context).savedNewsDao()
    private val savedNews = savedNewsDao.getAllSavedNews()

    suspend fun upsertNews(news: News)
    {
        savedNewsDao.upsert(news)
    }

    suspend fun deleteNews(news: News)
    {
        savedNewsDao.delete(news)
    }

    fun searchNews(query: String): LiveData<List<News>>
    {
        return savedNewsDao.getSearch(query)
    }

    fun getAllSavedNews(): LiveData<List<News>>
    {
        return savedNews
    }
}