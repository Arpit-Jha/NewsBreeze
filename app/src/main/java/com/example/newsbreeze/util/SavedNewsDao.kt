package com.example.newsbreeze.util

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsbreeze.model.News

@Dao
interface SavedNewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(news: News)

    @Delete
    suspend fun delete(news: News)

    @Query("SELECT * FROM news_table WHERE title LIKE '%' || :searchQuery || '%'")
    fun getSearch(searchQuery : String): LiveData<List<News>>

    @Query("SELECT * FROM news_table")
    fun getAllSavedNews(): LiveData<List<News>>
}