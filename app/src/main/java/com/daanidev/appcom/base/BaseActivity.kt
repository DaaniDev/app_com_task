package com.daanidev.appcom.base

import android.R
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<Binding : ViewDataBinding> : AppCompatActivity() {

    private var _binding: Binding? = null
    protected val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = setLayout()


    }

    override fun onResume() {
        super.onResume()
        performBindings()
    }
    override fun onDestroy() {
        super.onDestroy()
        cleanup()
        _binding = null

    }

    open fun setActivityTitle(title: String) {
        supportActionBar?.title = title
    }

    open fun enableBackButton(boolean: Boolean=false)
    {
        supportActionBar?.setDisplayHomeAsUpEnabled(boolean)
    }

    protected abstract fun setLayout(): Binding

    protected abstract fun performBindings()

    protected open fun cleanup() {}

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}