package com.daanidev.appcom.ui.movies

import android.content.Intent
import androidx.databinding.DataBindingUtil
import com.daanidev.appcom.R
import com.daanidev.appcom.databinding.ActivityMoviesTabBinding
import com.daanidev.appcom.ui.movies.adapter.MoviesTabAdapter
import com.daanidev.appcom.base.BaseActivity
import com.daanidev.appcom.ui.addmovie.AddMovieActivity
import com.daanidev.appcom.utils.AppConstants
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

val tabsArray= arrayOf(
    "Top Rated",
    "Low Rated"
)
@AndroidEntryPoint
class MovieListTabActivity : BaseActivity<ActivityMoviesTabBinding>() {


    var categoryID=-1
    var categoryName=""
    private lateinit var tabAdapter: MoviesTabAdapter

    override fun setLayout() : ActivityMoviesTabBinding = DataBindingUtil.setContentView(this,R.layout.activity_movies_tab)

    override fun performBindings() {


        intent?.let {
            categoryID = it.getIntExtra(AppConstants.CATEGORY_INTENT_KEY,-1)
            categoryName = it.getStringExtra(AppConstants.CATEGORY_NAME_INTENT_KEY).toString()
        }

        tabAdapter = MoviesTabAdapter(this, tabsArray.size)
        binding.tabViewPager.adapter=tabAdapter
        TabLayoutMediator(binding.tabsMovie, binding.tabViewPager) { tab, position ->
            tab.text = tabsArray[position]

        }.attach()


       setActivityTitle(categoryName)
        enableBackButton(true)


        binding.btnAddMovie.setOnClickListener {

            val intent = Intent(this,AddMovieActivity::class.java)
            intent.putExtra(AppConstants.CATEGORY_INTENT_KEY,categoryID)
            intent.putExtra(AppConstants.CATEGORY_NAME_INTENT_KEY,categoryName)
            startActivity(intent)
        }
    }

}