package com.conelab.moviesexam.ui.movie_detail

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.conelab.moviesexam.data.api.MoviesRepository
import com.conelab.moviesexam.ui.common_composables.DetailsTopBar
import com.conelab.moviesexam.ui.common_composables.MovieDetailsCard
import com.conelab.moviesexam.ui.common_composables.TopBar

@Composable
fun DetailsScreen(navController: NavController, viewModel: DetailsViewModel = viewModel())  {

    val movieIdString = navController.currentBackStackEntry?.arguments?.getString("movieId")
    val movieId = movieIdString?.toIntOrNull()
    Log.d("DetailsScreen", "Movie ID: $movieIdString")


    if (movieId == null) {
        return
    }
    viewModel.fetchMovieDetails(movieId)
    val movie = viewModel.movieDetails
    val isLoading = viewModel.isLoading.collectAsState().value

    Log.d("DetailsScreen", "Movie Details: $movie")

    Scaffold(

        topBar = { DetailsTopBar(title = movie?.data?.title ?:  "Sin título ", onBackClick = { navController.popBackStack() }) }

    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            if (isLoading) {
                // Mostrar el indicador de carga mientras se esperan los detalles de la película
                CircularProgressIndicator(modifier = Modifier.fillMaxSize())
            } else {
                // Mostrar los detalles de la película solo si no está cargando
                movie?.let {
                    MovieDetailsCard(movieDetails = it.data)
                } ?: run {
                    // Si no hay datos de la película, mostrar un mensaje o manejar el error
                    Text(text = "No se pudieron cargar los detalles de la película.")
                }
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Español")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Português")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "English")
                }
            }
        }

    }

}