package com.daanidev.appcom.network


import com.daanidev.appcom.ui.addmovie.model.AddMovieResponse
import com.daanidev.appcom.ui.categories.model.MovieCategoryResponse
import com.daanidev.appcom.ui.movies.model.MovieListResponse
import com.google.gson.JsonArray
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

     @GET("get_all_categories")
    suspend fun getMovieCategories() : Response<MovieCategoryResponse>

     @GET("get_category_movies")
     suspend fun getMovieInCategory(@Query("category_id") categoryID:Int) : Response<MovieListResponse>

     @Multipart
     @POST("add_new_movie")
     suspend fun uploadMovie(@Part("category_id") categoryID: RequestBody,
                             @Part("movie_name") movieName: RequestBody,
                             @Part("movie_rating") movieRating: RequestBody,
                             @Part("movie_description") movieDescription: RequestBody,
                             @Part  movieImage: MultipartBody.Part) : Response<AddMovieResponse>
}