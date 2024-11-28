package com.conelab.moviesexam.ui.movie_list

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.conelab.moviesexam.data.api.MoviesRepository
import com.conelab.moviesexam.data.database.MovieDatabaseHelper
import com.conelab.moviesexam.data.preferences.SPManager
import com.conelab.moviesexam.ui.common_composables.MovieCard
import com.conelab.moviesexam.ui.common_composables.TopBar
import com.conelab.moviesexam.utils.Utils.getCurrentDate


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MoviesScreen(
    navController: NavController,
    repository: MoviesRepository,
    spManager: SPManager,
) {

    val context = LocalContext.current
    val movieDatabaseHelper = MovieDatabaseHelper(context)

    val viewModel: MoviesViewModel =
        viewModel(factory = MoviesViewModelFactory(repository, context))

    val movies = viewModel.movies.collectAsState(initial = emptyList())


    if (!spManager.isUpdated() || spManager.getDate() != getCurrentDate()) {
        viewModel.fetchMoviesFromApi()
    } else {
        viewModel.fetchMoviesFromDb()
    }

    val sortedMovies = movies.value.sortedBy { it.vote_average }

    Scaffold(
        topBar = { TopBar(title = "Top de Películas") }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            itemsIndexed(sortedMovies) { index, movie ->
                MovieCard(
                    index = index + 1,
                    movie = movie,
                    onClick = {
                        Log.d("MoviesScreen", "Movie clicked: ${movie.title}")
                        val movieId = movie.id
                        navController.navigate("details/${movieId}")

                    }
                )

                movieDatabaseHelper.insertMovie(movie)
            }

        }

        spManager.setUpdated(true)
        spManager.setDate(getCurrentDate())


    }

}

@Composable
fun getCurrentContext(): Context {
    return LocalContext.current
}

