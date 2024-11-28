package com.conelab.moviesexam.ui.movie_list

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.conelab.moviesexam.utils.Constants
import com.conelab.moviesexam.data.api.MoviesApiResult
import com.conelab.moviesexam.data.api.MoviesRepository
import com.conelab.moviesexam.data.database.MovieDatabaseHelper
import com.conelab.moviesexam.data.model.MovieItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)

class MoviesViewModel(
    val moviesRepository: MoviesRepository,
    context: Context
) : ViewModel() {


    private val movieDatabaseHelper = MovieDatabaseHelper(context)
    private val _movies = MutableStateFlow<List<MovieItem>>(emptyList())


    val movies: StateFlow<List<MovieItem>> = _movies

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage


//    init {
//        fetchMoviesFromApi()
//    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun fetchMoviesFromApi(page: Int = 1) {
        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch {
            val result = moviesRepository.fetchTopRatedMovies(page, "es")
            _isLoading.value = false
            when (result) {
                is MoviesApiResult.Success -> {

                    _movies.value = result.data.results.take(Constants.MAX_MOVIES_RESULTS)
                        .map { movie: MovieItem ->
                            MovieItem(
                                movie.id,
                                movie.original_title,
                                movie.title,
                                movie.vote_average,
                                movie.release_date,
                                movie.backdrop_path,
                                movie.gender_ids
                            )


                        }
                    result.data.results.take(Constants.MAX_MOVIES_RESULTS).forEach { movie ->
                        movieDatabaseHelper.insertMovie(movie)
                    }

                }

                is MoviesApiResult.Error -> {
                    _errorMessage.value = result.exception.message ?: "Error desconocido"
                    fetchMoviesFromDb()

                }

                is MoviesApiResult.SuccesDetails -> {}
            }
        }
    }


    fun fetchMoviesFromDb() {
        _movies.value = movieDatabaseHelper.getAllMovies()

    }

}

