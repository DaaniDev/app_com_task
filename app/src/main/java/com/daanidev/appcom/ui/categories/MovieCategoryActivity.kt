package com.daanidev.appcom.ui.categories

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.daanidev.appcom.R
import com.daanidev.appcom.databinding.ActivityMovieCategoryBinding
import com.daanidev.appcom.extensions.showToast
import com.daanidev.appcom.network.ApiStatus
import com.daanidev.appcom.ui.categories.adapter.MovieCategoryListAdapter
import com.daanidev.appcom.ui.movies.MovieListTabActivity
import com.daanidev.appcom.base.BaseActivity
import com.daanidev.appcom.utils.AppConstants
import dagger.hilt.android.AndroidEntryPoint

const val movieTitle = "Movies"

@AndroidEntryPoint
class MovieCategoryActivity : BaseActivity<ActivityMovieCategoryBinding>() {

    private lateinit var movieCategoryListAdapter: MovieCategoryListAdapter
    private lateinit var mContext: Context
    private val viewModel by viewModels<MovieCategoryViewModel>()

    override fun setLayout(): ActivityMovieCategoryBinding =
        DataBindingUtil.setContentView(this, R.layout.activity_movie_category)

    override fun performBindings() {
        mContext = this
        setupUI()
    }

    private fun setupUI() {

        setActivityTitle(movieTitle)

        movieCategoryListAdapter = MovieCategoryListAdapter {id,name->

            startMovieListActivity(id,name)

        }
        binding.rvMovieCategory.apply {
            this.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
            this.adapter = movieCategoryListAdapter
        }



        viewModel.movieCategoryLiveData.observe(this, {

            when (it.status) {
                ApiStatus.SUCCESS -> {

                    it.data?.let { list ->
                        movieCategoryListAdapter.setList(list.movie_categories)
                    }
                }
                ApiStatus.ERROR -> {
                    mContext.showToast(it.message.toString())
                }
            }
        })

        viewModel.getMovieCategories()
    }

    private fun startMovieListActivity(id:Int,name:String){

        val intent = Intent(mContext,MovieListTabActivity::class.java)
        intent.putExtra(AppConstants.CATEGORY_INTENT_KEY,id)
        intent.putExtra(AppConstants.CATEGORY_NAME_INTENT_KEY,name)
        startActivity(intent)
    }

}