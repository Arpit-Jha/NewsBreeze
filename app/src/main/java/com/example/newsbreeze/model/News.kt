package com.example.newsbreeze.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_table")
data class News(
    @ColumnInfo(name = "author") val author: String? = "",
    @ColumnInfo(name = "title") val title: String? = "",
    @ColumnInfo(name = "description") val description: String? = "",
    @ColumnInfo(name = "content") val content: String? = "",
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "urlToImage") val urlToImage: String? = "",
    @ColumnInfo(name = "published_at") val publishedAt: String?,
)
