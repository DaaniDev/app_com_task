package com.daanidev.appcom.network


import com.daanidev.appcom.network.ApiHelper
import com.daanidev.appcom.network.ApiService
import com.google.gson.JsonArray
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {


    override suspend fun getMovieCategories() = apiService.getMovieCategories()

    override suspend fun getMovieInCategory(id:Int) = apiService.getMovieInCategory(id)
    override suspend fun uploadMovie(
        categoryID: RequestBody,
        movieName: RequestBody,
        movieRating: RequestBody,
        movieDescription: RequestBody,
        movieImage: MultipartBody.Part
    ) = apiService.uploadMovie(categoryID,movieName,movieRating,movieDescription,movieImage)

}