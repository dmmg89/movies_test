package com.conelab.moviesexam.data.model

data class TopRatedMoviesResponse(
    val results: List<MovieItem>,
    val page: Int,
    val total_results: Int,
    val total_pages: Int
)
