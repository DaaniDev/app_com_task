package com.daanidev.appcom.ui.categories.model

import com.google.gson.annotations.SerializedName


data class MovieCategoryResponse(
    val movie_categories: List<MovieCategory>,
    val success: Boolean
)

data class MovieCategory(
    val category_id: Int,
    val category_image: String,
    val category_name: String
)