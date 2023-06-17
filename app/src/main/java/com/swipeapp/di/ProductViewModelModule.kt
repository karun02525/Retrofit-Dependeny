package com.swipeapp.di

import com.swipeapp.ui.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productViewModelModule = module {
    viewModel { ProductViewModel(get()) }
}