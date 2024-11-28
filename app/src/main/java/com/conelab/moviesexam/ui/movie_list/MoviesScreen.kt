package com.conelab.moviesexam.ui.movie_list

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.conelab.moviesexam.data.api.MovieApi
import com.conelab.moviesexam.data.api.MoviesRepository
import com.conelab.moviesexam.data.api.RetrofitMoviesClient
import com.conelab.moviesexam.ui.common_composables.MovieCard
import com.conelab.moviesexam.ui.common_composables.TopBar
import com.conelab.moviesexam.ui.navigation.AppRoutes


@Composable
fun MoviesScreen(navController: NavController, repository: MoviesRepository) {


    val viewModel: MoviesViewModel = viewModel(factory = MoviesViewModelFactory(repository))

    val movies = viewModel.movies.collectAsState(initial = emptyList())

    Scaffold(
        topBar = { TopBar(title = "Top de Películas") {} }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(movies.value) { movie ->
                MovieCard(
                    movie = movie,
                    onClick = {
                        Log.d("MoviesScreen", "Movie clicked: ${movie.title}")
                        val movieId = movie.id
                        navController.navigate("details/${movieId}")

                    }
                )
            }
        }
    }

}


