package com.swipeapp.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.swipeapp.databinding.ActivityMainBinding
import com.swipeapp.domain.model.ProductModel
import com.swipeapp.network.ResponseStatus
import com.swipeapp.ui.adapter.ProductListAdapter
import com.swipeapp.utils.toast
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ProductActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private val viewModel: ProductViewModel by inject()
    private lateinit var mProductListAdapter: ProductListAdapter
    var suggestionList = mutableListOf<ProductModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setUpUI()

        binding.fbButton.setOnClickListener {
            startActivity(Intent(this@ProductActivity,AddProductActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.viewModelScope.launch {
            viewModel.getProduct()
        }
    }
    private fun setupViewModel() {
        lifecycleScope.launch {
            viewModel.productList.collectLatest {
                progressHideShow(false)
                when (it) {
                    is ResponseStatus.Idle -> {
                        Log.d("TAG", "Idle")
                    }
                    is ResponseStatus.Error -> {
                        toast(it.message ?: "")
                    }
                    is ResponseStatus.Loading -> {
                        binding.progressCircular.visibility = View.VISIBLE
                    }
                    is ResponseStatus.Success -> {
                        suggestionList = it.data as MutableList<ProductModel>
                        notifyAdapter()
                    }
                }
            }
        }
    }

    private fun setUpUI() {
        binding.rvProduct.layoutManager = LinearLayoutManager(this)
        binding.searchSuggest.requestFocus();
        binding.searchSuggest.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (s.length > 1) {
                    filter(suggestionList.filter {
                        it.productName.contains(s.toString(), true)
                    })
                    binding.ivClear.visibility = View.VISIBLE
                    progressHideShow(true)
                } else {
                    closeSuggestion()
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
        })
        binding.ivClear.setOnClickListener {
            closeSuggestion()
            binding.searchSuggest.text.clear()
        }
    }

    private fun notifyAdapter() {
        mProductListAdapter = ProductListAdapter(suggestionList)
        binding.rvProduct.adapter = mProductListAdapter
        mProductListAdapter.notifyDataSetChanged()
    }

    private fun filter(data: List<ProductModel>) {
        progressHideShow(false)
        mProductListAdapter.apply {
            ProductListAdapter(data)
            notifyDataSetChanged()
        }
    }

    fun closeSuggestion() {
        if (::mProductListAdapter.isInitialized) {
            progressHideShow(false)
            notifyAdapter()
        }
        binding.ivClear.visibility = View.GONE
    }

    fun progressHideShow(flag: Boolean) {
        if (flag) {
            binding.progressCircular.visibility = View.VISIBLE
        } else {
            binding.progressCircular.visibility = View.GONE
        }
    }
}