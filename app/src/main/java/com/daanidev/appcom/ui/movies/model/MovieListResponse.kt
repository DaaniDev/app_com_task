package com.daanidev.appcom.ui.movies.model

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    val movies: List<Movie>,
    val success: Boolean
)

data class Movie(
    val category_id: Int,
    val movie_description: String,
    val movie_id: Int,
    val movie_image: String,
    val movie_name: String,
    val movie_rating: Int
)