package com.example.newsbreeze.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.widget.Toast


class MainActivity: Activity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        val intent: Intent

        if (isNetworkAvailable()) intent = Intent(this, HeadlinesActivity::class.java)
        else
        {
            intent = Intent(this, SavedNewsActivity::class.java)
            Toast.makeText(this, "No internet available, showing saved news.", Toast.LENGTH_SHORT)
                .show()
        }

        startActivity(intent)
        finish()
    }

    private fun isNetworkAvailable(): Boolean
    {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
        {
            val nw = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when
            {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                //for other device how are able to connect with Ethernet
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                //for check internet over Bluetooth
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        }
        else
        {
            return connectivityManager.activeNetworkInfo?.isConnected ?: false
        }
    }
}