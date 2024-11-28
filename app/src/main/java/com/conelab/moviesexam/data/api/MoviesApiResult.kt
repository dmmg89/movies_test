package com.conelab.moviesexam.data.api

import com.conelab.moviesexam.data.model.MovieDetailsItem
import com.conelab.moviesexam.data.model.TopRatedMoviesResponse

sealed class MoviesApiResult  {
    data class Success(val data: TopRatedMoviesResponse) : MoviesApiResult()
    data class Error(val exception: Exception) : MoviesApiResult()
    data class SuccesDetails(val data: MovieDetailsItem) : MoviesApiResult()
}