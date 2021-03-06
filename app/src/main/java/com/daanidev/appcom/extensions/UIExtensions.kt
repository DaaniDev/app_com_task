package com.daanidev.appcom.extensions

import android.content.Context
import android.view.View
import android.widget.Toast

fun Context.showToast(msg: String) {

    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun View.show(){
    this.visibility=View.VISIBLE
}

fun View.gone(){
    this.visibility=View.GONE
}