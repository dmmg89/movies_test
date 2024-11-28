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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.conelab.moviesexam.ui.common_composables.DetailsTopBar
import com.conelab.moviesexam.ui.common_composables.ErrorDialog
import com.conelab.moviesexam.ui.common_composables.MovieDetailsCard
import com.conelab.moviesexam.ui.common_composables.SquareButton
import com.conelab.moviesexam.utils.Utils

@Composable
fun DetailsScreen(navController: NavController, viewModel: DetailsViewModel = viewModel())  {

    val movieIdString = navController.currentBackStackEntry?.arguments?.getString("movieId")
    val movieId = movieIdString?.toIntOrNull()
    Log.d("DetailsScreen", "Movie ID: $movieIdString")


    val showDialog =  !Utils.isNetworkAvailable(LocalContext.current)

    if (showDialog) {
        ErrorDialog()
        return
    }else{
        val movie = viewModel.movieDetails
        val isLoading = viewModel.isLoading.collectAsState().value

        Log.d("DetailsScreen", "Movie Details: $movie")

        Scaffold(

            topBar = { DetailsTopBar(title = movie?.data?.title ?:  "Elija un Idioma", onBackClick = { navController.popBackStack() }) }

        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    SquareButton(text= "Español"){
                        viewModel.fetchMovieDetails(movieId= movieId!!,language = "es")
                    }
                    SquareButton(text= "Português"){
                        viewModel.fetchMovieDetails(movieId= movieId!!,language = "pt")
                    }
                    SquareButton(text= "English"){
                        viewModel.fetchMovieDetails(movieId= movieId!!,language = "en")
                    }


                }

                Spacer(modifier = Modifier.height(8.dp))


                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.fillMaxSize(0.4f))
                } else {
                    movie?.let {
                        MovieDetailsCard(movieDetails = it.data)
                    } ?: run {
                        Text(text = "")
                    }
                }


            }

        }
    }

//    viewModel.fetchMovieDetails(movieId, "es")


}