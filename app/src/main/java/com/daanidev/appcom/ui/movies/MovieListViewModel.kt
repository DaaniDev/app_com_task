package com.daanidev.appcom.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daanidev.appcom.network.ApiResult
import com.daanidev.appcom.network.ApiStatus
import com.daanidev.appcom.repository.MainRepositoryImpl
import com.daanidev.appcom.ui.categories.model.MovieCategoryResponse
import com.daanidev.appcom.ui.movies.model.MovieListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val mainRepositoryImpl: MainRepositoryImpl)  : ViewModel() {


    var movieListLiveData = MutableLiveData<ApiResult<MovieListResponse>>()

    fun getMovieList(categoryID:Int){

        viewModelScope.launch {
            val response = mainRepositoryImpl.getMovieInCategory(categoryID)

            response.collect {

                when(it.status)
                {
                    ApiStatus.SUCCESS->{

                        movieListLiveData.postValue(ApiResult.Success(it.data))
                    }
                    ApiStatus.ERROR->{

                        movieListLiveData.postValue(ApiResult.Error(it.message.toString()))
                    }
                }
            }
        }


    }
}