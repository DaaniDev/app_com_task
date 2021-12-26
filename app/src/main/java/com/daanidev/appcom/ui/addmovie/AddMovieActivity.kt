package com.daanidev.appcom.ui.addmovie

import android.app.Activity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.daanidev.appcom.R
import com.daanidev.appcom.base.BaseActivity
import com.daanidev.appcom.databinding.ActivityAddMovieBinding
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels

import androidx.core.app.ActivityCompat.startActivityForResult
import com.daanidev.appcom.extensions.gone
import com.daanidev.appcom.extensions.show
import com.daanidev.appcom.extensions.showToast
import com.daanidev.appcom.network.ApiStatus
import com.daanidev.appcom.utils.AppConstants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_add_movie.*
import java.io.File
import java.io.InputStream

@AndroidEntryPoint
class AddMovieActivity : BaseActivity<ActivityAddMovieBinding>() {


    private val addMovieViewModel by viewModels<AddMovieViewModel>()
    private var categoryID=-1
    private var categoryName=""
    private lateinit var inputStream:InputStream
    private lateinit var file:File
    override fun setLayout(): ActivityAddMovieBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_movie)

    override fun performBindings() {

        intent?.let {
            categoryID=it.getIntExtra(AppConstants.CATEGORY_INTENT_KEY,-1)
            categoryName=it.getStringExtra(AppConstants.CATEGORY_NAME_INTENT_KEY).toString()
        }

        binding.tvAddTo.text="Add to ${categoryName}"

        setupSpinner()

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        setActivityTitle("Add Movie")
        enableBackButton(true)


        binding.cardSelectImage.setOnClickListener {
        pickImagesLauncher.launch("image/*")
        }


        binding.btnConfirmAddMovie.setOnClickListener {

            addMovieViewModel.addMovie(categoryID,binding.etMovieTitle.text.toString(),
            binding.spinnerRating.selectedItem.toString().toInt(),binding.etMovieDesc.text.toString(),file.name,inputStream)
        }


        addMovieViewModel.addMovieLiveData.observe(this,{

            when(it.status){
                ApiStatus.SUCCESS->{
                    this.showToast("Movie Added")
                }
                ApiStatus.ERROR->{
                    this.showToast(it.message.toString())
                }
            }
        })

        binding.tvRemoveImage.setOnClickListener {

            binding.ivSelectedImage.setImageURI(null)
            binding.tvRemoveImage.gone()
            binding.cardSelectedImage.gone()
        }

    }

    private val pickImagesLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {

                binding.cardSelectedImage.show()
                binding.tvRemoveImage.show()
                val stream = contentResolver.openInputStream(it)

               inputStream = stream!!
                file = File(it.path)
                binding.ivSelectedImage.setImageURI(it)

            }
        }
    private fun setupSpinner(){
        ArrayAdapter.createFromResource(
            this,
            R.array.rating_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.spinnerRating.adapter = adapter
        }

        binding.spinnerRating.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }



}