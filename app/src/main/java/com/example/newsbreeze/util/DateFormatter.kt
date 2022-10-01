package com.example.newsbreeze.util

import android.content.Context
import java.text.DateFormat
import java.text.SimpleDateFormat

class DateFormatter(context: Context)
{
    private val dateFormat = DateFormat
        .getDateInstance(DateFormat.LONG, context.resources.configuration.locale)

    fun format(oldDate: String?): String
    {
        if (oldDate == null) return ""
        val newDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(oldDate)
        return dateFormat.format(newDate)
    }
}