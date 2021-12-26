package com.daanidev.appcom.ui


import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapter {

    // display image using glide
    @JvmStatic
    @BindingAdapter(value = ["image"])
    fun loadImage(view: ImageView, url: String?) {

        Glide.with(view.context).load(url).into(view)
    }






}