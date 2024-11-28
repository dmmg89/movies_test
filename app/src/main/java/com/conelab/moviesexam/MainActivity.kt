package com.conelab.moviesexam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.conelab.moviesexam.data.api.MovieApi
import com.conelab.moviesexam.data.api.MoviesRepository
import com.conelab.moviesexam.data.api.RetrofitMoviesClient
import com.conelab.moviesexam.ui.navigation.AppNavigation

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {

            val moviesApi = RetrofitMoviesClient.retrofit.create(MovieApi::class.java)
            val repository = MoviesRepository(moviesApi)
            val navController = rememberNavController()
            AppNavigation(navController = navController, repository = repository)


        }

    }
}

