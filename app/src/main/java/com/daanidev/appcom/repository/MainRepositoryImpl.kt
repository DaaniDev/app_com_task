package com.daanidev.appcom.repository

import com.daanidev.appcom.network.ApiResult
import com.daanidev.appcom.ui.categories.model.MovieCategoryResponse
import com.daanidev.appcom.network.ApiHelper
import com.daanidev.appcom.ui.addmovie.model.AddMovieResponse
import com.daanidev.appcom.ui.movies.model.MovieListResponse
import com.google.gson.JsonArray
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val apiHelper: ApiHelper) : MainRepository {
    override fun getMovieCategories(): Flow<ApiResult<MovieCategoryResponse>> {

        return flow {

            val response = apiHelper.getMovieCategories()

            if(response.isSuccessful)
            {
                emit(ApiResult.Success(response.body()))
            }
            else{
                emit(ApiResult.Error(response.errorBody().toString()))
            }
        }
    }

    override fun getMovieInCategory(id:Int) : Flow<ApiResult<MovieListResponse>>{
        return flow {

            val response = apiHelper.getMovieInCategory(id)

            if(response.isSuccessful)
            {
                emit(ApiResult.Success(response.body()))
            }
            else{
                emit(ApiResult.Error(response.errorBody().toString()))
            }
        }
    }

    override fun uploadMovie(
        categoryID: RequestBody,
        movieName: RequestBody,
        movieRating: RequestBody,
        movieDescription: RequestBody,
        movieImage: MultipartBody.Part
    ): Flow<ApiResult<AddMovieResponse>> {

        return flow {

            val response = apiHelper.uploadMovie(categoryID,movieName,movieRating,movieDescription,movieImage)

            if(response.isSuccessful)
            {
                emit(ApiResult.Success(response.body()))
            }
            else{
                emit(ApiResult.Error(response.errorBody().toString()))
            }
        }
    }


}