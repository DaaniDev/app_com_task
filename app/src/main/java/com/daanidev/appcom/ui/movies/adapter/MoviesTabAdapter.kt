package com.daanidev.appcom.ui.movies.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.daanidev.appcom.ui.movies.fragments.LowRatedMoviesFragment
import com.daanidev.appcom.ui.movies.fragments.TopRatedMoviesFragment

class MoviesTabAdapter (fm: FragmentActivity, private val tabCount: Int) : FragmentStateAdapter(fm) {
    override fun getItemCount(): Int {
        return tabCount
    }

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> TopRatedMoviesFragment()
            else -> LowRatedMoviesFragment()
        }
    }
}