package com.conelab.moviesexam.utils

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Utils {

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentDate(): String {
        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return currentDate.format(formatter)
    }

    @Composable
    fun getAppContext(): Context {
        return LocalContext.current
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}