package com.conelab.moviesexam.data.api

import com.conelab.moviesexam.data.model.MovieDetailsItem
import com.conelab.moviesexam.data.model.TopRatedMoviesResponse

class MoviesRepository(private val movieApi: MovieApi) {
    suspend fun fetchTopRatedMovies(page: Int = 1):MoviesApiResult{
        return try {
            val response = movieApi.getTopRatedMovies(page = page)
            MoviesApiResult.Success(response)

        }catch (exception : Exception) {
            MoviesApiResult.Error(exception)
        }
    }

    suspend fun fetchMovieDetails(movieId: Int ) : MoviesApiResult {
        return try{
            val response = movieApi.getMovieDetails(movieId)
            MoviesApiResult.SuccesDetails(response)
        }catch (exception: Exception){
            MoviesApiResult.Error(exception)
        }
    }
}

//import kotlinx.coroutines.runBlocking
//
//fun main() = runBlocking {
//    val api = RetrofitClient.retrofit.create(MovieApi::class.java)
//    val repository = MovieRepository(api)
//
//    val result = repository.fetchTopRatedMovies()
//    result.onSuccess { response ->
//        println("Top Rated Movies:")
//        response.results.forEach { movie ->
//            println("- ${movie.title} (Rating: ${movie.vote_average})")
//        }
//    }.onFailure { error ->
//        println("Failed to fetch movies: ${error.message}")
//    }
//}