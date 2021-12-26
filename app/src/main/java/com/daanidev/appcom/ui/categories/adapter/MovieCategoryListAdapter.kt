package com.daanidev.appcom.ui.categories.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daanidev.appcom.R
import com.daanidev.appcom.databinding.ItemMovieCategoryBinding
import com.daanidev.appcom.ui.categories.model.MovieCategory

class MovieCategoryListAdapter (val itemClicked:(categoryID:Int,categoryName:String)->Unit): RecyclerView.Adapter<MovieCategoryListAdapter.ViewHolder>() {


    private var movieCategoryList = listOf<MovieCategory>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCategoryListAdapter.ViewHolder {

        val itemMovieCategoryBinding: ItemMovieCategoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie_category, parent, false
        )
        return ViewHolder(itemMovieCategoryBinding)
    }

    override fun getItemCount(): Int {

        return movieCategoryList.size
    }


    override fun onBindViewHolder(holder: MovieCategoryListAdapter.ViewHolder, position: Int) {

        holder.bind(position)

    }

    fun setList(movieCategoryList: List<MovieCategory>)
    {
        this.movieCategoryList=movieCategoryList
        notifyDataSetChanged()
    }
    inner class ViewHolder(private val itemMovieCategoryBinding: ItemMovieCategoryBinding) :
        RecyclerView.ViewHolder(itemMovieCategoryBinding.root) {

        fun bind(pos:Int){

            itemMovieCategoryBinding.category=movieCategoryList[pos]

        }
        init {
            itemMovieCategoryBinding.cardMovieCategory.setOnClickListener {

                itemClicked.invoke(movieCategoryList[adapterPosition].category_id,movieCategoryList[adapterPosition].category_name)
            }
        }

    }
}