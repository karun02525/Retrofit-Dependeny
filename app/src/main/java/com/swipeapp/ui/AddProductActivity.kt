package com.swipeapp.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.github.dhaval2404.imagepicker.ImagePicker
import com.swipeapp.R
import com.swipeapp.databinding.ActivityAddProductBinding
import com.swipeapp.databinding.ActivityMainBinding
import com.swipeapp.domain.model.ProductModel
import com.swipeapp.network.ResponseStatus
import com.swipeapp.utils.toast
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.io.IOException

class AddProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProductBinding
    private val viewModel: ProductViewModel by inject()
    private var uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()

        binding.saveAction.setOnClickListener {
            validation()
        }

        binding.chooseImage.setOnClickListener {
            chooseImage()
        }
    }

    private fun validation(){
        val name = binding.itemName.text.toString()
        val type = binding.itemType.text.toString()
        val price = binding.itemPrice.text.toString()
        val tax = binding.itemTax.text.toString()

        if(name.isEmpty()){
            toast("Please enter product name")
        }else if(type.isEmpty()){
            toast("Please enter product type")
        }else if(price.isEmpty()){
            toast("Please enter product price")
        }else if(tax.isEmpty()){
            toast("Please enter product tax")
        }else{
            viewModel.viewModelScope.launch {
                viewModel.addProduct(name, type, price, tax,uri)
            }
        }
    }
    private fun setupViewModel() {
        lifecycleScope.launch {
            viewModel.addProduct.collectLatest {
                progressHideShow(false)
                when (it) {
                    is ResponseStatus.Idle -> {
                        Log.d("TAG", "Idle")
                    }
                    is ResponseStatus.Error -> {
                        toast(it.message ?: "")
                    }
                    is ResponseStatus.Loading -> {
                        progressHideShow(true)
                    }
                    is ResponseStatus.Success -> {
                        it.message?.let { it1 -> toast(it1) }
                        finish()
                    }
                }
            }
        }
    }

    private fun progressHideShow(flag: Boolean) {
        if (flag) {
            binding.progressCircular.visibility = View.VISIBLE
        } else {
            binding.progressCircular.visibility = View.GONE
        }
    }

    private fun chooseImage(){
        ImagePicker.with(this)
            .crop()
            .compress(1024)
            .maxResultSize(1080, 1080)
            .start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                val uri: Uri = data?.data!!
                this.uri = uri
                binding.chooseImage.setImageURI(uri)
            }
            ImagePicker.RESULT_ERROR -> {
                toast( ImagePicker.getError(data))
            }
            else -> {
                toast("Task Cancelled")
            }
        }
    }
}