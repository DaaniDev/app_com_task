package com.daanidev.appcom.network


import com.daanidev.appcom.ui.addmovie.model.AddMovieResponse
import com.daanidev.appcom.ui.categories.model.MovieCategoryResponse
import com.daanidev.appcom.ui.movies.model.MovieListResponse
import com.google.gson.JsonArray
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Part

interface ApiHelper {

    suspend fun getMovieCategories(): Response<MovieCategoryResponse>
    suspend fun getMovieInCategory(id: Int): Response<MovieListResponse>
    suspend fun uploadMovie(
        categoryID: RequestBody,
        movieName: RequestBody,
        movieRating: RequestBody,
        movieDescription: RequestBody,
        movieImage: MultipartBody.Part
    ): Response<AddMovieResponse>
}