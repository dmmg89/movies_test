package com.conelab.moviesexam.data.model

//import androidx.room.Entity
//import androidx.room.PrimaryKey

//@Entity(tableName = "movies")
data class MovieItem(
//    @PrimaryKey val id: Int,
    val id: Int,
    val original_title: String,
    val title: String,
    val vote_average: Double? = null,
    val release_date: String? = null,
    val backdrop_path: String? = null,
    val gender_ids: List<Int>? = null,
)
