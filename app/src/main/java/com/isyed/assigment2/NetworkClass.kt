package com.isyed.assigment2

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager


class NetworkClass constructor(): Application() {


    companion object{

        var myAppContext : Context? = null

        fun isNetworkAvailable(): Boolean{

            val connectivityManager = myAppContext?.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }

    override fun onCreate() {
        super.onCreate()
        myAppContext = applicationContext
    }

    fun getAppContext(): Context? {
        return myAppContext
    }

}