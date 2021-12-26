package com.daanidev.appcom.repository


import com.daanidev.appcom.network.ApiResult
import com.daanidev.appcom.ui.addmovie.model.AddMovieResponse
import com.daanidev.appcom.ui.categories.model.MovieCategoryResponse
import com.daanidev.appcom.ui.movies.model.MovieListResponse
import com.google.gson.JsonArray
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

interface MainRepository {


    fun getMovieCategories(): Flow<ApiResult<MovieCategoryResponse>>
    fun getMovieInCategory(id: Int): Flow<ApiResult<MovieListResponse>>
    fun uploadMovie(
        categoryID: RequestBody,
        movieName: RequestBody,
        movieRating: RequestBody,
        movieDescription: RequestBody,
        movieImage: MultipartBody.Part
    ): Flow<ApiResult<AddMovieResponse>>
}

