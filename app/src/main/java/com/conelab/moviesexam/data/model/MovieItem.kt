package com.conelab.moviesexam.data.model


data class MovieItem(
    val id: Int,
    val original_title: String,
    val title: String,
    val vote_average: Double? = null,
    val release_date: String? = null,
    val backdrop_path: String? = null,
    val gender_ids: List<Int>? = null,
)
