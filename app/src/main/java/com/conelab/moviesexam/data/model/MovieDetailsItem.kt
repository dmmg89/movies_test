package com.conelab.moviesexam.data.model


data class MovieDetailsItem(
    val backdrop_path: String,
    val belongs_to_collection: Any?,
    val budget: Int,
    val genres:List<MovieGenre>,
    val id:Int,
    val origin_country:List<String>,
    val original_languaje:String,
    val original_title:String,
    val overview:String,
    val revenue: Int,
    val runtime: Int,
    val title:String
)
