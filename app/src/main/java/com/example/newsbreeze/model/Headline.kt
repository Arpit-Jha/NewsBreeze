package com.example.newsbreeze.model

import com.google.gson.annotations.SerializedName

data class Headline(
    @SerializedName("source")
    val source: Source? = null,
    @SerializedName("author")
    val author: String? = "",
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String? = "",
    @SerializedName("publishedAt")
    val publishedAt: String? = "",
    @SerializedName("content")
    val content: String? = ""
)