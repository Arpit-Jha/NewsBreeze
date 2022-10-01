package com.example.newsbreeze.model

import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("id")
    private val id: String? = "",
    @SerializedName("name")
    private val name: String? = ""
)
