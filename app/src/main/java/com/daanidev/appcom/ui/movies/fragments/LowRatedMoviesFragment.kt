package com.daanidev.appcom.ui.movies.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daanidev.appcom.R
import com.daanidev.appcom.base.BaseFragment
import com.daanidev.appcom.databinding.FragmentMovieListBinding
import com.daanidev.appcom.extensions.showToast
import com.daanidev.appcom.network.ApiStatus
import com.daanidev.appcom.ui.movies.MovieListTabActivity
import com.daanidev.appcom.ui.movies.MovieListViewModel
import com.daanidev.appcom.ui.movies.adapter.MovieListAdapter
import com.daanidev.appcom.ui.movies.model.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LowRatedMoviesFragment : BaseFragment<FragmentMovieListBinding>() {

    private val movieListAdapter = MovieListAdapter()
    private lateinit var sortedList : List<Movie>
    private val viewModel by viewModels<MovieListViewModel>()
    override fun setLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMovieListBinding {

        return DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false)
    }

    override fun performBindings() {

        viewModel.getMovieList((activity as MovieListTabActivity).categoryID)

        binding.rvMovieList.apply {
            this.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            this.adapter = movieListAdapter
        }

        viewModel.movieListLiveData.observe(this, {

            when (it.status) {

                ApiStatus.SUCCESS -> {

                    it.data?.let { data ->

                        sortedList = data.movies.sortedBy { movie ->
                            movie.movie_rating
                        }

                        movieListAdapter.setList(sortedList)
                    }
                }
                ApiStatus.ERROR -> {

                    requireContext().showToast(it.message.toString())
                }
            }
        })


    }
}