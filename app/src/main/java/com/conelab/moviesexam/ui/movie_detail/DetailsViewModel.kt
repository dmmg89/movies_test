package com.conelab.moviesexam.ui.movie_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.conelab.moviesexam.data.api.MoviesApiResult
import com.conelab.moviesexam.data.api.MoviesRepository
import com.conelab.moviesexam.data.model.MovieDetailsItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(private val movieRepository: MoviesRepository) :ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    var error: String? = null




    var movieDetails : MoviesApiResult.SuccesDetails? = null


    fun fetchMovieDetails(movieId: Int, language: String) {

        error = null

        viewModelScope.launch {
            _isLoading.value = true


            try {

                val response = movieRepository.fetchMovieDetails(movieId, language)
                movieDetails = response as MoviesApiResult.SuccesDetails
                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
                error = "Error al cargar los detalles"
            }
        }
    }




}