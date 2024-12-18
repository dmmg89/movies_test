package com.conelab.moviesexam.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.conelab.moviesexam.data.api.MovieApi
import com.conelab.moviesexam.data.api.MoviesRepository
import com.conelab.moviesexam.data.api.RetrofitMoviesClient
import com.conelab.moviesexam.data.database.MovieDatabaseHelper
import com.conelab.moviesexam.data.preferences.SPManager
import com.conelab.moviesexam.ui.movie_detail.DetailsScreen
import com.conelab.moviesexam.ui.movie_detail.DetailsViewModel
import com.conelab.moviesexam.ui.movie_detail.DetailsViewModelFactory
import com.conelab.moviesexam.ui.movie_list.MoviesScreen
import com.conelab.moviesexam.utils.Utils.getAppContext

object AppRoutes {
    const val MOVIES = "movies"
    const val DETAILS = "details/{movieId}"
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(navController: NavHostController, repository: MoviesRepository) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.MOVIES
    ) {
        composable(AppRoutes.MOVIES) {
            val spManager = SPManager(getAppContext())

            MoviesScreen(
                navController = navController,
                repository = repository,
                spManager = spManager,
            )
        }
        composable(AppRoutes.DETAILS) { _ ->
            val viewModel: DetailsViewModel =
                viewModel(factory = DetailsViewModelFactory(repository))
            DetailsScreen(navController = navController, viewModel = viewModel)
        }
    }
}