package com.swipeapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.swipeapp.databinding.ActivityMainBinding
import com.swipeapp.network.ResponseStatus
import com.swipeapp.ui.adapter.ProductListAdapter
import com.swipeapp.utils.toast
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ProductViewModel by inject()
    private lateinit var mUserAdapter: ProductListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()

        viewModel.viewModelScope.launch {
            viewModel.getProduct()
        }
    }

    private fun setupViewModel() {
        lifecycleScope.launch {
            viewModel.productList.collectLatest {
                binding.progressCircular.visibility = View.GONE
                when (it) {
                    is ResponseStatus.Idle -> {
                        Log.d("TAG", "Idle")
                    }
                    is ResponseStatus.Error -> {
                        toast(it.message ?:"")
                    }
                    is ResponseStatus.Loading -> {
                        binding.progressCircular.visibility = View.VISIBLE
                    }
                    is ResponseStatus.Success -> {
                        binding.rv.layoutManager = LinearLayoutManager(this@MainActivity)
                        mUserAdapter = it.data?.let { it1 -> ProductListAdapter(it1) }!!
                        binding.rv.adapter = mUserAdapter
                    }
                }
            }
        }
    }
}