package com.conelab.moviesexam.data.api


class MoviesRepository( private val movieApi: MovieApi) {
    suspend fun fetchTopRatedMovies(page: Int = 1, language: String ):MoviesApiResult{
        return try {
            val response = movieApi.getTopRatedMovies(page = page)
            MoviesApiResult.Success(response)

        }catch (exception : Exception) {
            MoviesApiResult.Error(exception)
        }
    }

    suspend fun fetchMovieDetails(movieId: Int , language: String ) : MoviesApiResult {
        return try{
            val response = movieApi.getMovieDetails(movieId = movieId, language = language)
            MoviesApiResult.SuccesDetails(response)
        }catch (exception: Exception){
            MoviesApiResult.Error(exception)
        }
    }



}

