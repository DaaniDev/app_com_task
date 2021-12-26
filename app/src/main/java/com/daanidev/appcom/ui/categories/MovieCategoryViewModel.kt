package com.daanidev.appcom.ui.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daanidev.appcom.network.ApiResult
import com.daanidev.appcom.network.ApiStatus
import com.daanidev.appcom.ui.categories.model.MovieCategoryResponse
import com.daanidev.appcom.repository.MainRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieCategoryViewModel  @Inject constructor(private val mainRepositoryImpl: MainRepositoryImpl) : ViewModel() {

    var movieCategoryLiveData = MutableLiveData<ApiResult<MovieCategoryResponse>>()

    fun getMovieCategories(){

        viewModelScope.launch {
            val response = mainRepositoryImpl.getMovieCategories()

            response.collect {

                when(it.status)
                {
                    ApiStatus.SUCCESS->{

                        movieCategoryLiveData.postValue(ApiResult.Success(it.data))
                    }
                    ApiStatus.ERROR->{

                      movieCategoryLiveData.postValue(ApiResult.Error(it.message.toString()))
                    }
                }
            }
        }


    }

}