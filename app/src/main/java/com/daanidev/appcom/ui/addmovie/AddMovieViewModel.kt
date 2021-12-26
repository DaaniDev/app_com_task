package com.daanidev.appcom.ui.addmovie

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daanidev.appcom.network.ApiResult
import com.daanidev.appcom.network.ApiStatus
import com.daanidev.appcom.repository.MainRepositoryImpl
import com.daanidev.appcom.ui.addmovie.model.AddMovieResponse
import com.daanidev.appcom.ui.movies.model.MovieListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.InputStream
import java.net.URI
import java.util.stream.Stream
import javax.inject.Inject


@HiltViewModel
class AddMovieViewModel @Inject constructor(private val mainRepositoryImpl: MainRepositoryImpl) :
    ViewModel() {


    var addMovieLiveData = MutableLiveData<ApiResult<AddMovieResponse>>()

    fun addMovie(category_ID:Int,movie_Name:String,movie_Rating:Int,movie_Description:String,file_name:String,movie_Image: InputStream){

        val part = MultipartBody.Part.createFormData(
            "movie_image", file_name, RequestBody.create(
                "image/*".toMediaTypeOrNull(),
                movie_Image.readBytes()
            )
        )
        val categoryID =
           category_ID.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
        val movieName = movie_Name.toRequestBody("multipart/form-data".toMediaTypeOrNull())
        val movieRating = movie_Rating.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
        val movieDescription = movie_Description.toRequestBody("multipart/form-data".toMediaTypeOrNull())

        viewModelScope.launch {
            val response = mainRepositoryImpl.uploadMovie(categoryID,movieName,movieRating,movieDescription,part)

            response.collect {

                when(it.status)
                {
                    ApiStatus.SUCCESS->{

                        addMovieLiveData.postValue(ApiResult.Success(it.data))
                    }
                    ApiStatus.ERROR->{

                        addMovieLiveData.postValue(ApiResult.Error(it.message.toString()))
                    }
                }
            }
        }


    }

}