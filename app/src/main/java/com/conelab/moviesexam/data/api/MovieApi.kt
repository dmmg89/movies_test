package com.conelab.moviesexam.data.api

import com.conelab.moviesexam.data.model.MovieDetailsItem
import com.conelab.moviesexam.data.model.TopRatedMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") languaje:String = "es",
        @Query("page") page:Int = 1
    ): TopRatedMoviesResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @retrofit2.http.Path("movie_id") movieId: Int,
        @Query("language") language: String = "es"
    ): MovieDetailsItem
}