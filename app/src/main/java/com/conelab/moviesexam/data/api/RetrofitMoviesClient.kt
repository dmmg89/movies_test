package com.conelab.moviesexam.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object RetrofitMoviesClient {

    private val authInterceptor = Interceptor { chain ->
        try {
            val request = chain.request().newBuilder()
                .addHeader("Authorization", ApiMoviesConfig.AUTH_TOKEN)
                .addHeader("accept", "application/json")
                .build()
            val response = chain.proceed(request)

            if (!response.isSuccessful) {
                throw IOException("Error en la solicitud: ${response.message}")
            }
            response
        } catch (e: Exception) {
            throw RuntimeException("Error en el interceptor: ${e.message}")
        }
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(ApiMoviesConfig.BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
