package com.daanidev.appcom.ui.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.daanidev.appcom.R
import com.daanidev.appcom.databinding.ItemMovieCategoryBinding
import com.daanidev.appcom.databinding.ItemMovieListBinding
import com.daanidev.appcom.ui.categories.adapter.MovieCategoryListAdapter
import com.daanidev.appcom.ui.categories.model.MovieCategory
import com.daanidev.appcom.ui.movies.model.Movie

class MovieListAdapter: RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {


    private var movieList = listOf<Movie>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.ViewHolder {

        val itemMovieListBinding: ItemMovieListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie_list, parent, false
        )
        return ViewHolder(itemMovieListBinding)
    }

    override fun getItemCount(): Int {

        return movieList.size
    }


    override fun onBindViewHolder(holder: MovieListAdapter.ViewHolder, position: Int) {

        holder.bind(position)

    }

    fun setList(movieList: List<Movie>)
    {
        this.movieList=movieList
        notifyDataSetChanged()
    }
    inner class ViewHolder(private val itemMovieListBinding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(itemMovieListBinding.root) {

        fun bind(pos:Int){

            itemMovieListBinding.movie=movieList[pos]
        }


    }
}